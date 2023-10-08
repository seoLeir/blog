package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.UserBookmark;
import io.seoLeir.socialmedia.entity.keys.UserBookmarksId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookmarkRepository extends JpaRepository<UserBookmark, UserBookmarksId> {
}
