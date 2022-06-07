package com.example.orderManagement.service.stock;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.dto.StockDto;
import com.example.orderManagement.entity.Order;
import com.example.orderManagement.entity.Stock;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface stockService {
    StockDto insertStock(StockDto stock_Dto);

    List<StockDto> getAllStocks();

    StockDto getStockById(int id);

    StockDto updateStock(StockDto stock_Dto, int id);

    void deleteStockById(int id);

    List<Integer> findQuantityProductByName(String name);
    StockDto findStockCertainDate(String date);
}
