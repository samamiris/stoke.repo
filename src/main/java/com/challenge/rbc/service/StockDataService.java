package com.challenge.rbc.service;

import com.challenge.rbc.entity.StockData;
import com.challenge.rbc.repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;


import java.io.IOException;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.text.ParseException;
import java.util.stream.Collectors;

@Service
public interface StockDataService {

    public List<StockData> getStockDataBySymbol(String symbol);

    public List<StockData> bulkInsert(MultipartFile file, String clientId);

    public StockData addStockData(StockData stockData);

    List<StockData> findBySymbol(String symbol);
}