package yamly.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import yamly.repositories.ProductRepository;
import yamly.models.Product;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean productExists(Integer id) {
        return productRepository.existsById(id);
    }
}
