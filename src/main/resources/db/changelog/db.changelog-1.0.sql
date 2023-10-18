--liquibase formatted sql

--changeset leir:1
CREATE TABLE users
(
    id UUID PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role varchar(16) default 'USER',
    info text,
    created_at TIMESTAMP,
    modified_at TIMESTAMP
);

--changeset leir:2
CREATE TABLE publications
(
    id UUID PRIMARY KEY,
    tittle TEXT NOT NULL,
    publication_text text NOT NULL,
    publisher_username VARCHAR(32) REFERENCES users (username),
    is_published BOOLEAN default true,
    view_count bigint default 0,
    time_to_read_in_minutes int not null,
    is_draft BOOLEAN default false,
    is_hidden BOOLEAN default false,
    is_edited BOOLEAN default false,
    created_date TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
);

--changeset leir:3
CREATE TABLE user_bookmarks
(
    user_username VARCHAR(32) REFERENCES users(username),
    publication_uuid UUID REFERENCES publications(id),
    bookmarked_date DATE NOT NULL,
    CONSTRAINT users_bookmarks_pk PRIMARY KEY (user_username, publication_uuid)
);

--changeset leir:4
CREATE TABLE publication_comments
(
    id UUID PRIMARY KEY,
    user_uuid UUID REFERENCES users(id) NOT NULL,
    publication_uuid UUID REFERENCES publications(id),
    parent_comment_uuid UUID REFERENCES publication_comments(id),
    comment_message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

--changeset leir:5
CREATE TABLE publication_likes
(
    user_uuid UUID REFERENCES users(id),
    publication_uuid UUID REFERENCES publications(id),
    like_datetime TIMESTAMP NOT NULL,
    CONSTRAINT publication_likes_fk PRIMARY KEY (publication_uuid, user_uuid)
);

--changeset leir:6
CREATE TABLE publication_comments_likes
(
    user_uuid UUID REFERENCES users(id),
    publication_comment_uuid UUID REFERENCES publication_comments (id),
    like_datetime TIMESTAMP NOT NULL,
    CONSTRAINT comments_likes_fk PRIMARY KEY (publication_comment_uuid, user_uuid)
);

--changeset leir:7
CREATE TABLE files
(
    name UUID PRIMARY KEY,
    real_name TEXT NOT NULL,
    mime_type VARCHAR(64) NOT NULL,
    loaded_by UUID references users(id),
    loaded_time TIMESTAMP NOT NULL
);

--changeset leir:8
CREATE TABLE publication_files
(
    publication_uuid UUID REFERENCES publications(id),
    file_name UUID REFERENCES files(name),
    CONSTRAINT publication_files_pk PRIMARY KEY (publication_uuid, file_name)
);

--changeset leir:9
CREATE TABLE messages
(
    id UUID PRIMARY KEY,
    user_from UUID references users (id) NOT NULL,
    user_to UUID references users(id) NOT NULL,
    sent_datetime TIMESTAMP NOT NULL,
    message_body TEXT,
    is_read BOOLEAN default false,
    is_spam BOOLEAN default false,
    is_deleted BOOLEAN default false,
    not_delivered BOOLEAN default false,
    is_postponed BOOLEAN default false,
    is_edited BOOLEAN default false,
    updated_at TIMESTAMP,
    parent_message UUID REFERENCES messages(id)
);

--changeset leir:10
CREATE TABLE message_files
(
    message_uuid UUID REFERENCES messages(id),
    file_name UUID REFERENCES files(name),
    CONSTRAINT message_files_pk PRIMARY KEY (message_uuid, file_name)
);

--changeset leir:11
CREATE TABLE subscriptions
(
    subscriber_id UUID REFERENCES users(id),
    target_user UUID REFERENCES users(id),
    is_mutual BOOLEAN,
    subscription_datetime TIMESTAMP NOT NULL,
    CONSTRAINT subscriptions_pk PRIMARY KEY (subscriber_id, target_user)
);



