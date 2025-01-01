package com.campasklad.facility.enitity;

import com.campasklad.facility.enums.DocumentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writeoffs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Writeoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    private DocumentStatus status;
}
