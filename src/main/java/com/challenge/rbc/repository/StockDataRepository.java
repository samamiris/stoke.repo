package com.challenge.rbc.repository;


import com.challenge.rbc.entity.StockData;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface StockDataRepository extends MongoRepository<StockData, String> {
    List<StockData> findByStock(String stock);
}