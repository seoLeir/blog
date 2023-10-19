package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Short> {
    @Query("select r from Roles r where r.name = ?1")
    Optional<Roles> findByName(String name);
}
