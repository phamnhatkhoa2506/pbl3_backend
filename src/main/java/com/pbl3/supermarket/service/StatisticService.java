package com.pbl3.supermarket.service;

import com.pbl3.supermarket.entity.Category;
import com.pbl3.supermarket.entity.Product;
import com.pbl3.supermarket.repository.CustomerRepository;
import com.pbl3.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Map<String, Integer> countByCategories() {
        Map<String, Integer> result = new HashMap<>();
        for(Product p : productRepository.findAll()) {
            if(p.getCategories() != null) {
                Category c = p.getCategories().getFirst();
                result.put(c.getName(), result.getOrDefault(c.getName(), 0) + 1);
            }
        }
        return result;
    }
}