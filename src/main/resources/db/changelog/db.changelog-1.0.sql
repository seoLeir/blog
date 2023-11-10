--liquibase formatted sql

--changeset leir:1
CREATE TABLE roles
(
    id SMALLINT PRIMARY KEY,
    name VARCHAR(50)
);

--changeset leir:2
CREATE TABLE users
(
    id UUID PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    info text,
    created_at TIMESTAMP,
    modified_at TIMESTAMP
);

--changeset leir:3
CREATE TABLE users_roles
(
    user_uuid UUID REFERENCES users(id) ON DELETE CASCADE,
    role_id SMALLINT REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT users_roles_pk PRIMARY KEY (user_uuid, role_id)
);

--changeset leir:4
CREATE TABLE publications
(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    publication_text text NOT NULL,
    publisher_uuid UUID REFERENCES users (id) ON DELETE CASCADE,
    is_published BOOLEAN default true,
    view_count BIGINT default 0,
    time_to_read_in_minutes INT not null,
    is_draft BOOLEAN default false,
    is_hidden BOOLEAN default false,
    is_edited BOOLEAN default false,
    created_date TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
);

--changeset leir:5
CREATE TABLE users_bookmarks
(
    user_uuid UUID REFERENCES users(id) ON DELETE CASCADE,
    publication_uuid UUID REFERENCES publications(id) ON DELETE CASCADE,
    bookmarked_date DATE NOT NULL,
    CONSTRAINT users_bookmarks_pk PRIMARY KEY (user_uuid, publication_uuid)
);

--changeset leir:6
CREATE TABLE publications_comments
(
    id UUID PRIMARY KEY,
    user_uuid UUID REFERENCES users(id) ON DELETE CASCADE NOT NULL,
    publication_uuid UUID REFERENCES publications(id) ON DELETE CASCADE,
    parent_comment_uuid UUID REFERENCES publications_comments(id),
    comment_message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

--changeset leir:7
CREATE TABLE publications_likes
(
    user_uuid UUID REFERENCES users(id) ON DELETE CASCADE,
    publication_uuid UUID REFERENCES publications(id) ON DELETE CASCADE,
    action_datetime TIMESTAMP NOT NULL,
    is_like BOOLEAN NOT NULL,
    CONSTRAINT publication_likes_fk PRIMARY KEY (publication_uuid, user_uuid)
);

--changeset leir:8
CREATE TABLE publications_comments_likes
(
    user_uuid UUID REFERENCES users(id) ON DELETE CASCADE,
    publication_comment_uuid UUID REFERENCES publications_comments (id) ON DELETE CASCADE,
    action_datetime TIMESTAMP NOT NULL,
    is_like BOOLEAN NOT NULL,
    CONSTRAINT comments_likes_fk PRIMARY KEY (publication_comment_uuid, user_uuid)
);

--changeset leir:9
CREATE TABLE files
(
    name UUID PRIMARY KEY,
    real_name TEXT NOT NULL,
    mime_type VARCHAR(64) NOT NULL,
    loaded_by UUID references users(id) ON DELETE CASCADE,
    loaded_time TIMESTAMP NOT NULL
);

--changeset leir:10
CREATE TABLE publications_files
(
    publication_uuid UUID REFERENCES publications(id) ON DELETE CASCADE,
    file_name UUID REFERENCES files(name) ON DELETE CASCADE,
    CONSTRAINT publication_files_pk PRIMARY KEY (publication_uuid, file_name)
);

--changeset leir:11
CREATE TABLE subscriptions
(
    subscriber_id UUID REFERENCES users(id) ON DELETE CASCADE,
    target_user UUID REFERENCES users(id) ON DELETE CASCADE,
    is_mutual BOOLEAN,
    subscription_datetime TIMESTAMP NOT NULL,
    CONSTRAINT subscriptions_pk PRIMARY KEY (subscriber_id, target_user)
);



