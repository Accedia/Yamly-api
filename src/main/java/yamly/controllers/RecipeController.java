package yamly.controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yamly.controllers.helpers.Error;
import yamly.models.Recipe;
import yamly.services.RecipeService;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class RecipeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/recipes/random/{uid}", method = RequestMethod.GET)
    public ResponseEntity<?> fetchRandomRecipe(@PathVariable("uid") String uid) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if(!document.exists()) {
            LOGGER.error("User with uid {uid} does not exist.", uid);
            return new ResponseEntity(new Error("User with uid " + uid
                    + " does not exist."), HttpStatus.NOT_FOUND);
        }

        List<Integer> likedProductsIds;
        List<Integer> dislikedProductsIds;

        try {
            likedProductsIds = (List<Integer>) document.getData().get("likedProducts");
            dislikedProductsIds = (List<Integer>) document.getData().get("dislikedProducts");
        } catch (NullPointerException error) {
            LOGGER.error("User with uid {uid} has not liked/disliked products yet.", uid);
            return new ResponseEntity(new Error("User with uid " + uid
                    + " has not liked/disliked products yet."), HttpStatus.NOT_FOUND);
        }

        List<Integer> recipesIds = this.recipeService.findAllIdsOfRecipesWithLikedProducts(likedProductsIds, dislikedProductsIds);

        Collections.shuffle(recipesIds);

        Integer recipeId = (Integer) recipesIds.toArray()[0];

        Recipe recipe = this.recipeService.getRecipe(recipeId);

        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }
}