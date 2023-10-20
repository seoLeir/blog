--liquibase formatted sql

--changeset leir:1
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

--changeset leir:2
INSERT INTO users (id, username, email, password, info, created_at)
VALUES
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'admin', 'mirzayevmiralim28@gmail.com',
        '$2a$10$hSDLMQYZ6T/rm7HvOCWQVeHpKBTVlxa1XCMU7x.2yToV.WmCLk8km', 'Admin of this website', now()),
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'test-username', 'test-email@gmail.com',
     '$2a$10$hSDLMQYZ6T/rm7HvOCWQVeHpKBTVlxa1XCMU7x.2yToV.WmCLk8km', 'Test user', now());

--changeset leir:3
INSERT INTO users_roles (user_uuid, role_id)
VALUES ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 1),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 2);