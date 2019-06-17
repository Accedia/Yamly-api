package yamly.services;

import yamly.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    List<Integer> findAllIdsOfRecipesWithLikedProducts(List<Long> liked, List<Long> disliked);

    Recipe getRecipe(Integer id);

    boolean recipeExists(Integer id);
}
