package de.lhind.internship.mini.project.common.GuestProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestProfileRepo extends JpaRepository<GuestProfile, Long> {
}
