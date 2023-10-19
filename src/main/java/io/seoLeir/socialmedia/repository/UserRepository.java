package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("select u.id from User u where u.username = :username")
    UUID getUserUuidByUsername(String username);

//    @Modifying(flushAutomatically = true)
//    @Query("update User u set u.role = :role where u.username = :username")
//    void updateRole(@Param("username") String username, @Param("role") Roles role);
}
