package com.campasklad.products.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDto {

    Long id;
    String name;
    String phone;
    String socialLink;
    String location;
    String aisleNumber;
    String containerNumber;
}
