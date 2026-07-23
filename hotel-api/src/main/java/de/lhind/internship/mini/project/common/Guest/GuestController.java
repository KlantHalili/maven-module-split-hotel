package de.lhind.internship.mini.project.common.Guest;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<Page<GuestResponseDTO>> getAllGuests(@PageableDefault(size = 5, sort = "firstName")Pageable pageable){
        return new ResponseEntity<>(guestService.listAllGuests(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createGuest(@Valid @RequestBody GuestRequestDTO guestRequestDTO){
        guestService.createGuest(guestRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponseDTO> getGuest(@PathVariable Long id){
        return new ResponseEntity<>(guestService.getGuest(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestRequestDTO guestRequestDTO){
        guestService.updateGuest(id, guestRequestDTO);
        return ResponseEntity.ok().build();
    }
}
