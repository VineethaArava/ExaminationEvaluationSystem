package com.examination.app.repository;

import com.examination.app.entity.User_App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User_App, Integer> {

    /**
     * Retrieves an optional User entity by email.
     *
     * @param email The email of the user.
     * @return An optional User entity.
     */
    Optional<User_App> findUserByEmail(String email);

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email The email to check.
     * @return True if a user with the email exists, false otherwise.
     */
    Boolean existsByEmail(String email);
}
