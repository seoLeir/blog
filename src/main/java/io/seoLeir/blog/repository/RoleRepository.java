package io.seoLeir.blog.repository;

import io.seoLeir.blog.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Short> {
    @Query("select r from Roles r where r.name = :role")
    Optional<Roles> findByName(@Param("role") String name);
}
