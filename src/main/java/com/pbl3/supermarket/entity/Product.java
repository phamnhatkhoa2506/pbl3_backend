package com.pbl3.supermarket.entity;

import com.pbl3.supermarket.dto.response.CategoryResponse;
import com.pbl3.supermarket.dto.response.ProductResponse;
import com.pbl3.supermarket.dto.response.SupplierResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    String imageUrl;
    int stockQuantity;
    float price;
    String unit_price;
    float discount;
    LocalDate createDate;
    LocalDate expiryDate;

    int nBuy;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @OneToMany(mappedBy = "product")
    List<StockImport> stocks;

    private SupplierResponse toSupplierResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .email(supplier.getEmail())
                .name(supplier.getName())
                .build();
    }
    private List<CategoryResponse> toCategoryResponse(List<Category> categories) {
        List<CategoryResponse> categoriesResponse = new ArrayList<>();
        for(Category category : categories) {
            categoriesResponse.add(
                    CategoryResponse.builder()
                            .id(category.getId())
                            .description(category.getDescription())
                            .name(category.getName())
                            .build()
            );
        }
        return categoriesResponse;
    }
    public ProductResponse toProductResponse() {
        return ProductResponse.builder()
                .id(this.getId())
                .supplier(toSupplierResponse(this.supplier))
                .categories(toCategoryResponse(this.categories))
                .createDate(this.getCreateDate())
                .discount(this.getDiscount())
                .price(this.getPrice())
                .expiryDate(this.getExpiryDate())
                .unit_price(this.getUnit_price())
                .quantity(this.getStockQuantity())
                .name(this.getName())
                .imageUrl(this.getImageUrl())
                .build();
    }

    public void increaseQuantity(int quantity)
    {
        this.stockQuantity += quantity;
    }

    public void increaseNBuy(int quantity){
        this.nBuy += quantity;
    }
}
