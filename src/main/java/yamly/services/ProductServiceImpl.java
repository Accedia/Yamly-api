package yamly.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import yamly.repositories.ProductRepository;
import yamly.models.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean productExists(Integer id) {
        return this.productRepository.existsById(id);
    }
}
