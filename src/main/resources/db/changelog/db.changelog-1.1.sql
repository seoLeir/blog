--liquibase formatted sql

--changeset leir:1
CREATE INDEX user_username_idx ON users (username);

CREATE INDEX subscriber_id_idx ON subscriptions (subscriber_id);

CREATE INDEX target_user_idx ON subscriptions (target_user);

CREATE INDEX publication_username_idx ON publications (publisher_uuid);

CREATE INDEX publication_uuid_idx ON publication_likes (publication_uuid);

CREATE INDEX publication_file_uuid_idx ON publication_files (publication_uuid);

CREATE INDEX publication_comment_uuid_idx ON publication_comments (publication_uuid);

CREATE INDEX publication_comment_like_uuid_idx ON publication_comments_likes (publication_comment_uuid);

CREATE INDEX message_files_idx ON message_files (message_uuid);

CREATE INDEX users_bookmarks_user_username_idx ON user_bookmarks (user_username);

CREATE INDEX users_bookmarks_publication_uuid_idx ON user_bookmarks (publication_uuid);