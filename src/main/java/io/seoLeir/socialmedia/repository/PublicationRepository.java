package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.publication.FeedDto;
import io.seoLeir.socialmedia.entity.Publication;
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
            "p.id, p.tittle, SUBSTRING(p.text, 1, 100), p.user, p.viewCount, p.timeToReadInMinutes, p.createdDate) " +
            "from Publication p where p.id in (:ids)",
            countQuery = "select count(p.id) from Publication p")
    Page<Publication> getAllUserBookmarkedPublication(@Param("ids") List<UUID> uuids, Pageable pageable);

    @Query(value = "select count (p.id) from Publication p where p.user.id = :id")
    Long getPublicationCountByUserUuid(@Param("id") UUID userUid);

    @Query("select p.user.username from Publication p where p.id = :id")
    String getPublicationPublisherNameByPublicationUuid(@Param("id") UUID publicationUuid);

    @Query("select p from Publication p where p.id = :id")
    Optional<Publication> getPublicationById(@Param("id") UUID publicationUuid);

    @Modifying(flushAutomatically = true)
    @Query("delete from Publication p where p.id = :id")
    void deleteById(@Nullable @Param("id") UUID id);

    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.viewCount = :newViewCount")
    void updateViewCount(@Param("newViewCount") Long newViewCount);

    @Modifying(flushAutomatically = true)
    @Query("update Publication p set p.tittle = :tittle, p.text = :text where p.id = :id")
    void updateTittleAndText(@Param("tittle") String tittle, @Param("text") String text, @Param("id") UUID id);

    @Query(value = "select " +
            "p.id as publicationUuid, " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.5 + " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) + " +
            "       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 + " +
            "       (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 + " +
            "       p.view_count * 0.1 as score " +
            "FROM publications p " +
            "WHERE " +
            "    p.created_date between :startDate and :endDate " +
            "order by score desc", nativeQuery = true)
    Page<FeedDto> getTopPublicationsByPeriod(@Param("startDate")Instant start, @Param("endDate") Instant end, Pageable pageable);


    @Query(value = """
            select p.id as publicationUuid,
                sum(CASE WHEN (l.publication_uuid = p.id and l.is_like = true) THEN 1 ELSE 0 END) * 0.5 +
                sum(CASE WHEN (l.publication_uuid = p.id and l.is_like = false) THEN 1 ELSE 0 END) * -0.1 +
                (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 +
                (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 +
                p.view_count * 0.1 as score
            from publications p
                left join publications_likes l on p.id = l.publication_uuid
            group by p.id
            order by score desc
            """, nativeQuery = true)
    Page<FeedDto> getTopPublicationsAllTime(Pageable pageable);

    @Query(value = "select " +
            "p.id as publicationUuid, " +
            "       ((select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.5 + " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) + " +
            "       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 + " +
            "       (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 + " +
            "       p.view_count * 0.1) as score " +
            "FROM publications p " +
            "WHERE score >= :range " +
            "order by p.created_date desc", nativeQuery = true)
    Page<FeedDto> getNewPublicationsByRangeFilter(@Param("range") Integer range, Pageable pageable);

    @Query(value = "select " +
            "p.id as publicationUuid, " +
            "       ((select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.5 + " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) + " +
            "       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 + " +
            "       (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 + " +
            "       p.view_count * 0.1) as score " +
            "FROM publications p " +
            "order by p.created_date desc", nativeQuery = true)
    Page<FeedDto> getNewPublicationsDefaultFeeder(Pageable pageable);

    @Query(value = "select " +
            "p.id as publicationUuid, " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.5 + " +
            "       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) + " +
            "       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 + " +
            "       (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 + " +
            "       p.view_count * 0.1 as score " +
            "FROM publications p " +
            "WHERE " +
            "    p.publication_text like CONCAT('%', :searchText, '%') " +
            "group by p.id " +
            "order by score desc ", nativeQuery = true)
    Page<FeedDto> searchPublicationByPopularityOrder(@Param("searchText") String text, Pageable pageable);

    @Query("select p from Publication p where p.text like :textToSearch order by p.createdDate desc")
    Page<Publication> searchPublicationByCreatedDateOrder(@Param("textToSearch") String text, Pageable pageable);




}
