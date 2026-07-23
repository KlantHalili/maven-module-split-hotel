package de.lhind.internship.mini.project.common.Room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    public Optional<Room> findByHotelIdAndRoomNumber(Long hotelId, String roomNumber);
    public Page<Room> findAllByHotelId(Long hotelId, Pageable pageable);
}
