package com.campasklad.facility.enitity;

import com.campasklad.facility.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writeoffs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Writeoff extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    Facility facility;

    DocumentStatus status;
}
