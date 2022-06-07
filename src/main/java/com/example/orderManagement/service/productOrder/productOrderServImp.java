package com.example.orderManagement.service.productOrder;

import com.example.orderManagement.dto.prodOrdCusDto;
import com.example.orderManagement.dto.productDto;
import com.example.orderManagement.entity.CusOrdProd;
import com.example.orderManagement.entity.Product;
import com.example.orderManagement.repository.ProductOrderRepository;
import com.example.orderManagement.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class productOrderServImp implements productOrderService{

    private ProductOrderRepository prodOrder_Repository;
    public productOrderServImp(ProductOrderRepository prodOrder_Repository) {
        this.prodOrder_Repository = prodOrder_Repository;
    }

    @Override
    public List<Double> findTotalPriceOfAllOrdersByCustomerId(int id) {
        List<Double> priceList = prodOrder_Repository.findTotalPriceOfAllOrdersByCustomerId(id);

        return priceList;
    }

    @Override
    public List<prodOrdCusDto> findAllOrders() {
        List<CusOrdProd> orderList = prodOrder_Repository.findAllOrders();
        return orderList.stream().map(prodOrdCus -> mapToDTO(prodOrdCus)).collect(Collectors.toList());
    }

    private prodOrdCusDto mapToDTO(CusOrdProd COProd){
        prodOrdCusDto product_Dto = new prodOrdCusDto();
        product_Dto.setQuantity(COProd.getQuantity());
        product_Dto.setName(COProd.getName());
        product_Dto.setPrice(COProd.getPrice());
        product_Dto.setVat(COProd.getVat());
        product_Dto.setCustomerId(COProd.getCustomerId());
        product_Dto.setOrderAt(COProd.getOrderAt());


        return product_Dto;
    }
}
