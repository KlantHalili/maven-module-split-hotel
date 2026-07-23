package de.lhind.internship.mini.project.common.Guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Long> {
    public boolean existsByEmail(String email);
}
