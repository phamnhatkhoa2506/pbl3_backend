package com.pbl3.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff extends User {
    static float base_salary;
    float salary_coefficient;

    @OneToMany(mappedBy = "staff")
    List<Delivery> deliveryList;

    public float getFinalSalary() {
        return base_salary * salary_coefficient;
    }
}
