package de.lhind.internship.mini.project.common.Hotel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {
    public Optional<Hotel> findByAddress(String address);
    public Page<Hotel> findAll(Pageable pageable);
    public Page<Hotel> findByCityIgnoreCase(String city, Pageable pageable);
    public boolean existsByAddress(String address);
}
