package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("select u.id from User u where u.username = :username")
    UUID getUserUuidByUsername(String username);

}
