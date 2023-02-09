package com.challenge.rbc.controller;

import com.challenge.rbc.entity.StockData;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.challenge.rbc.service.StockDataServiceImpl;

import java.util.List;


@Api(value = "Stock Data Controller")
@RestController
@RequestMapping("/api/stock-data")
public class StockDataController {

    @Autowired
    private StockDataServiceImpl stockDataService;
    private static final Logger LOG = LoggerFactory.getLogger(StockDataController.class);

    /**
     * insert bulk data as a file
     * @param clientId
     * @param file
     * @return
     */
    @PostMapping("/bulk-insert")
    public ResponseEntity<String> bulkInsert(@RequestHeader("X-Client_Id") String clientId,
                                             @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        if (!clientId.equals("abc123")) {
            return new ResponseEntity<>("Invalid client Id", HttpStatus.UNAUTHORIZED);
        }

        try {
            stockDataService.bulkInsert(file, clientId);
            return new ResponseEntity<>("Data inserted successfully", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Error inserting data for clientId: {}", clientId, e);
            return new ResponseEntity<>("Error inserting data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * return specific stock by symbol
     * @param clientId
     * @param symbol
     * @return
     */
    @GetMapping("/{symbol}")
    public ResponseEntity<?> getStockData(@RequestHeader("X-Client_Id") String clientId,
                                          @PathVariable("symbol") String symbol) {
        if (!clientId.equals("abc123")) {
            return new ResponseEntity<>("Invalid client Id", HttpStatus.UNAUTHORIZED);
        } else {
            List<StockData> stockData = stockDataService.getStockDataBySymbol(symbol);
            if (stockData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stockData, HttpStatus.OK);
        }
    }

    /**
     * add one row of stock
     * @param stockData
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<StockData> addStockData(@RequestBody StockData stockData) {
        StockData newStockData = stockDataService.addStockData(stockData);
        return new ResponseEntity<>(newStockData, HttpStatus.CREATED);
    }

    /**
     * find list of stock by symbol
     * @param symbol
     * @return
     */
    @GetMapping("/findStock/{stock}")
    public ResponseEntity<List<StockData>> getStockDataBySymbol(@PathVariable String symbol) {
        List<StockData> stockDataList = stockDataService.findBySymbol(symbol);
        if (stockDataList == null || stockDataList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockDataList, HttpStatus.OK);
    }

    /**
     * return all stock data
     * @return
     */
    @GetMapping("/allStockData")
    public ResponseEntity<List<StockData>> getAllStockData() {
        List<StockData> stockDataList = stockDataService.findAll();
        if (stockDataList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(stockDataList, HttpStatus.OK);
    }
}


