package de.lhind.internship.mini.project.common.GuestProfile;

import de.lhind.internship.mini.project.common.Guest.Guest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "guest_profiles")
public class GuestProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private LocalDate dateOfBirth;

    private String nationality;

    private String preferredLanguage;

    @OneToOne
    @MapsId
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;
}
