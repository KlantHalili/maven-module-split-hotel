package de.lhind.internship.mini.project.common.Hotel;

import de.lhind.internship.mini.project.common.DTOs.requests.HotelRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.HotelResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController (HotelService hotelService){
        this.hotelService = hotelService;
    }


    @PostMapping
    public ResponseEntity<Void> createHotel(@Valid @RequestBody HotelRequestDTO hotelRequestDTO){
        hotelService.addHotel(hotelRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<HotelResponseDTO>> getAllHotels(@PageableDefault(size = 5, sort = "name")Pageable pageable){
        return new ResponseEntity<>(hotelService.getAllHotels(pageable), HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
        public ResponseEntity<HotelResponseDTO> getHotel(@PathVariable Long hotelId){
        return new ResponseEntity<>(hotelService.getHotel(hotelId), HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Void> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody HotelRequestDTO hotelRequestDTO){
        hotelService.updateHotel(hotelId, hotelRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId){
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<HotelResponseDTO>> searchHotels(@RequestParam String city, @PageableDefault(size = 5, sort = "name") Pageable pageable){
        return ResponseEntity.ok(hotelService.searchHotelsByCity(city, pageable));
    }

}
