package com.example.orderManagement.controller;

import com.example.orderManagement.dto.StockDto;
import com.example.orderManagement.entity.Stock;
import com.example.orderManagement.exception.BadRequestException;
import com.example.orderManagement.service.stock.stockService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Api(value = "CRUD REST APIs for stock")
@RestController
@RequestMapping("/api/stock")
public class stockController {


    private final Logger log = LoggerFactory.getLogger(stockController.class);


    @Autowired
    private stockService stock_service;
    public stockController(stockService stock_service) {

        this.stock_service = stock_service;
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return ResponseEntity.ok().body(stock_service.getAllStocks()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(stock_service.getStockById(id));
    }

    @PostMapping
    public ResponseEntity<StockDto> insertStock(@Valid @RequestBody StockDto stock_Dto) {
        if (stock_Dto.getId() != null) {
            log.error("Cannot have an ID {}", stock_Dto);
            throw new BadRequestException(stockController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(stock_service.insertStock(stock_Dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateStock(@Valid @RequestBody StockDto stock_Dto
            , @PathVariable(name = "id") int id) {

        return new ResponseEntity<>(stock_service.updateStock(stock_Dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {
        stock_service.deleteStockById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public List<Integer> findQuantityProductByName(@PathVariable(name = "name")String name) {
       return stock_service.findQuantityProductByName(name);

    }

    @GetMapping("/date/{date}")
    public StockDto findStockCertainDate(@PathVariable(name = "date")String date) {
        return stock_service.findStockCertainDate(date);

    }
}
