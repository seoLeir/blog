package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.File;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    @Query("select f from File f where f.user.id = :id")
    List<File> findAllByUser(@Param("id") UUID id);
}
