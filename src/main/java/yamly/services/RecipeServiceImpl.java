package yamly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yamly.models.Recipe;
import yamly.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return this.recipeRepository.findAll();
    }

    @Override
    public List<Integer> findAllIdsOfRecipesWithLikedProducts(List<Long> liked, List<Long> disliked) {
        return this.recipeRepository.findAllIdsOfRecipesWithLikedProducts(liked, disliked);
    }

    @Override
    public Recipe getRecipe(Integer id) {
        return this.recipeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean recipeExists(Integer id) {
        return this.recipeRepository.existsById(id);
    }
}
