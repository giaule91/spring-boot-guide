package com.helen.demo.controller;

import com.helen.demo.entity.Product;
import com.helen.demo.service.ProductService;
import com.helen.demo.view.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        //return a static product for demo
//        ProductView product1 = new ProductView();
//        product1.setId(1);
//        product1.setName("Phone");
//        product1.setPrice(BigDecimal.valueOf(100));
        return ResponseEntity.ok(this.productService.getProductByID(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        //do some logic to save ...
        // ...

        return ResponseEntity.ok(this.productService.updateOrInsert(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        //do some logic to save ...
        // ...
        product.setId(id);
        return ResponseEntity.ok(this.productService.updateOrInsert(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        this.productService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
