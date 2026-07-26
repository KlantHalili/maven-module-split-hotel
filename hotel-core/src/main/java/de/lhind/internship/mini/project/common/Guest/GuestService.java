package de.lhind.internship.mini.project.common.Guest;

import de.lhind.internship.mini.project.common.DTOs.requests.GuestRequestDTO;
import de.lhind.internship.mini.project.common.DTOs.responses.GuestResponseDTO;
import de.lhind.internship.mini.project.common.GuestProfile.GuestProfileRepo;
import de.lhind.internship.mini.project.common.exceptions.GuestExistsException;
import de.lhind.internship.mini.project.common.exceptions.GuestNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    private final GuestRepo guestRepo;
    private final GuestProfileRepo guestProfileRepo;

    public GuestService(GuestRepo guestRepo, GuestProfileRepo guestProfileRepo){
        this.guestRepo = guestRepo;
        this.guestProfileRepo = guestProfileRepo;
    }

    public void createGuest(GuestRequestDTO guestRequestDTO){
        if(guestRepo.existsByEmail(guestRequestDTO.getEmail())){
            throw new GuestExistsException(guestRequestDTO.getEmail());
        }
        Guest guest = dtoToObjectConverter(guestRequestDTO);
        guestRepo.save(guest);
    }

    public Page<GuestResponseDTO> listAllGuests (Pageable pageable){
        return guestRepo.findAll(pageable).map(guest -> objectToDtoConverter(guest));
    }

    public GuestResponseDTO getGuest(Long id){
        Guest guest = guestRepo.findById(id)
                .orElseThrow(()-> new GuestNotFoundException(id));
        return objectToDtoConverter(guest);
    }

    public void updateGuest(Long id, GuestRequestDTO guestRequestDTO){
        Guest guest = guestRepo.findById(id)
                .orElseThrow(()-> new GuestNotFoundException(id));
        if (guestRepo.existsByEmail(guestRequestDTO.getEmail())
                && !guest.getEmail().equals(guestRequestDTO.getEmail())) {
            throw new GuestExistsException(guestRequestDTO.getEmail());
        }
        guest.setEmail(guestRequestDTO.getEmail());
        guest.setFirstName(guestRequestDTO.getName());
        guest.setLastName(guestRequestDTO.getLastName());
        guest.setPhoneNumber(guestRequestDTO.getPhoneNumber());
        guestRepo.save(guest);
    }

    public static Guest dtoToObjectConverter(GuestRequestDTO guestRequestDTO){
        return Guest.builder()
                .email(guestRequestDTO.getEmail())
                .firstName(guestRequestDTO.getName())
                .lastName(guestRequestDTO.getLastName())
                .phoneNumber(guestRequestDTO.getPhoneNumber())
                .build();
    }

    public static GuestResponseDTO objectToDtoConverter(Guest guest){
        return GuestResponseDTO.builder()
                .email(guest.getEmail())
                .id(guest.getId())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .phoneNumber(guest.getPhoneNumber())
                .build();
    }
}
