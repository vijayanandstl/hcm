package tech.stl.hcm.common.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.stl.hcm.common.db.entities.UserStatus;

import java.util.Optional;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
    Optional<UserStatus> findByName(String name);
} 