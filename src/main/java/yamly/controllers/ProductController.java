package yamly.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yamly.controllers.helpers.Error;
import yamly.models.Product;
import yamly.services.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts() {
        LOGGER.info("Fetching all Products");

        List<Product> products = this.productService.getAllProducts();

        if (products.isEmpty()) {
            LOGGER.info("No Products");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {
        LOGGER.info("Fetching Product with id {}", id);

        if(!this.productService.productExists(id)) {
            LOGGER.error("Product with id {} not found.", id);
            return new ResponseEntity(new Error("Product with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        Product product = this.productService.getProduct(id);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
