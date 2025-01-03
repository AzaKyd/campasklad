package com.campasklad.facility.entity;

import com.campasklad.facility.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "Transfers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "source_facility_id", nullable = false)
    Facility sourceFacility;

    @ManyToOne
    @JoinColumn(name = "destination_facility_id", nullable = false)
    Facility destinationFacility;

    DocumentStatus status;
}
