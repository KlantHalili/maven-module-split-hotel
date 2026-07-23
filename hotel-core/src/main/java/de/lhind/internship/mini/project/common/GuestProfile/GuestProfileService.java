package de.lhind.internship.mini.project.common.GuestProfile;

import de.lhind.internship.mini.project.common.Guest.Guest;
import de.lhind.internship.mini.project.common.Guest.GuestRepo;
import de.lhind.internship.mini.project.common.exceptions.GuestHasProfileException;
import de.lhind.internship.mini.project.common.exceptions.GuestNotFoundException;
import de.lhind.internship.mini.project.common.exceptions.GuestProfileNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GuestProfileService {
    private final GuestProfileRepo guestProfileRepo;
    private final GuestRepo guestRepo;

    public GuestProfileService(GuestProfileRepo guestProfileRepo, GuestRepo guestRepo){
        this.guestProfileRepo = guestProfileRepo;
        this.guestRepo = guestRepo;
    }

    public void createGuestProfile(Long guestId, GuestProfileRequestDTO guestProfileRequestDTO){
        Guest guest = guestRepo.findById(guestId)
                .orElseThrow(()->new GuestNotFoundException(guestId));
        if(guest.getGuestProfile() != null){
            throw new GuestHasProfileException(guestId);
        }
        guestProfileRepo.save(GuestProfile.builder()
                .guest(guest)
                .address(guestProfileRequestDTO.getAddress())
                .dateOfBirth(guestProfileRequestDTO.getDateOfBirth())
                .nationality(guestProfileRequestDTO.getNationality())
                .preferredLanguage(guestProfileRequestDTO.getPreferredLanguage())
                .build());
    }

    public GuestProfileResponseDTO getGuestProfile(Long guestProfileId){
        GuestProfile guestProfile = guestProfileRepo.findById(guestProfileId)
                .orElseThrow(()-> new GuestProfileNotFoundException());
        return GuestProfileResponseDTO.builder()
                .id(guestProfile.getId())
                .address(guestProfile.getAddress())
                .dateOfBirth(guestProfile.getDateOfBirth())
                .nationality(guestProfile.getNationality())
                .preferredLanguage(guestProfile.getPreferredLanguage())
                .build();
    }

    public GuestProfileResponseDTO getGuestProfileByGuestId(Long guestId){
        Guest guest = guestRepo.findById(guestId)
                .orElseThrow(()-> new GuestNotFoundException(guestId));
        if(guest.getGuestProfile() == null){
            throw new GuestProfileNotFoundException();
        }
        GuestProfile guestProfile = guest.getGuestProfile();
        return GuestProfileResponseDTO.builder()
                .id(guestProfile.getId())
                .address(guestProfile.getAddress())
                .dateOfBirth(guestProfile.getDateOfBirth())
                .nationality(guestProfile.getNationality())
                .preferredLanguage(guestProfile.getPreferredLanguage())
                .build();
    }
}
