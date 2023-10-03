package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.File;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("select f.realName, f.mimeType from File f where f.filename = :id")
    Optional<File> findByFilename(@NonNull @Param("id") UUID fileUUID);
}
