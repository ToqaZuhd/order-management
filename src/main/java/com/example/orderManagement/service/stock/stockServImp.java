package com.example.orderManagement.service.stock;

import com.example.orderManagement.dto.CustomerDto;
import com.example.orderManagement.dto.OrderDto;
import com.example.orderManagement.dto.StockDto;
import com.example.orderManagement.entity.Customer;
import com.example.orderManagement.entity.Order;
import com.example.orderManagement.entity.Stock;
import com.example.orderManagement.exception.ResourceNotFoundException;
import com.example.orderManagement.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class stockServImp implements stockService{
    private StockRepository stock_Repository;

    public stockServImp(StockRepository stock_Repository) {
        this.stock_Repository = stock_Repository;
    }
    @Override
    public StockDto insertStock(StockDto stock_Dto) {
        Stock stock = mapToEntity(stock_Dto);
        Stock newStock = stock_Repository.save(stock);

        StockDto stockResponse = mapToDTO(newStock);
        return stockResponse;
    }


    @Override
    public List<Integer> findQuantityProductByName(String name) {
        List<Integer> quantity = stock_Repository.findQuantityProductByName(name);

        return quantity;
    }

    @Override
    public StockDto findStockCertainDate(String date) {
        Stock stocks = stock_Repository.findStockCertainDate(date);

        return mapToDTO(stocks);
    }
    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stock_Repository.findAll();
        return stocks.stream().map(stock -> mapToDTO(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(int id) {
        Stock stocks = stock_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));
        return mapToDTO(stocks);
    }


    @Override
    public StockDto updateStock(StockDto stock_Dto, int id) {
        Stock stock = stock_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", id));

        stock.setQuantity(stock_Dto.getQuantity());
        stock.setUpdateAt(stock_Dto.getUpdateAt());


        Stock updatedStock = stock_Repository.save(stock);
        return mapToDTO(updatedStock);
    }

    @Override
    public void deleteStockById(int id) {
        Stock stocks = stock_Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        stock_Repository.delete(stocks);
    }

    private StockDto mapToDTO(Stock stock){
        StockDto stock_Dto = new StockDto();
        stock_Dto.setId(stock.getId());
        stock_Dto.setQuantity(stock.getQuantity());
        stock_Dto.setUpdateAt(stock.getUpdateAt());


        return stock_Dto;
    }

    private Stock mapToEntity(StockDto stock_Dto){
        Stock stock = new Stock();
        stock.setQuantity(stock_Dto.getQuantity());
        stock.setUpdateAt(stock_Dto.getUpdateAt());


        return stock;

    }

}
