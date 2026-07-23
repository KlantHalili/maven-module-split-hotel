package de.lhind.internship.mini.project.common.GuestProfile;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guests/{guestId}/profile")
public class GuestProfileController {
    private final GuestProfileService guestProfileService;

    public GuestProfileController(GuestProfileService guestProfileService) {
        this.guestProfileService = guestProfileService;
    }

    @GetMapping
    public ResponseEntity<GuestProfileResponseDTO> getGuestProfile(@PathVariable Long guestId){
        return new ResponseEntity<>(guestProfileService.getGuestProfileByGuestId(guestId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createGuestProfile(@PathVariable Long guestId, @Valid @RequestBody GuestProfileRequestDTO guestProfileRequestDTO){
        guestProfileService.createGuestProfile(guestId, guestProfileRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
