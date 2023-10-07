package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID>,
        PagingAndSortingRepository<Publication, UUID>, JpaSpecificationExecutor<Publication> {

    @Query(value = "select p from Publication p where p.user.username in (:usernames)",
            countQuery = "select count(p.id) from Publication p")
    Page<Publication> getAllPublicationsFromUserFollowing(Pageable pageable, @Param("usernames") String... usernames);

    @Query(value = "select p from Publication p where p.user.id = :id",
            countQuery = "select count (p.id) from Publication p")
    Page<Publication> getAllByUserId(Pageable pageable, @Param("id") UUID userUuid);

    @Modifying(flushAutomatically = true)
    void deleteById(@Nullable UUID id);

}
