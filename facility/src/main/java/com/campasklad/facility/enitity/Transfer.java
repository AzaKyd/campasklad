package com.campasklad.facility.enitity;

import com.campasklad.facility.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transfers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_facility_id", nullable = false)
    private Facility sourceFacility;

    @ManyToOne
    @JoinColumn(name = "destination_facility_id", nullable = false)
    private Facility destinationFacility;

    private DocumentStatus status;
}
