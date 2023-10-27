--liquibase formatted sql

--changeset leir:1
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

--changeset leir:2
INSERT INTO users (id, username, email, password, info, created_at)
VALUES
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'admin', 'mirzayevmiralim28@gmail.com', '$2a$10$5zr5vwHXLkvt09L.fg.Bae/7C6id/XqCj9GcM3bV0eOHsgBIZu2py', 'Admin of website', now()),
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'test-username', 'test-email@gmail.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'Test user', now()),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'user1', 'user1@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 1 Info', '2023-10-21 10:00:00'),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'user2', 'user2@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 2 Info', '2023-10-21 11:00:00'),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'user3', 'user3@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 3 Info', '2023-10-21 12:00:00'),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'user4', 'user4@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 4 Info', '2023-10-21 13:00:00'),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'user5', 'user5@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 5 Info', '2023-10-21 14:00:00'),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'user6', 'user6@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 6 Info', '2023-10-21 15:00:00'),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'user7', 'user7@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 7 Info', '2023-10-21 16:00:00'),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'user8', 'user8@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 8 Info', '2023-10-21 17:00:00'),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'user9', 'user9@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 9 Info', '2023-10-21 18:00:00'),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'user10', 'user10@example.com', '$2a$10$1G61Rkw.NcNKabZCuzkAl.nQSBjQ1IWfQqjIUwFQvj3pJIDiWfdyK', 'User 10 Info', '2023-10-21 19:00:00');

--changeset leir:3
INSERT INTO users_roles (user_uuid, role_id)
VALUES ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 1),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 2),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 2),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 2),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 2),
       ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 2),
       ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 2),
       ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 2),
       ('874aa7a0-2a48-4e9b-85f5-301419def21a', 2),
       ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 2),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 2),
       ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 2);

