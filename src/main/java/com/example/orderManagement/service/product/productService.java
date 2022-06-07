package com.example.orderManagement.service.product;

import com.example.orderManagement.dto.productDto;
import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface productService {

    productDto insertProduct(productDto product_Dto);

    List<productDto> getAllProducts();

    productDto getProductById(int id);

    productDto updateProduct(productDto ProductDto, int id);

    void deleteProductById(int id);

    List<productDto> findProductExceptPrice(double price);
    List<productDto> findIsStockable(int id);

}
