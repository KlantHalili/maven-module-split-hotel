package de.lhind.internship.mini.project.common.Room;

import de.lhind.internship.mini.project.common.DTOs.requests.RoomRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.RoomResponseDTO;
import de.lhind.internship.mini.project.common.enums.RoomStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("api/hotels/{hotelId}/rooms")
    public ResponseEntity<Void> addRoomToHotel(@PathVariable Long hotelId, @Valid @RequestBody RoomRequestDTO roomRequestDTO){
        roomService.addRoomToHotel(hotelId, roomRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("api/hotels/{hotelId}/rooms")
    public ResponseEntity<Page<RoomResponseDTO>> listRoomsForHotel(@PathVariable Long hotelId, @PageableDefault(size = 5, sort = "roomNumber")Pageable pageable){
        return new ResponseEntity<>(roomService.listHotelRooms(hotelId, pageable), HttpStatus.OK);
    }

    @GetMapping("/api/rooms/{id}")
    public ResponseEntity<RoomResponseDTO> getRoom(@PathVariable Long id){
        return new ResponseEntity<>(roomService.getRoom(id), HttpStatus.OK);
    }

    @PutMapping("api/rooms/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequestDTO roomRequestDTO){
        roomService.updateRoom(id, roomRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/api/rooms/{id}/status")
    public ResponseEntity<Void> updateRoomStatus(@PathVariable Long id, @RequestParam RoomStatus roomStatus){
        roomService.updateRoomStatus(id, roomStatus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
