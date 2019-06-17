package yamly.services;

import yamly.models.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();

    List<Integer> findAllIdsOfRecipesWithLikedProducts(List<Integer> liked, List<Integer> disliked);

    Recipe getRecipe(Integer id);

    boolean recipeExists(Integer id);
}
