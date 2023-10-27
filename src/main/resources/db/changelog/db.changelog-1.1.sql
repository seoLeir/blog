--liquibase formatted sql

--changeset leir:1
CREATE INDEX user_username_idx ON users (username);

CREATE INDEX subscriber_id_idx ON subscriptions (subscriber_id);

CREATE INDEX target_user_idx ON subscriptions (target_user);

CREATE INDEX publication_username_idx ON publications (publisher_uuid);

CREATE INDEX publications_likes_uuid_idx ON publications_likes (publication_uuid);

CREATE INDEX publication_file_uuid_idx ON publications_files (publication_uuid);

CREATE INDEX publication_comment_uuid_idx ON publications_comments (publication_uuid);

CREATE INDEX publication_comment_like_uuid_idx ON publications_comments_likes (publication_comment_uuid);

CREATE INDEX users_bookmarks_user_username_idx ON users_bookmarks (user_uuid);

CREATE INDEX users_bookmarks_publication_uuid_idx ON users_bookmarks (publication_uuid);