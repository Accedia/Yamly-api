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

    public Product findTask(Integer id) {
        return productRepository.getOne(id);
    }
}