--changeset leir:4
INSERT INTO publications (id, tittle, publication_text, publisher_uuid, is_published, view_count, time_to_read_in_minutes, is_draft, is_hidden, is_edited, created_date)
VALUES
    ('3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', 'The Art of Coding', 'In this publication, we delve into the world of coding and its creative aspects.', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', true, 100, 5, false, false, false, '2023-04-30 10:00:00'),
    ('0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Machine Learning Insights', 'Explore the latest trends and insights in machine learning technology.', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', true, 150, 7, false, false, false, '2023-05-25 11:00:00'),
    ('aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'The Future of Artificial Intelligence', 'Discover the potential of AI in shaping the future of technology and society.', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', true, 120, 6, false, false, false, '2022-12-15 12:00:00'),
    ('b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'Cybersecurity Best Practices', 'Learn how to protect your data and systems with effective cybersecurity practices.', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', true, 200, 8, false, false, false, '2023-01-07 13:00:00'),
    ('04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'The Power of Data Analysis', 'Uncover the significance of data analysis in making informed decisions.', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true, 180, 9, false, false, false, '2023-06-19 14:00:00'),
    ('e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Blockchain Revolution', 'Explore the potential of blockchain technology in various industries.', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', true, 220, 10, false, false, false, '2021-02-13 15:00:00'),
    ('a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'The Art of Photography', 'Discover the art and science behind capturing beautiful photographs.', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, 250, 12, false, false, false, '2022-03-21 16:00:00'),
    ('f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'The World of Space Exploration', 'Journey through the cosmos and explore the latest in space exploration.', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, 300, 14, false, false, false, '2020-11-29 17:00:00'),
    ('3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'The Art of Storytelling', 'Master the craft of storytelling and its impact on literature and media.', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', true, 270, 11, false, false, false, '2020-07-09 18:00:00'),
    ('78d0c387-77ab-4efb-b4a5-97b9999b969d', 'Understanding Quantum Computing', 'Delve into the fascinating world of quantum computing and its potential applications.', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', true, 230, 13, false, false, false, '2021-09-09 19:00:00'),
    ('41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', 'Exploring Renewable Energy', 'Learn about sustainable energy sources and their role in combatting climate change.', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', true, 200, 8, false, false, false, '2022-12-29 10:00:00'),
    ('4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'The Art of Painting', 'Explore the world of visual art and the techniques used in painting.', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', true, 150, 7, false, false, false, '2020-10-01 11:00:00'),
    ('dd4afc88-9022-471e-8190-0dda15b1fcbc', 'The Future of Robotics', 'Discover the latest advancements in robotics and their impact on industry and daily life.', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', true, 120, 6, false, false, false, '2023-08-07 12:00:00'),
    ('af918b35-c512-4934-942c-9a09d594a0cd', 'The World of Virtual Reality', 'Dive into the immersive world of virtual reality and its applications in gaming and education.', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', true, 600, 16, false, false, true, '2022-09-22 10:30:00'),
    ('0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', 'The Art of Culinary Mastery', 'Explore the world of gastronomy and the art of creating exquisite dishes.', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true, 180, 9, false, false, false, '2021-03-15 14:00:00'),
    ('8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Exploring Ancient History', 'Journey through the pages of history and explore the mysteries of ancient civilizations.', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', true, 220, 10, false, false, false, '2019-12-23 15:00:00'),
    ('a0e3e0a0-2e44-4e9b-85f9-30a119def21e', 'The Wonders of Nature', 'Discover the beauty of the natural world and the importance of conservation.', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, 250, 12, false, false, false, '2022-11-15 16:00:00'),
    ('09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Space Travel and Beyond', 'Embark on a journey to the stars and explore the future of space travel.', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, 300, 14, false, false, false, '2022-06-06 17:00:00'),
    ('3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'The Art of Music', 'Dive into the world of music and the creative process of composing and performing.', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', true, 270, 11, false, false, false, '2021-09-25 18:00:00'),
    ('8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', 'The Science of Climate Change', 'Explore the science behind climate change and the actions needed for a sustainable future.', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', true, 230, 13, false, false, false, '2020-12-21 19:00:00'),
    ('1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'The World of AI Ethics', 'Discuss the ethical considerations surrounding artificial intelligence and its impact on society.', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', true, 200, 8, false, false, false, '2022-09-12 10:00:00'),
    ('1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', 'The Art of Classical Music', 'The art of classical music encompasses a vast and rich tradition of Western music that has evolved over several centuries.', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', true, 150, 7, false, false, false, '2019-11-15 11:00:00'),
    ('a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'The Future of Healthcare', 'Discover the latest advancements in healthcare technology and its potential to revolutionize the industry.', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', true, 120, 6, false, false, false, '2022-12-01 12:00:00'),
    ('d1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'The World of Sustainable Living', 'Explore sustainable living practices and their role in protecting the environment.', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', true, 200, 8, false, false, false, '2021-06-13 13:00:00'),
    ('2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'The Art of Filmmaking', 'Dive into the world of cinema and the art of storytelling through film.', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true, 180, 9, false, false, false, '2019-07-19 14:00:00'),
    ('e1d0a2d6-7e14-4f7c-9ac3-15a0e3963ab6', 'The Art of Dance', 'Explore the beauty of dance and its significance in cultures around the world.', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', true, 220, 10, false, false, false, '2021-03-03 15:00:00'),
    ('8ec44680-42f7-4dbf-a1cb-bbff9160c679', 'The World of Philosophy', 'Delve into the depths of philosophy and the profound questions it seeks to answer.', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, 250, 12, false, false, false, '2022-04-14 16:00:00'),
    ('f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'The Future of Transportation', 'Explore the innovations in transportation and their role in shaping the future of mobility.', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, 300, 14, false, false, false, '2021-12-29 17:00:00'),
    ('3eaad095-47cf-4a3d-a697-d095ed5dc2be', 'The Art of Animation', 'Discover the magic of animation and the creative process behind bringing characters to life.', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', true, 270, 11, false, false, false, '2020-01-30 18:00:00'),
    ('b6d69615-8eac-41fb-856e-37e4c895e2e5', 'The World of War', 'In timeless wisdom on strategy and warfare continues to inspire leaders and military tacticians. This ancient text, dating back to the 5th century BC, explores the principles of conflict, diplomacy, and the art of gaining an advantage.', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc', true, 900, 13, false, false, false, '2022-09-22 19:00:00'),
    ('3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'The World of Nanotechnology', 'Uncover the potential of nanotechnology and its applications in various fields.', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', true, 230, 13, false, false, false, '2021-09-16 19:00:00');

--changeset leir:5
INSERT INTO files (name, real_name, mime_type, loaded_by, loaded_time)
VALUES
    ('5e91eacd-f43e-4f1f-bca2-fd586e3d7dfe', 'blockchain-revolution.jpg', 'image/jpeg', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '2023-10-27 12:30:50.112374'),
    ('b954542a-23ba-42f9-82c8-4092bc3583ac', 'Cybersecurity-best-practices.png', 'image/png', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '2023-10-27 12:15:00.989708'),
    ('169b6c64-6db7-4d4d-b2d3-56000adeaa77', 'exploring-ancient-history.jpg', 'image/jpeg', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '2023-10-27 12:35:41.076410'),
    ('743fc708-082a-46d3-a7f1-73ce165d600b', 'exploring-renewable-energy.png', 'image/png', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '2023-10-27 12:01:22.271263'),
    ('6a5e90ca-91fe-4826-a0cf-c46f86efa43d', 'future-of-artificial-intelligence.jpg', 'image/jpeg', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '2023-10-27 12:11:22.041412'),
    ('a3d48090-8e83-4663-be0e-4f21724dce97', 'machine-learning-insights.png', 'image/png', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '2023-10-27 12:06:51.061906'),
    ('2406ff0a-99c4-4af9-96cd-31c12aa55737', 'space-travel-and-beyond.jpg', 'image/jpeg', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '2023-10-27 12:42:03.156671'),
    ('63142b02-7171-40d0-be6d-a32838ce6f61', 'the-art-of-animation.jpg', 'image/jpeg', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2023-10-27 12:50:51.163260'),
    ('6f66da7a-dc71-43e1-83b5-adb46cf8fef7', 'the-art-of-classical-music.jpg', 'image/jpeg', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '2023-10-27 12:08:06.718589'),
    ('92352591-9710-4a90-bb67-0b073c3b0e4f', 'the-art-of-coding.jpeg', 'image/jpeg', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '2023-10-27 11:57:57.787424'),
    ('7fb783e2-3f31-4acb-a14b-c99c319f1e1b', 'the-art-of-culinary-mastery.avif', 'image/avif', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '2023-10-27 12:19:02.769054'),
    ('b9926eb1-a2f3-4c7b-a6c5-050f496afbaa', 'the-art-of-dance.jpg', 'image/jpeg', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '2023-10-27 12:35:57.145258'),
    ('273f8b45-2235-4cef-a4c8-322c1f6cacf8', 'the-art-of-filmmaking.jpg', 'image/jpeg', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '2023-10-27 12:24:09.252422'),
    ('48e625be-2142-4ebf-bc6d-48411eb8f59a', 'the-art-of-music.avif', 'image/avif', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2023-10-27 12:50:30.403354'),
    ('44e4670b-bd5c-4d88-9fbb-a1f05d328d24', 'the-art-of-painting.webp', 'image/webp', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '2023-10-27 12:07:32.758670'),
    ('2d667871-4ff3-44af-9bdd-99649475abb5', 'the-art-of-photography.jpg', 'image/jpeg', '874aa7a0-2a48-4e9b-85f5-301419def21a', '2023-10-27 12:38:14.337697'),
    ('82028429-d21d-4218-a3b2-67fb87484e39', 'the-art-of-strotytelling.webp', 'image/webp', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2023-10-27 12:49:54.916625'),
    ('23a4eefb-6a71-484e-a60d-912fbad44d76', 'the-future-of-healthcare.webp', 'image/webp', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '2023-10-27 12:12:05.289701'),
    ('cbc73aca-5224-4c95-9aa0-2d0fea174110', 'the-future-of-robotics.jpg', 'image/jpeg', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '2023-10-27 12:11:42.246554'),
    ('41a0f079-b65a-4adf-a3d5-7f90d7ad85cb', 'the-future-of-transportation.jpg', 'image/jpeg', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '2023-10-27 12:42:35.389179'),
    ('d7603be1-e83e-4e68-891c-42a98b9beec0', 'The-power-of-data-analysis.png', 'image/png', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '2023-10-27 12:18:40.284002'),
    ('0e44d5f6-d446-48a9-b1ca-d1360b44fec8', 'the-science-of-climate-change.jpg', 'image/jpeg', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '2023-10-27 12:55:38.405118'),
    ('24d33110-127c-4238-bef3-c4e2a1250ba0', 'the-wonders-of-nature.webp', 'image/webp', '874aa7a0-2a48-4e9b-85f5-301419def21a', '2023-10-27 12:38:33.728946'),
    ('8c3682cc-7637-4d57-a395-677d11d77cc8', 'the-world-of-ai-ethics.jpg', 'image/jpeg', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '2023-10-27 12:01:58.212621'),
    ('cf9ea899-ba87-446e-a542-ac74ff9866c4', 'the-world-of-nanotechnology.jpg', 'image/jpeg', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '2023-10-27 12:55:54.514129'),
    ('28bdda86-dfba-4ccd-9464-4502937bb8ad', 'the-world-of-philosophy.jpg', 'image/jpeg', '874aa7a0-2a48-4e9b-85f5-301419def21a', '2023-10-27 12:38:52.495413'),
    ('2b9c1eff-ed94-4607-af67-46ee234a6c22', 'the-world-of-space-exploring.webp', 'image/webp', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '2023-10-27 12:41:41.895297'),
    ('e60c011d-6b30-47c2-8bba-96010f944450', 'the-world-of-sustainable-living.jpg', 'image/jpeg', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '2023-10-27 12:15:28.364305'),
    ('5536bf3a-3742-46e3-8a0e-806cf9bf1b99', 'the-world-of-virtual-reality.jpg', 'image/jpeg', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '2023-10-27 12:55:06.051996'),
    ('c8c8f39b-bc06-45ba-b5c9-34b5684b76a8', 'the-world-of-war.jpg', 'image/jpeg', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '2023-10-27 11:29:32.996624'),
    ('7582acd2-c839-4410-9690-3bde608f4a19', 'understanding-of-quantum-computing.png', 'image/png', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '2023-10-27 12:54:47.716588');

--changeset leir:6
INSERT INTO publications_files (publication_uuid, file_name)
VALUES
    --admin -> The World of war
    ('b6d69615-8eac-41fb-856e-37e4c895e2e5', 'c8c8f39b-bc06-45ba-b5c9-34b5684b76a8'),

    --user1 ->
    -- The art of coding
    ('3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '92352591-9710-4a90-bb67-0b073c3b0e4f'),
    -- Exploring renewable energy
    ('41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', '743fc708-082a-46d3-a7f1-73ce165d600b'),
    -- The world of AI Ethics
    ('1e144963-5ffa-4e7f-bd74-e6c08af1583f', '8c3682cc-7637-4d57-a395-677d11d77cc8'),

    -- user2 ->
    -- Machine learning insights
    ('0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'a3d48090-8e83-4663-be0e-4f21724dce97'),
    -- The Art of Painting
    ('4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', '44e4670b-bd5c-4d88-9fbb-a1f05d328d24'),
    -- The Art of Classical Music
    ('1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '6f66da7a-dc71-43e1-83b5-adb46cf8fef7'),

    --user3 ->
    --The Future of Artificial Intelligence
    ('aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', '6a5e90ca-91fe-4826-a0cf-c46f86efa43d'),
    -- The Future of Robotics
    ('dd4afc88-9022-471e-8190-0dda15b1fcbc', 'cbc73aca-5224-4c95-9aa0-2d0fea174110'),
    -- The Future of Healthcare
    ('a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '23a4eefb-6a71-484e-a60d-912fbad44d76'),

    --user4 ->
    --Cybersecurity Best Practices
    ('b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'b954542a-23ba-42f9-82c8-4092bc3583ac'),
    -- The World of Sustainable Living
    ('d1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'e60c011d-6b30-47c2-8bba-96010f944450'),

    --user5 ->
    -- The Power of Data Analysis
    ('04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'd7603be1-e83e-4e68-891c-42a98b9beec0'),
    -- The Art of Culinary Mastery
    ('0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '7fb783e2-3f31-4acb-a14b-c99c319f1e1b'),
    -- The Art of Filmmaking
    ('2cb3c7e9-ad24-466e-8882-6a53b62f99f8', '273f8b45-2235-4cef-a4c8-322c1f6cacf8'),

    --user6 ->
    -- Blockchain revolution
    ('e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', '5e91eacd-f43e-4f1f-bca2-fd586e3d7dfe'),
    -- Exploring Ancient History
    ('8357e265-8175-492f-a7a7-7b81a5f9fb96', '169b6c64-6db7-4d4d-b2d3-56000adeaa77'),
    -- The Art of Dance
    ('e1d0a2d6-7e14-4f7c-9ac3-15a0e3963ab6', 'b9926eb1-a2f3-4c7b-a6c5-050f496afbaa'),

    --user7
    --The Art of Photography
    ('a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2d667871-4ff3-44af-9bdd-99649475abb5'),
    -- The Wonders of Nature
    ('a0e3e0a0-2e44-4e9b-85f9-30a119def21e', '24d33110-127c-4238-bef3-c4e2a1250ba0'),
    -- The World of Philosophy
    ('8ec44680-42f7-4dbf-a1cb-bbff9160c679', '28bdda86-dfba-4ccd-9464-4502937bb8ad'),

    --user8
    -- The World of Space Exploration
    ('f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2b9c1eff-ed94-4607-af67-46ee234a6c22'),
    -- Space Travel and Beyond
    ('09080b8c-2ea6-43a8-8b59-7446f56955ad', '2406ff0a-99c4-4af9-96cd-31c12aa55737'),
    -- The Future of Transportation
    ('f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '41a0f079-b65a-4adf-a3d5-7f90d7ad85cb'),

    --user9
    -- The Art of Storytelling
    ('3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '82028429-d21d-4218-a3b2-67fb87484e39'),
    -- The Art of Music
    ('3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '48e625be-2142-4ebf-bc6d-48411eb8f59a'),
    -- The Art of Animation
    ('3eaad095-47cf-4a3d-a697-d095ed5dc2be', '63142b02-7171-40d0-be6d-a32838ce6f61'),

    --user10
    -- Understanding Quantum Computing
    ('78d0c387-77ab-4efb-b4a5-97b9999b969d', '7582acd2-c839-4410-9690-3bde608f4a19'),
    -- The World of Virtual Reality
    ('af918b35-c512-4934-942c-9a09d594a0cd', '5536bf3a-3742-46e3-8a0e-806cf9bf1b99'),
    -- The Science of Climate Change
    ('8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '0e44d5f6-d446-48a9-b1ca-d1360b44fec8'),
    -- The World of Nanotechnology
    ('3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'cf9ea899-ba87-446e-a542-ac74ff9866c4');

-- 3.141592653589 - PI: admin-3, test-username-1, 1-4, 2-1, 3-5, 4-9, 5-2, 6-6, 7-5, 8-3, 9-5, 10-8
--changeset leir:7
INSERT INTO users_bookmarks (user_uuid, publication_uuid, bookmarked_date)
VALUES
    -- Admin
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-05-31'), --41
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2022-09-22'), --69
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', '2023-09-15'), --50

    -- Test-username
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-01-02'), --42

    -- User 1
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-11-15'), --67
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2022-10-10'), --63
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-02-10'), --43
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', '2023-09-20'), --50

    -- User 2
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2022-05-15'), --63

    -- User 3
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2022-04-20'), --54
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2020-07-29'), --48
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2021-06-19'), --59
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2022-03-15'), --49
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '1e144963-5ffa-4e7f-bd74-e6c08af1583f', '2023-01-10'), --60

    -- User 4
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2022-04-25'), --46
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-07-20'), --44
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2022-11-13'), --58
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'a0e3e0a0-2e44-4e9b-85f9-30a119def21e', '2022-12-30'), --56
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '8ec44680-42f7-4dbf-a1cb-bbff9160c679', '2022-05-12'), --66
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2020-06-14'), --61
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2021-06-10'), --47
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2022-07-26'), --57
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '3eaad095-47cf-4a3d-a697-d095ed5dc2be', '2021-05-19'), --68

    -- User 5
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2021-02-13'), --61
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-06-17'), --40

    -- User 6
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2021-03-13'), --59
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2022-09-13'), --46
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-08-06'), --41
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2021-08-18'), --63
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '8357e265-8175-492f-a7a7-7b81a5f9fb96', '2021-10-30'), --55
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '8ec44680-42f7-4dbf-a1cb-bbff9160c679', '2023-03-15'), --66

    -- User 7
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2022-11-07'), --57
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'dd4afc88-9022-471e-8190-0dda15b1fcbc', '2023-10-10'), --52
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-03-12'), --62
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2021-12-31'), --67
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2022-11-02'),

    -- User 8
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', '2020-12-19'), --51
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2020-12-19'), --57
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-02-26'), --62

    -- User 9
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-03-11'), --62
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2020-12-19'), --58
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', '2020-08-16'), --64
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2022-05-07'), --67
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2023-06-29'), --46

    -- User 10
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', '2019-12-12'), --64
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '1e144963-5ffa-4e7f-bd74-e6c08af1583f', '2022-11-20'), --60
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-07-26'), --42
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2020-12-19'), --57
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', '2021-11-23'), --45
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2021-10-10'), --49
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2019-12-30'), --61
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', '2022-12-31'); --50

--changeset leir:8
INSERT INTO publications_likes (user_uuid, publication_uuid, action_datetime, is_like)
VALUES
    -- Admin
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2023-10-24 10:00:00', true),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),

    --Test-username
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', '8357e265-8175-492f-a7a7-7b81a5f9fb96', '2023-10-24 10:00:00', true),
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-10-24 10:00:00', false),
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2023-10-24 10:00:00', true),

    --User 1
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '8ec44680-42f7-4dbf-a1cb-bbff9160c679', '2023-10-24 10:00:00', true),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2023-10-24 11:00:00', false),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', '2023-10-24 11:00:00', true),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', false),

    --User 2
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2023-10-24 11:00:00', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', false),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2023-10-24 11:00:00', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', false),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '1e144963-5ffa-4e7f-bd74-e6c08af1583f', '2023-10-24 11:00:00', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),

    --User 3
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'af918b35-c512-4934-942c-9a09d594a0cd', '2023-10-24 11:00:00', false),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', false),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'e1d0a2d6-7e14-4f7c-9ac3-15a0e3963ab6', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '3eaad095-47cf-4a3d-a697-d095ed5dc2be', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', true),
    ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-10-24 11:00:00', false),

    --User 4
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2023-10-24 11:00:00', true),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', true),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'dd4afc88-9022-471e-8190-0dda15b1fcbc', '2023-10-24 11:00:00', false),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'a0e3e0a0-2e44-4e9b-85f9-30a119def21e', '2023-10-24 11:00:00', true),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', true),
    ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', false),

    --User 5
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2023-10-24 11:00:00', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'e1d0a2d6-7e14-4f7c-9ac3-15a0e3963ab6', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '3eaad095-47cf-4a3d-a697-d095ed5dc2be', '2023-10-24 11:00:00', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),

    --User 6
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-10-24 11:00:00', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2023-10-24 11:00:00', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'af918b35-c512-4934-942c-9a09d594a0cd', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '8357e265-8175-492f-a7a7-7b81a5f9fb96', '2023-10-24 11:00:00', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2023-10-24 11:00:00', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', true),

    --User 7
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', false),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '1e144963-5ffa-4e7f-bd74-e6c08af1583f', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2023-10-24 11:00:00', false),

    --User 8
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'af918b35-c512-4934-942c-9a09d594a0cd', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '8357e265-8175-492f-a7a7-7b81a5f9fb96', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', false),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '8ec44680-42f7-4dbf-a1cb-bbff9160c679', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '3eaad095-47cf-4a3d-a697-d095ed5dc2be', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'd1d7a2d7-7c4e-4a2d-9d8e-10d76d2c64b2', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', false),

    --User 9
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', '2023-10-24 11:00:00', false),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'af918b35-c512-4934-942c-9a09d594a0cd', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', '2023-10-24 11:00:00', false),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', false),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'b6d69615-8eac-41fb-856e-37e4c895e2e5', '2023-10-24 11:00:00', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', true),

    --User 10
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '3e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', '2023-10-24 11:00:00', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'f8e7e8ab-66a4-4d1a-aa4f-19d02b6eae4e', '2023-10-24 11:00:00', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '78d0c387-77ab-4efb-b4a5-97b9999b969d', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '09080b8c-2ea6-43a8-8b59-7446f56955ad', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '8c4e5ff9-8c4d-4ff3-9d5c-0d4b1ca14e3e', '2023-10-24 11:00:00', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1', '2023-10-24 11:00:00', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', '2023-10-24 11:00:00', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', '2023-10-24 11:00:00', false);