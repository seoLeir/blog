package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmailAndPassword(String email, String password);

    @Query("select u.id from User u where u.username = :username")
    UUID getUserUuidByUsername(String username);
}
