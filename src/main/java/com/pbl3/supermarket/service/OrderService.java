package com.pbl3.supermarket.service;

import com.pbl3.supermarket.repository.CustomerRepository;
import com.pbl3.supermarket.repository.ReceiptRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    CustomerRepository customerRepository;

}
