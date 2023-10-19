package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("select f from File f where f.user.id = :id")
    List<File> findAllByUser(@Param("id") UUID id);

    @Query("select (count(f) > 0) from File f where f.filename = :filename")
    boolean existsByFilename(@Param("filename") UUID filename);
}
