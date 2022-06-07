package com.example.orderManagement.service.product;

import com.example.orderManagement.dto.productDto;
import com.example.orderManagement.entity.Product;
import com.example.orderManagement.exception.ResourceNotFoundException;
import com.example.orderManagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class productServImp implements productService{

    private ProductRepository product_Repository;

    public productServImp(ProductRepository product_Repository) {
        this.product_Repository = product_Repository;
    }

    @Override
    public List<productDto> findProductExceptPrice(double price) {
        List<Product> products = product_Repository.findProductExceptPrice(price);

        return products.stream().map(prod -> mapToDTO(prod)).collect(Collectors.toList());
    }

    @Override
    public List<productDto> findIsStockable(int id) {
        List<Product> products = product_Repository.findIsStockable(id);

        return products.stream().map(prod -> mapToDTO(prod)).collect(Collectors.toList());
    }
    @Override
    public productDto insertProduct(productDto product_Dto) {

        Product prod = mapToEntity(product_Dto);
        Product newProduct = product_Repository.save(prod);

        productDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    @Override
    public List<productDto> getAllProducts() {
        List<Product> prods = product_Repository.findAll();
        return prods.stream().map(prod -> mapToDTO(prod)).collect(Collectors.toList());

    }

    @Override
    public productDto getProductById(int id) {
        Product prods = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(prods);
    }

    @Override
    public productDto updateProduct(productDto product_Dto, int id) {
        Product prod = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        prod.setSlug(product_Dto.getSlug());
        prod.setName(product_Dto.getName());
        prod.setReference(product_Dto.getReference());
        prod.setPrice(product_Dto.getPrice());
        prod.setVat(product_Dto.getVat());
        prod.setStockable(product_Dto.isStockable());

        Product updatedProduct = product_Repository.save(prod);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(int id) {
        Product prods = product_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        product_Repository.delete(prods);
    }

    private productDto mapToDTO(Product prod){
        productDto product_Dto = new productDto();
        product_Dto.setId(prod.getId());
        product_Dto.setSlug(prod.getSlug());
        product_Dto.setName(prod.getName());
        product_Dto.setReference(prod.getReference());
        product_Dto.setPrice(prod.getPrice());
        product_Dto.setVat(prod.getVat());
        product_Dto.setStockable(prod.isStockable());

        return product_Dto;
    }

    private Product mapToEntity(productDto product_Dto){
        Product prod = new Product();
        prod.setSlug(product_Dto.getSlug());
        prod.setName(product_Dto.getName());
        prod.setReference(product_Dto.getReference());
        prod.setPrice(product_Dto.getPrice());
        prod.setVat(product_Dto.getVat());
        prod.setStockable(product_Dto.isStockable());

        return prod;

    }
}
