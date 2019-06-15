package yamly.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yamly.controllers.helpers.Error;
import yamly.models.Product;
import yamly.services.ProductService;

import java.util.Collections;
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

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/random", method = RequestMethod.GET)
    public ResponseEntity<?> getRandomBatchOfProducts(@RequestParam String number) {
        LOGGER.info("Fetching a batch of random Products");

        int batch;

        try {
            batch = Integer.parseInt(number);

            List<Product> products = this.productService.getAllProducts();

            if (products.isEmpty()) {
                LOGGER.info("No Products");
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

            Collections.shuffle(products);

            products = products.subList(0, batch);

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NumberFormatException error) {
            LOGGER.info("Please provide a valid number!");
            return new ResponseEntity<>(new Error("Please provide a valid number!"),
                    HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException  error) {
            LOGGER.info("Number must be positive!");
            return new ResponseEntity<>(new Error("Number must be positive!"),
                    HttpStatus.FORBIDDEN);
        } catch (IndexOutOfBoundsException error) {
            LOGGER.info("Number is out of bounds!");
            return new ResponseEntity<>(new Error("Number is out of bounds!"),
                    HttpStatus.FORBIDDEN);
        }
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

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
