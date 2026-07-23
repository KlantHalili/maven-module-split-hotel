package de.lhind.internship.mini.project.common.Hotel;

import de.lhind.internship.mini.project.common.Room.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    @Column(unique = true, length = 255)
    private String address;

    @Column(name = "star_rating")
    private int hotelStarRating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> hotelRooms = new ArrayList<>();
}
