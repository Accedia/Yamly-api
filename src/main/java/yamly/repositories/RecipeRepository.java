package yamly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yamly.models.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Query(value = "SELECT recipes_id FROM yamly.products_recipes WHERE products_id IN :liked AND products_id NOT IN :disliked", nativeQuery = true)
    List<Integer> findAllIdsOfRecipesWithLikedProducts(@Param("liked") List<Long> liked, @Param("disliked") List<Long> disliked);
}
