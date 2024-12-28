package com.campasklad.products.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "suppliers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier extends BaseEntity    {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "social_link")
    String socialLink;

    @Column(name = "location")
    String location;

    @Column(name = "aisle_number", length = 50)
    String aisleNumber;

    @Column(name = "container_number", length = 50)
    String containerNumber;
}
