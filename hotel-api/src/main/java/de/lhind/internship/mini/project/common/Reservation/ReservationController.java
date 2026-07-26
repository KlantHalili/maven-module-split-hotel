package de.lhind.internship.mini.project.common.Reservation;

import de.lhind.internship.mini.project.common.DTOs.requests.ReservationRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.ReservationResponseDTO;
import de.lhind.internship.mini.project.common.enums.ReservationStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Void> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO){
        reservationService.createReservation(reservationRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ReservationResponseDTO>> listAllReservations(@PageableDefault(size = 5, sort = "createdAt")Pageable pageable){
        return new ResponseEntity<>(reservationService.listAllReservations(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservation(@PathVariable Long id){
        return new ResponseEntity<>(reservationService.getReservation(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateReservationStatus(@PathVariable Long id, @Valid @RequestParam ReservationStatus reservationStatus){
        reservationService.updateReservationStatus(id, reservationStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id){
        reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
