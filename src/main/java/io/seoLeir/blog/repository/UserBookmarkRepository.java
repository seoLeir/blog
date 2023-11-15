package io.seoLeir.blog.repository;

import io.seoLeir.blog.entity.UserBookmark;
import io.seoLeir.blog.entity.keys.UserBookmarksId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface UserBookmarkRepository extends JpaRepository<UserBookmark, UserBookmarksId>,
        PagingAndSortingRepository<UserBookmark, UserBookmarksId> {

    @Query("select ub.id.publicationUuid from UserBookmark ub where ub.id.userUuid = :userUuid " +
            "order by ub.bookmarkedDate desc")
    List<UUID> getAllPublicationsByUserUsername(@Param("userUuid") UUID userUuid);

    @Query("select ub.user.username from UserBookmark ub where ub.publication.id = :id order by ub.bookmarkedDate desc ")
    Page<String>getPublicationBookmarkedUsernames(@Param("id") UUID publicationUuid, Pageable pageable);

    @Query("select count(ub.id.userUuid) from UserBookmark ub where ub.id.publicationUuid = :id")
    Long getPublicationBookmarksCount(@Param("id") UUID publicationUuid);

    @Query("select count(ub.id.publicationUuid) from UserBookmark ub where ub.id.userUuid = " +
            "(select u.id from User u where u.username = :username)")
    Long getUserBookmarkedPublicationsCount(@Param("username") String username);

    @Query("select exists(select 1 from UserBookmark ub " +
            "where ub.id.userUuid = :userId " +
            "and ub.id.publicationUuid = :publicationId)")
    boolean isUserBookmarkedPublication(@Param("userId") UUID userUuid, @Param("publicationId") UUID publicationUuid);
}
