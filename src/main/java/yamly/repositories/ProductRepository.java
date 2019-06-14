package yamly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import yamly.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
