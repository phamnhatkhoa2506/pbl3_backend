package com.pbl3.supermarket.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReceiptProduct> receiptProducts = new ArrayList<>();

    float totalPrice;
    LocalDate bill_date;
    LocalTime bill_time;

    public void addReceiptProduct(ReceiptProduct receiptProduct) {
        receiptProducts.add(receiptProduct);
        if(receiptProduct != null) {
            receiptProduct.setReceipt(this);
        }
    }
}