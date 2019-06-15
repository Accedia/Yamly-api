package yamly.services;

import yamly.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(Integer id);

    boolean productExists(Integer id);
}
