package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.UserBookmark;
import io.seoLeir.socialmedia.entity.keys.UserBookmarksId;
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

    @Query("select ub.user from UserBookmark ub where ub.id.publicationUuid = :uuid")
    List<String> getAllUsernameByPublicationUuid(@Param("uuid") UUID publicationUuid);

    /*@Query("select count (ub.id.publicationUuid) from UserBookmark ub where ub.id.userUsername = :username")
    long getUserAllBookmarkedPublicationsCount(@Param("username") String username);*/

}
