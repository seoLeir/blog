package io.seoLeir.blog.repository;

import io.seoLeir.blog.dto.publication.FeedDto;
import io.seoLeir.blog.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID>,
        PagingAndSortingRepository<Publication, UUID>, JpaSpecificationExecutor<Publication> {

    @Query(value = "select new Publication(" +
            "p.id, p.title, SUBSTRING(p.text, 1, 100), p.user, p.viewCount, p.timeToReadInMinutes, p.createdDate) " +
            "from Publication p where p.id in (:ids)",
            countQuery = "select count(p.id) from Publication p")
    Page<Publication> getAllUserBookmarkedPublication(@Param("ids") List<UUID> uuids, Pageable pageable);

    @Query(value = "select count (p.id) from Publication p where p.user.id = :id")
    Long getPublicationCountByUserUuid(@Param("id") UUID userUid);

    @Query("select p.user.username from Publication p where p.id = :id")
    String getPublicationPublisherNameByPublicationUuid(@Param("id") UUID publicationUuid);

    @Query("select p from Publication p where p.id = :id")
    Optional<Publication> getPublicationById(@Param("id") UUID publicationUuid);

    @Query(value = """
     select new io.seoLeir.socialmedia.entity.Publication(
     p.id, p.title, SUBSTRING(p.text, 1, 20), p.user, p.viewCount, p.timeToReadInMinutes, p.createdDate)\s
     from Publication p where p.id = :id
     """)
    Publication getPublicationForFeeder(@Param("id") UUID publicationUuid);

    @Modifying(flushAutomatically = true)
    @Query("delete from Publication p where p.id = :id")
    void deleteById(@Nullable @Param("id") UUID id);

    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.viewCount = :newViewCount")
    void updateViewCount(@Param("newViewCount") Long newViewCount);

    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.title = :tittle, p.text = :text where p.id = :id")
    void updateTittleAndText(@Param("tittle") String tittle, @Param("text") String text, @Param("id") UUID id);


    /**
    * Accepted
    */
    @Query(value = """
            select 
                    p.id as publicationUuid, 
                    get_publication_score(p.id) as score 
                    FROM publications p 
                    WHERE 
                        p.created_date between :startDate and :endDate 
                    order by score desc
            """, nativeQuery = true)
    Page<FeedDto> getTopPublicationsByPeriod(@Param("startDate")Instant start, @Param("endDate") Instant end, Pageable pageable);

    /**
    * Accepted
    */
    @Query(value = """
            select 
                    p.id as publicationUuid, 
                    get_publication_score(p.id) as score 
                    FROM publications p 
                    WHERE 
                        p.created_date <= :startDate
                    order by score desc
            """, nativeQuery = true)
    Page<FeedDto> getTopPublicationsAllTime(@Param("startDate") Instant start, Pageable pageable);

    /**
    * Accepted
    */
    @Query(value = """
            select 
                pub.publicationUuid, 
                pub.score
            from (select 
                p.id as publicationUuid,
                p.created_date, 
                get_publication_score(p.id) as score
            from publications p) as pub
            where pub.score >= ?1
            order by pub.created_date desc """, nativeQuery = true, countQuery = """
            select count(pub.publicationUuid)
            from (select 
                p.id as publicationUuid,
                get_publication_score(p.id) as score
            from publications p) as pub
            where pub.score >= ?1
            """)
    Page<FeedDto> getNewPublicationsByRangeFilter(Integer range, Pageable pageable);

    /**
    * Accepted
    */
    @Query(value = """
            select
                p.id as publicationUuid, 
                get_publication_score(p.id) as score 
            FROM publications p 
            WHERE 
                p.publication_text like CONCAT('%', :textToSearch, '%')
            group by p.id 
            order by score desc 
    """, nativeQuery = true)
    Page<FeedDto> searchPublicationByPopularityOrder(@Param("textToSearch") String text, Pageable pageable);

    /**
    * Accepted
    */

    @Query("""
       select new io.seoLeir.socialmedia.entity.Publication(
     p.id, p.title, SUBSTRING(p.text, 1, 20), p.user, p.viewCount, p.timeToReadInMinutes, p.createdDate) 
     from Publication p where p.text like CONCAT('%', :textToSearch, '%') OR p.title like CONCAT('%', :textToSearch, '%')
     order by p.createdDate
       """)
    Page<Publication> searchPublicationByCreatedDateOrder(@Param("textToSearch") String text, Pageable pageable);


}
