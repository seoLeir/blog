package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationFile;
import io.seoLeir.socialmedia.entity.keys.PublicationFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PublicationFileRepository extends JpaRepository<PublicationFile, PublicationFileId> {

    @Query(name = "select p.file_uuid from publication_files p where p.publication_uuid = :id")
    List<UUID> findAllByPublicationId(@Param("id")UUID id);
}
