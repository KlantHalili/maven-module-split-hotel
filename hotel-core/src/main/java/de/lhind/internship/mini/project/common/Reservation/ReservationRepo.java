package de.lhind.internship.mini.project.common.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    @Query("""
    SELECT COUNT(r) > 0
    FROM Reservation r
    WHERE r.room.id = :roomId
      AND r.reservationStatus <> 'CANCELLED'
      AND r.checkInDate < :checkOutDate
      AND r.checkOutDate > :checkInDate""")
    boolean reservationOverlap(@Param("roomId") Long roomId, @Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate
    );
//   public Page<Reservation> findAll(Pageable pageable);
@Query("""
    SELECT COUNT(r)
    FROM Reservation r
    WHERE r.room.id = :roomId
      AND r.reservationStatus <> 'CANCELLED'
    """)
public Long nonCancelledReservations(@Param("roomId") Long roomId);
}
