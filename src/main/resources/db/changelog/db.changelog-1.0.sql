--liquibase formatted sql

--changeset leir:1
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

--changeset leir:2
CREATE TABLE users
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP,
    modified_at TIMESTAMP
);

--changeset leir:3
CREATE TABLE publication_optional_parameters
(
    parameter_uuid UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    is_published BOOLEAN,
    is_draft BOOLEAN,
    is_hidden BOOLEAN,
    is_edited BOOLEAN
);

--changeset leir:4
CREATE TABLE publications
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    header TEXT NOT NULL,
    publication_text text NOT NULL,
    publisher_uuid UUID REFERENCES users (id),
    created_date TIMESTAMP NOT NULL,
    modified_at TIMESTAMP,
    parameters UUID REFERENCES publication_optional_parameters(parameter_uuid)
);

--changeset leir:5
CREATE TABLE user_comments
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_uuid UUID REFERENCES users(id) NOT NULL,
    publication_uuid UUID REFERENCES publications(id),
    parent_comment_uuid UUID REFERENCES user_comments(id),
    comment_message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

--changeset leir:6
CREATE TABLE publication_likes
(
    user_uuid UUID REFERENCES users(id),
    publication_uuid UUID REFERENCES publications(id),
    like_datetime TIMESTAMP NOT NULL,
    CONSTRAINT publication_likes_fk PRIMARY KEY (publication_uuid, user_uuid)
);

--changeset leir:7
CREATE TABLE comment_likes
(
    user_uuid UUID REFERENCES users(id),
    comment_uuid UUID REFERENCES user_comments (id),
    like_datetime TIMESTAMP NOT NULL,
    CONSTRAINT comments_likes_fk PRIMARY KEY (comment_uuid, user_uuid)
);

--changeset leir:8
CREATE TABLE files
(
    name UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    real_name TEXT NOT NULL,
    file_extension VARCHAR(7) NOT NULL,
    mime_type VARCHAR(64) NOT NULL,
    loaded_time TIMESTAMP NOT NULL
);

--changeset leir:9
CREATE TABLE publication_files
(
    publication_uuid UUID REFERENCES publications(id),
    file_uuid UUID REFERENCES files(name),
    CONSTRAINT publication_files_pk PRIMARY KEY (publication_uuid, file_uuid)
);

--changeset leir:10
CREATE TABLE messages_optional_parameters
(
    parameter_uuid UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    is_spam BOOLEAN,
    is_deleted BOOLEAN,
    not_delivered BOOLEAN,
    is_postponed BOOLEAN,
    is_edited BOOLEAN
);

--changeset leir:11
CREATE TABLE messages
(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_from UUID references users (id) NOT NULL,
    user_to UUID references users(id) NOT NULL,
    sent_datetime TIMESTAMP NOT NULL,
    message_body TEXT,
    is_read BOOLEAN,
    parameters UUID REFERENCES messages_optional_parameters(parameter_uuid),
    updated_at TIMESTAMP,
    parent_message UUID REFERENCES messages(id)
);

--changeset leir:12
CREATE TABLE message_files
(
    message_uuid UUID REFERENCES messages(id),
    file_uuid UUID REFERENCES files(name),
    CONSTRAINT message_files_pk PRIMARY KEY (message_uuid, file_uuid)
);

--changeset leir:13
CREATE TABLE subscriptions
(
    subscriber_id UUID REFERENCES users(id),
    follower_id UUID REFERENCES users(id),
    subscription_datetime TIMESTAMP NOT NULL,
    CONSTRAINT subscriptions_pk PRIMARY KEY (subscriber_id, follower_id)
);



