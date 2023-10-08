package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Publication;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID>,
        PagingAndSortingRepository<Publication, UUID>, JpaSpecificationExecutor<Publication> {

    @Query(value = "select p from Publication p where p.user.username in (:usernames)",
            countQuery = "select count(p.id) from Publication p")
    Page<Publication> getAllPublicationsFromUserFollowing(Pageable pageable, @Param("usernames") String... usernames);

    @Query(value = "select p from Publication p where p.user.username = :username",
            countQuery = "select count (p.id) from Publication p where p.user.username = :username")
    Page<Publication> getByUserUsername(@Param("username") String username, Pageable pageable);

    @Modifying(flushAutomatically = true)
    @Query("delete from Publication p where p.id = :id")
    void deleteById(@Nullable UUID id);


    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.viewCount = :newViewCount")
    void updateViewCount(@Param("newViewCount") Long newViewCount);

    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.tittle = :tittle, p.text = :text where p.id = :id")
    void updateTittleAndText(@Param("tittle") String tittle, @Param("text") String text, @Param("id") UUID id);

    @EntityGraph(attributePaths = "user")
    @Query(value = "select p from Publication p where p.id = :id")
    Publication getById(@Nullable @Param("id") UUID publicationId);

}
