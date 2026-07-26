package de.lhind.internship.mini.project.common.Hotel;

import de.lhind.internship.mini.project.common.DTOs.requests.HotelRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.HotelResponseDTO;
import de.lhind.internship.mini.project.common.exceptions.AddressAlreadyOccupiedException;
import de.lhind.internship.mini.project.common.exceptions.HotelAlreadyExistsException;
import de.lhind.internship.mini.project.common.exceptions.HotelNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    private final HotelRepo hotelRepo;

    public HotelService(HotelRepo hotelRepo){
        this.hotelRepo = hotelRepo;
    }


    public void addHotel(HotelRequestDTO hotelRequestDTO){
        if (hotelRepo.findByAddress(hotelRequestDTO.getAddress()).isPresent()) {
            throw new HotelAlreadyExistsException(hotelRequestDTO.getAddress());
        }
        Hotel hotel = Hotel.builder()
                .name(hotelRequestDTO.getName())
                .city(hotelRequestDTO.getCity())
                .address(hotelRequestDTO.getAddress())
                .hotelStarRating(hotelRequestDTO.getHotelStarRating())
                .build();

        hotelRepo.save(hotel);
    }

    public HotelResponseDTO getHotel(Long id){
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(()-> new HotelNotFoundException(id));
        return HotelResponseDTO.builder()
                .city(hotel.getCity())
                .hotelStarRating(hotel.getHotelStarRating())
                .name(hotel.getName())
                .address(hotel.getAddress())
                .id(hotel.getId())
                .build();
    }

    public void updateHotel(Long id, HotelRequestDTO hotelRequestDTO){
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(()-> new HotelNotFoundException(id));
        if(hotelRepo.existsByAddress(hotelRequestDTO.getAddress()) && !(hotel.getAddress().equals(hotelRequestDTO.getAddress()))){
            throw new AddressAlreadyOccupiedException(hotelRequestDTO.getAddress());
        }
        hotel.setAddress(hotelRequestDTO.getAddress());
        hotel.setCity(hotelRequestDTO.getCity());
        hotel.setName(hotelRequestDTO.getName());
        hotel.setHotelStarRating(hotelRequestDTO.getHotelStarRating());
        hotelRepo.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(()-> new HotelNotFoundException(id));
        hotelRepo.delete(hotel);
    }

    public Page<HotelResponseDTO> getAllHotels (Pageable pageable){
       return hotelRepo.findAll(pageable).map(
               hotel -> HotelResponseDTO.builder()
                       .name(hotel.getName())
                       .address(hotel.getAddress())
                       .city(hotel.getCity())
                       .hotelStarRating(hotel.getHotelStarRating())
                       .id(hotel.getId())
                       .build());
    }

    public Page<HotelResponseDTO> searchHotelsByCity(String city, Pageable pageable){
        return hotelRepo.findByCityIgnoreCase(city, pageable).map(
                hotel -> HotelResponseDTO.builder()
                        .name(hotel.getName())
                        .address(hotel.getAddress())
                        .city(hotel.getCity())
                        .hotelStarRating(hotel.getHotelStarRating())
                        .id(hotel.getId())
                        .build());
    }
}
