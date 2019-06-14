package yamly.services;

import yamly.models.Product;

public interface ProductService {
    Product findProduct(Integer id);

    boolean productExists(Integer id);
}
