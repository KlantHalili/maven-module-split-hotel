package de.lhind.internship.mini.project.common.Guest;

import de.lhind.internship.mini.project.common.GuestProfile.GuestProfile;
import de.lhind.internship.mini.project.common.Reservation.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    private GuestProfile guestProfile;

}
