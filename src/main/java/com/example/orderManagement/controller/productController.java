package com.example.orderManagement.controller;

import com.example.orderManagement.dto.productDto;
import com.example.orderManagement.exception.BadRequestException;
import com.example.orderManagement.service.product.productService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for product")
@RestController
@RequestMapping("/api/product")
public class productController {

    private final Logger log = LoggerFactory.getLogger(productController.class);


    @Autowired
    private productService product_service;
    public productController(productService product_service) {

        this.product_service = product_service;
    }

    @GetMapping
    public ResponseEntity<List<productDto>> getAllProducts() {
        return ResponseEntity.ok().body(product_service.getAllProducts()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<productDto> getProductById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(product_service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<productDto> insertProduct(@Valid @RequestBody productDto product_Dto) {
        if (product_Dto.getId() != null) {
            log.error("Cannot have an ID {}", product_Dto);
            throw new BadRequestException(productController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(product_service.insertProduct(product_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<productDto> updateProduct(@Valid @RequestBody productDto product_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(product_service.updateProduct(product_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        product_service.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/price/{price}")
    public List<productDto> findProductExceptPrice(@PathVariable(name = "price") double price) {

        return product_service.findProductExceptPrice(price);
    }

    @GetMapping("/stock/{pid}")
    public List<productDto> findIsStockable(@PathVariable(name = "pid") int id) {
        return product_service.findIsStockable(id);
    }
}
