package com.pbl3.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Working_Date {
    @EmbeddedId
    StaffDateKey staff_date_key;

    @ManyToOne
    @MapsId("staffId")
    @JoinColumn(name="staff_id")
    Staff staff;

    Time start_time;
    Time end_time;
    int number_of_deliveries;

}