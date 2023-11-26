package io.seoLeir.blog.repository;

import io.seoLeir.blog.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

    @Query("select (count(f) > 0) from File f where f.filename = :filename")
    boolean existsByFilename(@Param("filename") UUID filename);
}
