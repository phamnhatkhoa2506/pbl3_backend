package com.pbl3.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Bill_Products")
public class ReceiptProduct {
    @EmbeddedId
    ReceiptProductId id;

    @ManyToOne
    @MapsId("receiptId")
    @JoinColumn(name="receiptID")
    Receipt receipt;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="product_id")
    Product product;

    int quantity;

    public ReceiptProduct(Receipt receipt, Product product, int quantity) {
        this.id = new ReceiptProductId(receipt.getId(), product.getId());
        this.receipt = receipt;
        this.product = product;
        this.quantity = quantity;
    }
}
