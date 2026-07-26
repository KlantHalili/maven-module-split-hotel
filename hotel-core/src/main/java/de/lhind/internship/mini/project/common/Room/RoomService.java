package de.lhind.internship.mini.project.common.Room;

import de.lhind.internship.mini.project.common.DTOs.requests.RoomRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.RoomResponseDTO;
import de.lhind.internship.mini.project.common.Hotel.Hotel;
import de.lhind.internship.mini.project.common.Hotel.HotelRepo;
import de.lhind.internship.mini.project.common.Reservation.ReservationRepo;
import de.lhind.internship.mini.project.common.enums.RoomStatus;
import de.lhind.internship.mini.project.common.exceptions.HotelNotFoundException;
import de.lhind.internship.mini.project.common.exceptions.RoomExistsException;
import de.lhind.internship.mini.project.common.exceptions.RoomHasReservationsException;
import de.lhind.internship.mini.project.common.exceptions.RoomNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final HotelRepo hotelRepo;
    private final ReservationRepo reservationRepo;

    public RoomService(RoomRepo roomRepo, HotelRepo hotelRepo, ReservationRepo reservationRepo){
        this.roomRepo = roomRepo;
        this.hotelRepo = hotelRepo;
        this.reservationRepo = reservationRepo;
    }

    public RoomResponseDTO getRoom(Long id){
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundException(id));
        return buildRoomResponse(room);
    }

    public void addRoomToHotel(Long hotelId, RoomRequestDTO roomRequestDTO){
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(()-> new HotelNotFoundException(hotelId));
//        for(Room room:hotel.getHotelRooms()){
//            if(room.getRoomNumber().equals(roomRequestDTO.getRoomNumber())){
//                throw new RoomExistsException(roomRequestDTO.getRoomNumber(), hotelId);
//            }
//        }
        if(roomRepo.findByHotelIdAndRoomNumber(hotelId, roomRequestDTO.getRoomNumber()).isPresent()){
            throw new RoomExistsException(roomRequestDTO.getRoomNumber(), hotelId);
        }
        Room room = buildRoomFromRequest(roomRequestDTO);
        room.setHotel(hotel);
        hotel.getHotelRooms().add(room);
        roomRepo.save(room);
    }

    public Page<RoomResponseDTO> listHotelRooms(Long hotelId, Pageable pageable){
        if(!hotelRepo.existsById(hotelId)){
            throw new HotelNotFoundException(hotelId);
        }
        return roomRepo.findAllByHotelId(hotelId, pageable).map(
                room -> buildRoomResponse(room)
        );
    }

    public void updateRoom(Long id, RoomRequestDTO roomRequestDTO){
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundException(id));
        if(roomRepo.findByHotelIdAndRoomNumber(room.getHotel().getId(), roomRequestDTO.getRoomNumber()).isPresent() && !(room.getRoomNumber().equals(roomRequestDTO.getRoomNumber()))){
            throw new RoomExistsException(roomRequestDTO.getRoomNumber(), room.getHotel().getId());
        }
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setRoomType(roomRequestDTO.getRoomType());
        room.setCapacity(roomRequestDTO.getCapacity());
        room.setStatus(roomRequestDTO.getRoomStatus());
        room.setPricePerNight(roomRequestDTO.getPricePerNight());

        roomRepo.save(room);
    }


    public void updateRoomStatus(Long id, RoomStatus roomStatus){
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundException(id));
        room.setStatus(roomStatus);
        roomRepo.save(room);
    }

    public void deleteRoom(Long id){
        Room room = roomRepo.findById(id)
                .orElseThrow(()-> new RoomNotFoundException(id));
        if(reservationRepo.nonCancelledReservations(room.getId())>0){
            throw new RoomHasReservationsException(room.getId());
        }
        roomRepo.deleteById(id);
    }

    public static RoomResponseDTO buildRoomResponse(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .status(room.getStatus())
                .hotelName(room.getHotel().getName())
                .build();
    }

    public static Room buildRoomFromRequest(RoomRequestDTO roomRequestDTO){
        return Room.builder()
                .roomNumber(roomRequestDTO.getRoomNumber())
                .capacity(roomRequestDTO.getCapacity())
                .pricePerNight(roomRequestDTO.getPricePerNight())
                .status(roomRequestDTO.getRoomStatus())
                .roomType(roomRequestDTO.getRoomType())
                .build();
    }
}
