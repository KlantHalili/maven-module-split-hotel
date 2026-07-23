package de.lhind.internship.mini.project.common.Reservation;

import de.lhind.internship.mini.project.common.Guest.Guest;
import de.lhind.internship.mini.project.common.Guest.GuestRepo;
import de.lhind.internship.mini.project.common.Room.Room;
import de.lhind.internship.mini.project.common.Room.RoomRepo;
import de.lhind.internship.mini.project.common.Room.RoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final GuestRepo guestRepo;
    private final RoomRepo roomRepo;

    public ReservationService(ReservationRepo reservationRepo, GuestRepo guestRepo, RoomRepo roomRepo) {
        this.reservationRepo = reservationRepo;
        this.guestRepo = guestRepo;
        this.roomRepo = roomRepo;
    }

    public void createReservation(ReservationRequestDTO reservationRequestDTO){
        Guest guest = guestRepo.findById(reservationRequestDTO.getGuestId())
                .orElseThrow(()->new GuestNotFoundException(reservationRequestDTO.getGuestId()));
        Room room = roomRepo.findById(reservationRequestDTO.getRoomId())
                .orElseThrow(()-> new RoomNotFoundException(reservationRequestDTO.getRoomId()));
        if (!room.getStatus().equals(RoomStatus.AVAILABLE)){
            throw new RoomNotAvailableException(room.getId());
        }
        if(reservationRepo.reservationOverlap(room.getId(), reservationRequestDTO.getCheckInDate(), reservationRequestDTO.getCheckOutDate())){
            throw new RoomNotAvailableException(room.getId());
        }
        if(room.getCapacity()< reservationRequestDTO.getNumberOfGuests()){
            throw new RoomTooSmallException(room.getId());
        }
        Reservation reservation = dtoToReservation(reservationRequestDTO);
        reservation.setGuest(guest);
        reservation.setRoom(room);
        guest.getReservationList().add(reservation);
        room.getReservationList().add(reservation);
        long nights = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        reservation.setTotalPrice(room.getPricePerNight().multiply(BigDecimal.valueOf(nights)));
        reservationRepo.save(reservation);
    }

    public static Reservation dtoToReservation(ReservationRequestDTO reservationRequestDTO){
        if(!reservationRequestDTO.getCheckOutDate().isAfter(reservationRequestDTO.getCheckInDate())){
            throw new InvalidReservationDatesException();
        }
        return Reservation.builder()
                .checkInDate(reservationRequestDTO.getCheckInDate())
                .checkOutDate(reservationRequestDTO.getCheckOutDate())
                .numberOfGuests(reservationRequestDTO.getNumberOfGuests())
                .reservationStatus(ReservationStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static ReservationResponseDTO reservationToDto(Reservation reservation){
        return ReservationResponseDTO.builder()
                .id(reservation.getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .numberOfGuests(reservation.getNumberOfGuests())
                .totalPrice(reservation.getTotalPrice())
                .reservationStatus(reservation.getReservationStatus())
                .createdAt(reservation.getCreatedAt())
                .build();
    }

    public Page<ReservationResponseDTO> listAllReservations(Pageable pageable){
        return reservationRepo.findAll(pageable).map(reservation -> reservationToDto(reservation));
    }

    public ReservationResponseDTO getReservation(Long id){
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(id));
        return reservationToDto(reservation);
    }

    public void updateReservationStatus(Long id, ReservationStatus reservationStatus){
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(()-> new ReservationNotFoundException(id));
        if (reservation.getReservationStatus() == ReservationStatus.CANCELLED
                && reservationStatus != ReservationStatus.CANCELLED) {
            if (reservationRepo.reservationOverlap(
                    reservation.getRoom().getId(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate())) {
                throw new RoomNotAvailableException(reservation.getRoom().getId());
            }
        }
        reservation.setReservationStatus(reservationStatus);
        reservationRepo.save(reservation);
    }

    public void cancelReservation(Long id){
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservation.setReservationStatus(ReservationStatus.CANCELLED);
        reservationRepo.save(reservation);
    }
}
