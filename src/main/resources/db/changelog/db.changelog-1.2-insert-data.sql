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
INSERT INTO publications (id, title, publication_text, publisher_uuid, is_published, view_count, time_to_read_in_minutes, is_draft, is_hidden, is_edited, created_date)
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

--changeset leir:9
INSERT INTO publications_comments (id, user_uuid, publication_uuid, comment_message, created_at)
VALUES  ('7231867e-14c4-49fb-95aa-91c3158205bb', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'e1d0a2d6-7e14-4f7c-9ac3-15a0e3963ab6','Comment under post Art of dance', '2023-10-31 11:16:07.048685'),
        ('ba30f899-6fb8-451f-81ea-9de243193bb0', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by user6',
        '2023-10-31 11:16:07.048685'),
       ('d35d5977-e016-495c-97b3-ffc6513084f8', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Storytelling by user6',
        '2023-10-31 11:16:07.048685'),
       ('56631d82-0020-4f00-9bd8-086287c6ddf1', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by user6',
        '2023-10-31 11:16:07.048685'),
       ('e492cc29-6539-4fa6-80fd-b3134001fcbb', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user6',
        '2023-10-31 11:16:07.048685'),
       ('184db880-60cf-41cf-af6d-3582d7999d6a', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user6',
        '2023-10-31 11:16:07.048685'),
       ('3d5208ad-9b33-4213-b514-8eed74d31854', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user6',
        '2023-10-31 11:16:07.048685'),
       ('87530117-da9c-4dbf-bd22-16d2439e8e3c', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user6', '2023-10-31 11:16:07.048685'),
       ('c49ccf24-a330-47db-8788-2ae73ecb5a90', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user6',
        '2023-10-31 11:16:07.048685'),
       ('64f8b69a-27ec-4595-be73-279026ec0ee3', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user6',
        '2023-10-31 11:16:07.048685'),
       ('657008e2-f749-45fe-984f-6e9c4ef89483', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user6',
        '2023-10-31 11:16:07.048685'),
       ('109fcbe3-c7e6-41e0-9cc9-b23349947092', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by user6',
        '2023-10-31 11:16:07.048685'),
       ('2e5c0624-85a4-44dd-8942-2b9d17858c20', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by user9',
        '2023-10-31 11:16:07.048685'),
       ('ceaef05c-1e9c-4b3f-8518-695078ee99eb', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by user9',
        '2023-10-31 11:16:07.048685'),
       ('241550f4-ba11-4dd6-911e-0dc47a2cdb48', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user9',
        '2023-10-31 11:16:07.048685'),
       ('2e528b30-cc1d-47b0-b9dc-d2f5e90d1453', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user9',
        '2023-10-31 11:16:07.048685'),
       ('cb404ad7-8c22-4200-bbdf-899cda0bbe86', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user9',
        '2023-10-31 11:16:07.048685'),
       ('78a79290-ba1c-46f3-8bb2-7bbd6292ff75', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user9',
        '2023-10-31 11:16:07.048685'),
       ('4ea7b58c-82da-42e4-b897-2c49be1c899a', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user9', '2023-10-31 11:16:07.048685'),
       ('cf9b2a2c-bb03-4849-ac9f-98b925d02529', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user9',
        '2023-10-31 11:16:07.048685'),
       ('0b52f169-9715-40c5-aa4c-e9f1ace7f40f', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user9', '2023-10-31 11:16:07.048685'),
       ('216aaa54-d27f-4392-8515-79deb0ad6a70', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'Comment on The Art of Filmmaking by user9',
        '2023-10-31 11:16:07.048685'),
       ('e867d62d-e859-4c39-985e-8f1e9d7c02e1', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user9',
        '2023-10-31 11:16:07.048685'),
       ('0729466a-bae5-42c2-a8ee-29914310eaab', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user9',
        '2023-10-31 11:16:07.048685'),
       ('6e6a7aed-2732-4a81-8f4f-65eec155b693', '36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by user9',
        '2023-10-31 11:16:07.048685'),
       ('1d703da7-98ab-4fdd-8b94-4f45700d28e1', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by user8',
        '2023-10-31 11:16:07.048685'),
       ('44af37a3-dc87-4195-999a-ea8e91a4c0a8', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by user8',
        '2023-10-31 11:16:07.048685'),
        ('c7e7bcec-5c43-4e55-a897-0a1b9dac7dc6', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by user8',
        '2023-10-31 11:16:07.048685'),
       ('43d3602c-f4b5-41f4-a7d1-42835e287aa5', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user8',
        '2023-10-31 11:16:07.048685'),
       ('ff522e6a-9c9b-47f3-b28f-ff7d4153f658', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user8',
        '2023-10-31 11:16:07.048685'),
       ('a784e370-e441-4ca8-9ffc-5a4e34fb4b04', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user8', '2023-10-31 11:16:07.048685'),
       ('78ad9a73-7a5f-4629-8edb-7af8242470d5', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user8',
        '2023-10-31 11:16:07.048685'),
       ('39d9da16-6300-4403-ab82-12dfb3649d94', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '78d0c387-77ab-4efb-b4a5-97b9999b969d', 'Comment on Understanding Quantum Computing by user8',
        '2023-10-31 11:16:07.048685'),
       ('e45fd75c-deaf-4fdf-83d3-33485cdaf486', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user8', '2023-10-31 11:16:07.048685'),
       ('9ea83a19-1cb3-453c-b3fb-f64c37f8aef7', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'Comment on The Art of Filmmaking by user8',
        '2023-10-31 11:16:07.048685'),
       ('b3aca67b-f360-4974-92c0-db3b69638f89', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user8',
        '2023-10-31 11:16:07.048685'),
       ('fbc6ad85-34a9-427e-bbc9-354138b588f0', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user8',
        '2023-10-31 11:16:07.048685'),
       ('b3cac149-700b-43d5-9a62-298e30d55c32', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by test-username',
        '2023-10-31 11:16:07.048685'),
       ('7c117b5c-725a-4dfe-a7e2-b07e6226a3bb', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by test-username',
        '2023-10-31 11:16:07.048685'),
       ('977a390a-ad75-4dfd-98e1-3fd860e4627a', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by test-username',
        '2023-10-31 11:16:07.48685'),
       ('69333760-028e-4d14-bc31-7de0f6133f64', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by test-username',
        '2023-10-31 11:16:07.08685'),
       ('9bda8357-cc30-427e-baab-404903bf0462', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by test-username',
        '2023-10-31 11:16:07.048685'),
       ('598de47d-67c7-4414-a7ff-ef241138c324', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by test-username',
        '2023-10-31 11:16:07.48685'),
       ('eda5bb76-2da8-4e1f-b228-ba3563d7125a', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by test-username',
        '2023-10-31 11:16:07.08685'),
       ('a86cdace-0fe2-4c76-b506-d1ec06a0eb89', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by test-username',
        '2023-10-31 11:16:07.08685'),
       ('1d066829-9b98-4670-9229-8f892e9e4282', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by test-username',
        '2023-10-31 11:16:07.08685'),
       ('191555cf-6f36-4fa1-8df9-dc26724797ea', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by test-username',
        '2023-10-31 11:16:07.08685'),
       ('393d08a9-9c16-44e2-85ab-1c287a7fa70a', '2343117f-8279-4d0d-aa8c-d4a712d8848f',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by test-username',
        '2023-10-31 11:16:07.48685'),
       ('59beb31f-1799-4398-9fd7-a1120aa71ed4', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by user3',
        '2023-10-31 11:16:07.048685'),
       ('7c11ddd3-0512-4c32-b60b-f42ccd549b2e', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by user3',
        '2023-10-31 11:16:07.048685'),
       ('b78d2649-3e85-42fb-a2b7-dbe70b2b266a', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by user3',
        '2023-10-31 11:16:07.048685'),
       ('1de8f0b8-4212-4d03-8dad-3659b33c63b9', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'Comment on Cybersecurity Best Practices by user3',
        '2023-10-31 11:16:07.048685'),
       ('77896bc0-16d5-4ebd-9062-e889f023ef53', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by user3',
        '2023-10-31 11:16:07.048685'),
       ('e7985a30-63fa-4b3b-b2f4-482fad123d1b', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user3',
        '2023-10-31 11:16:07.048685'),
       ('faa65bea-2e25-4e44-9337-b93be6106a35', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user3', '2023-10-31 11:16:07.048685'),
       ('51d91f18-ff62-44ff-8d57-64efe5e363b6', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'Comment on The Art of Filmmaking by user3',
        '2023-10-31 11:16:07.048685'),
       ('840bd037-2501-4187-bfaa-e9be5ab6de8d', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user3',
        '2023-10-31 11:16:07.048685'),
       ('1da1e34f-172c-44c2-aeb0-286e500f4b42', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user7',
        '2023-10-31 11:16:07.048685'),
       ('b7aeea14-716a-401c-95a2-b68013863afe', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user7',
        '2023-10-31 11:16:07.048685'),
       ('42c413b6-98a5-4f53-adde-b0c14a4b11a4', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Storytelling by user7',
        '2023-10-31 11:16:07.048685'),
       ('bb5653c0-b80e-4b3a-9d83-cc2c929ff390', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by user7',
        '2023-10-31 11:16:07.048685'),
       ('48b477f9-5398-4348-b6c8-aedc97c648e1', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by user7',
        '2023-10-31 11:16:07.048685'),
       ('763ca613-be5d-4cfd-a824-517b8beb0ddd', '874aa7a0-2a48-4e9b-85f5-301419def21a',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user7',
        '2023-10-31 11:16:07.048685'),
       ('4a92b78a-03bc-46ab-ae0b-e1fc77cf0b4c', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by user4',
        '2023-10-31 11:16:07.048685'),
       ('345fe50f-5955-4ea9-837c-b33beab1a137', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by user4',
        '2023-10-31 11:16:07.048685'),
       ('e8742669-cccb-43d0-a9cd-1963f384cd94', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'Comment on Cybersecurity Best Practices by user4',
        '2023-10-31 11:16:07.048685'),
       ('11b11e71-f786-4dba-80f3-80cf371ca599', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user4',
        '2023-10-31 11:16:07.048685'),
       ('7c71a906-f108-4d02-9f81-57c64fbab2a2', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user4',
        '2023-10-31 11:16:07.048685'),
       ('70eec2e5-db5b-4914-a2c5-30ac801c1b91', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user4',
        '2023-10-31 11:16:07.048685'),
       ('c0103c79-df85-4261-ac2e-1abc111f989e', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user4',
        '2023-10-31 11:16:07.048685'),
       ('efa403bc-130d-4915-a634-1be3452cbb9e', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user4', '2023-10-31 11:16:07.048685'),
       ('4054e861-bf81-46ab-a267-1c26f9c0b74c', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user4',
        '2023-10-31 11:16:07.048685'),
       ('7db36ddc-a60a-4e1c-87c3-dd2ee5818a5d', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '78d0c387-77ab-4efb-b4a5-97b9999b969d', 'Comment on Understanding Quantum Computing by user4',
        '2023-10-31 11:16:07.048685'),
       ('1c0eddfa-e25a-4fc9-b4aa-e6a2cd0f3282', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user4', '2023-10-31 11:16:07.048685'),
       ('db3b31be-c388-4ce8-9a8b-e0a2500106b2', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'Comment on The Art of Filmmaking by user4',
        '2023-10-31 11:16:07.048685'),
       ('346f6822-877b-4fa3-8673-faeb6ef41684', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user4',
        '2023-10-31 11:16:07.048685'),
       ('86e25535-9b84-4168-924e-998788b66a05', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user4',
        '2023-10-31 11:16:07.048685'),
       ('b803bd3d-bd92-4775-8295-a85b0ff2eb61', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user2',
        '2023-10-31 11:16:07.048685'),
       ('05037cb6-7617-43df-9e91-d080a2bbbb30', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Storytelling by user2',
        '2023-10-31 11:16:07.048685'),
       ('7e13f865-2111-4d70-baa3-e0797517446e', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by user2',
        '2023-10-31 11:16:07.048685'),
       ('9ac07d0e-fddc-4339-9b2e-4ec2a71719d5', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by user2',
        '2023-10-31 11:16:07.048685'),
       ('ba30249e-8f19-4020-8d0f-45f7930950cf', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user2',
        '2023-10-31 11:16:07.048685'),
       ('909b8c63-bbf7-434e-9c9f-c2212e0e6279', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user2',
        '2023-10-31 11:16:07.048685'),
       ('68108f72-b930-4437-bc0b-b15f076230ed', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user2',
        '2023-10-31 11:16:07.048685'),
       ('c7439776-b03c-4a4e-a9f5-0c35eaf2ea27', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user2', '2023-10-31 11:16:07.048685'),
       ('69262689-4708-4667-b1c8-4f2f5ffb2055', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user2',
        '2023-10-31 11:16:07.048685'),
       ('236b7380-9857-40a2-8695-de3e099187a0', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user2',
        '2023-10-31 11:16:07.048685'),
       ('ad27ed90-da11-4d19-9aae-89d2dfff09b2', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by user2',
        '2023-10-31 11:16:07.048685'),
       ('1a1daa1b-128d-4537-8cd9-2be90a787ed8', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by admin',
        '2023-10-31 11:16:07.048685'),
       ('ba6fc5ec-3232-4f3a-8c8d-df88b86d5ad6', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by admin',
        '2023-10-31 11:16:07.048685'),
       ('e0af4dc6-3a23-4b6f-805d-c68f73e5cb84', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by admin',
        '2023-10-31 11:16:07.048685'),
       ('275cdb50-e298-4868-81e0-db329536e8a7', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by admin',
        '2023-10-31 11:16:07.048685'),
       ('17d7e20e-97a1-4502-bd92-f0c7422a639f', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by admin',
        '2023-10-31 11:16:07.048685'),
       ('55759677-5cad-44ae-9166-7acd1f0e3bfa', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by admin', '2023-10-31 11:16:07.048685'),
       ('22aa174e-dbfc-4285-9e1a-1f4a61125726', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by admin',
        '2023-10-31 11:16:07.048685'),
       ('b7e0d744-b5b6-4252-830c-86830382c052', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by admin',
        '2023-10-31 11:16:07.048685'),
       ('51c40436-eab6-44cf-aecb-2a5aab4dd736', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '0e7e4b2b-84e4-402d-b0f7-190d29e00a35', 'Comment on Machine Learning Insights by user5',
        '2023-10-31 11:16:07.048685'),
       ('2eed8f6e-4a60-4070-b5dc-0001f7bb1160', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by user5',
        '2023-10-31 11:16:07.048685'),
       ('d59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'Comment on Cybersecurity Best Practices by user5',
        '2023-10-31 11:16:07.048685'),
       ('9aab564d-d73c-457e-8d0a-a5d4f033db73', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user5',
        '2023-10-31 11:16:07.048685'),
       ('00f650ba-e561-459d-9d47-5dc593ec560c', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user5',
        '2023-10-31 11:16:07.048685'),
       ('fa041653-4f22-46d6-a9c3-d33d8c91601d', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user5',
        '2023-10-31 11:16:07.048685'),
       ('44615b2f-f9c4-4441-98bf-8606690ca3b8', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user5', '2023-10-31 11:16:07.048685'),
       ('6200370a-12ba-4b7d-9832-be49d8657630', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user5',
        '2023-10-31 11:16:07.048685'),
       ('9f6adae9-30dd-452b-b8c0-ad1a985d2a55', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '78d0c387-77ab-4efb-b4a5-97b9999b969d', 'Comment on Understanding Quantum Computing by user5',
        '2023-10-31 11:16:07.048685'),
       ('06738cda-f96b-4e43-acc2-f26afb4c6ac7', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user5', '2023-10-31 11:16:07.048685'),
       ('e95249c1-9a54-4878-9d4c-74ed9e72481e', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        '2cb3c7e9-ad24-466e-8882-6a53b62f99f8', 'Comment on The Art of Filmmaking by user5',
        '2023-10-31 11:16:07.048685'),
       ('b93c6b2a-b631-496e-abea-6711fc7e7513', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user5',
        '2023-10-31 11:16:07.048685'),
       ('083a085b-aa26-43b5-947f-ddc51779b656', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'f7e8e8ab-66a4-4d1a-aa4f-19d02b6eae4e', 'Comment on The Future of Transportation by user5',
        '2023-10-31 11:16:07.048685'),
       ('6858f976-94cb-4aea-a111-10343e481e19', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by user5',
        '2023-10-31 11:16:07.048685'),
       ('1d39c01e-a628-4468-afd9-447bda6255af', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        'e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6', 'Comment on Blockchain Revolution by user10',
        '2023-10-31 11:16:07.048685'),
       ('0f8291ae-2bc3-4168-b573-92becc0dc6e0', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by user10',
        '2023-10-31 11:16:07.048685'),
       ('7a311412-bf94-4c24-bdd9-06a353c92c56', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user10',
        '2023-10-31 11:16:07.048685'),
       ('7801007b-f31c-48d9-bbe2-e9dad8cda175', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user10',
        '2023-10-31 11:16:07.048685'),
       ('2aea0fca-0375-4e7c-9fa7-28f3eb2d77e6', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '3e4a9a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Storytelling by user10',
        '2023-10-31 11:16:07.048685'),
       ('e027bc4d-d897-46cd-9c71-81bc0b28b9b7', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by user10',
        '2023-10-31 11:16:07.048685'),
       ('d3c6d267-4568-442b-a372-9e50a69d6f58', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '8357e265-8175-492f-a7a7-7b81a5f9fb96', 'Comment on Exploring Ancient History by user10',
        '2023-10-31 11:16:07.048685'),
       ('3b367f3d-31d8-4024-be5d-f8ef4bc720e0', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        'af918b35-c512-4934-942c-9a09d594a0cd', 'Comment on The World of Virtual Reality by user10',
        '2023-10-31 11:16:07.048685'),
       ('81ceb934-ac21-434c-8437-61b9f31ed69c', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user10',
        '2023-10-31 11:16:07.048685'),
       ('9e0ab656-1a9f-4041-8d52-ed3fec8881fa', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '78d0c387-77ab-4efb-b4a5-97b9999b969d', 'Comment on Understanding Quantum Computing by user10',
        '2023-10-31 11:16:07.048685'),
       ('5346cedb-b582-4d53-81b6-25fe28a7d3ea', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user10', '2023-10-31 11:16:07.048685'),
       ('38ab5869-1375-411d-8f08-beaab48dd4a1', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user10',
        '2023-10-31 11:16:07.048685'),
       ('737427eb-6978-470c-80ad-9bb0870d57c4', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '04a1a8e9-8a4f-4f8d-8e16-5d3e4c9f65f9', 'Comment on The Power of Data Analysis by user1',
        '2023-10-31 11:16:07.048685'),
       ('88a1ded5-c26c-4f6d-9114-58a89b5d3d29', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'b0f2a3d7-7c4e-4a2d-9d8e-10d76d2c64b2', 'Comment on Cybersecurity Best Practices by user1',
        '2023-10-31 11:16:07.048685'),
       ('684eac56-6b6d-4a50-9f7c-30a5e45ca25b', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '09080b8c-2ea6-43a8-8b59-7446f56955ad', 'Comment on Space Travel and Beyond by user1',
        '2023-10-31 11:16:07.048685'),
       ('8ed375f9-4608-4332-bf0e-d0fa35099405', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '1e144963-5ffa-4e7f-bd74-e6c08af1583f', 'Comment on The World of AI Ethics by user1',
        '2023-10-31 11:16:07.048685'),
       ('2ac06a01-47c2-4349-a188-c8a3b875ba4b', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'a2c8a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Healthcare by user1',
        '2023-10-31 11:16:07.048685'),
       ('43e71964-06b3-4c16-a272-a426cd36f3e1', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'dd4afc88-9022-471e-8190-0dda15b1fcbc', 'Comment on The Future of Robotics by user1',
        '2023-10-31 11:16:07.048685'),
       ('ad22df62-55ef-4705-8e8e-6bbbc0e5c239', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e', 'Comment on The World of Nanotechnology by user1',
        '2023-10-31 11:16:07.048685'),
       ('33504baf-d27d-4221-9508-c7bc03c81d49', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'b6d69615-8eac-41fb-856e-37e4c895e2e5', 'Comment on The World of War by user1', '2023-10-31 11:16:07.048685'),
       ('d3b54811-baa0-4c8f-99dc-2226f5c0cbb8', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '4a3b2e4e-ca5c-4533-a3ea-a394fb1fb90e', 'Comment on The Art of Painting by user1',
        '2023-10-31 11:16:07.048685'),
       ('cce81437-902f-4877-bfe0-149b0dfb93bc', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        '3e4a1a7d-1e09-4ef7-bc89-86a9bb8dbf9d', 'Comment on The Art of Music by user1', '2023-10-31 11:16:07.048685'),
       ('e08afbf6-cb9d-4bd7-bcc2-dcfaaf06b1b6', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'a0e7e0a0-2e44-4e9b-85f9-30a119def21e', 'Comment on The Art of Photography by user1',
        '2023-10-31 11:16:07.048685'),
       ('74455341-bca6-48d1-8950-22c7e71f3365', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911',
        'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Comment on The Future of Artificial Intelligence by user1',
        '2023-10-31 11:16:07.048685'),
        ('b8c8bdb7-afed-429a-843d-e6a773b8a6c3', 'b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', 'The art of culinary mastery and comment by user1', '2023-10-31 11:16:07.048685'),
        ('0c6a594d-36b1-48cd-8db9-5b9794604416', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '0d7b4a9b-8a4f-4f8d-8e16-5d3e4c9f65f9', 'The art of culinary mastery and comment by user10', '2023-10-31 11:16:07.048685'),
        ('8b934389-4961-47ea-a0f0-14252faaeb20', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4', 'Great post', '2023-10-31 11:16:07.048685');

--changeset leir:10
INSERT INTO publications_comments_likes (user_uuid, publication_comment_uuid, action_datetime, is_like)
VALUES
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'd3b54811-baa0-4c8f-99dc-2226f5c0cbb8', '2023-10-31 13:18:20.374397', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'd3b54811-baa0-4c8f-99dc-2226f5c0cbb8', '2023-10-31 13:18:20.374397', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', true),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', false),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '42c413b6-98a5-4f53-adde-b0c14a4b11a4', '2023-10-31 13:18:20.374397', false),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'e867d62d-e859-4c39-985e-8f1e9d7c02e1', '2023-10-31 13:18:20.374397', false),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'e867d62d-e859-4c39-985e-8f1e9d7c02e1', '2023-10-31 13:18:20.374397', false),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'e867d62d-e859-4c39-985e-8f1e9d7c02e1', '2023-10-31 13:18:20.374397', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', false),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', true),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '7231867e-14c4-49fb-95aa-91c3158205bb', '2023-10-31 13:18:20.374397', false),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'b8c8bdb7-afed-429a-843d-e6a773b8a6c3', '2023-10-31 13:18:20.374397', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'b8c8bdb7-afed-429a-843d-e6a773b8a6c3', '2023-10-31 13:18:20.374397', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'b8c8bdb7-afed-429a-843d-e6a773b8a6c3', '2023-10-31 13:18:20.374397', false),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'b8c8bdb7-afed-429a-843d-e6a773b8a6c3', '2023-10-31 13:18:20.374397', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', true),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', false),
    ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', false),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', false),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', true),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'b3cac149-700b-43d5-9a62-298e30d55c32', '2023-10-31 13:18:20.374397', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '11b11e71-f786-4dba-80f3-80cf371ca599', '2023-10-31 13:18:20.374397', false),
    ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '11b11e71-f786-4dba-80f3-80cf371ca599', '2023-10-31 13:18:20.374397', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '11b11e71-f786-4dba-80f3-80cf371ca599', '2023-10-31 13:18:20.374397', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '11b11e71-f786-4dba-80f3-80cf371ca599', '2023-10-31 13:18:20.374397', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '0c6a594d-36b1-48cd-8db9-5b9794604416', '2023-10-31 13:18:20.374397', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '0c6a594d-36b1-48cd-8db9-5b9794604416', '2023-10-31 13:18:20.374397', false),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '7e13f865-2111-4d70-baa3-e0797517446e', '2023-10-31 13:18:20.374397', false),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '7e13f865-2111-4d70-baa3-e0797517446e', '2023-10-31 13:18:20.374397', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '7e13f865-2111-4d70-baa3-e0797517446e', '2023-10-31 13:18:20.374397', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '7e13f865-2111-4d70-baa3-e0797517446e', '2023-10-31 13:18:20.374397', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '7e13f865-2111-4d70-baa3-e0797517446e', '2023-10-31 13:18:20.374397', true),
    ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'bb5653c0-b80e-4b3a-9d83-cc2c929ff390', '2023-10-31 13:18:20.374397', true),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', 'd59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '2023-10-31 13:18:20.374397', false),
    ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'd59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '2023-10-31 13:18:20.374397', false),
    ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'd59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '2023-10-31 13:18:20.374397', true),
    ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'd59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '2023-10-31 13:18:20.374397', true),
    ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'd59cf130-cf4e-4710-bbca-ca3cc46e5cf1', '2023-10-31 13:18:20.374397', true),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', 'e45fd75c-deaf-4fdf-83d3-33485cdaf486', '2023-10-31 13:18:20.374397', true),
    ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'e45fd75c-deaf-4fdf-83d3-33485cdaf486', '2023-10-31 13:18:20.374397', false),
    ('874aa7a0-2a48-4e9b-85f5-301419def21a', '8b934389-4961-47ea-a0f0-14252faaeb20', '2023-10-31 13:18:20.374397', false),
    ('71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', '8b934389-4961-47ea-a0f0-14252faaeb20', '2023-10-31 13:18:20.374397', false);

--changeset leir:11
INSERT INTO subscriptions (subscriber_id, target_user, is_mutual, subscription_datetime)
VALUES ('d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', '2343117f-8279-4d0d-aa8c-d4a712d8848f', false,'2023-10-31 13:36:42.490925'),
       ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true,'2023-10-31 13:36:42.490925'),
       ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', true,'2023-10-31 13:36:42.490925'),
       ('a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true,'2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', '2343117f-8279-4d0d-aa8c-d4a712d8848f', false, '2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', '874aa7a0-2a48-4e9b-85f5-301419def21a', false, '2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true, '2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', false, '2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', true, '2023-10-31 13:36:42.490925'),
       ('2343117f-8279-4d0d-aa8c-d4a712d8848f', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, '2023-10-31 13:36:42.490925'),
       ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true, '2023-10-31 13:36:42.490925'),
       ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', '874aa7a0-2a48-4e9b-85f5-301419def21a', false, '2023-10-31 13:36:42.490925'),
       ('5df3b797-b1cc-43c0-942a-06cdd30bdbfc', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', false, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', true, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', false, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', false, '2023-10-31 13:36:42.490925'),
       ('4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', false, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', false, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', true, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', true, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc', false, '2023-10-31 13:36:42.490925'),
       ('cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', false, '2023-10-31 13:36:42.490925'),
       ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '874aa7a0-2a48-4e9b-85f5-301419def21a', true, '2023-10-31 13:36:42.490925'),
       ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', 'a871dbd6-7f14-467c-9ac2-d5a0e3963ab2', true, '2023-10-31 13:36:42.490925'),
       ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', false, '2023-10-31 13:36:42.490925'),
       ('8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', '5df3b797-b1cc-43c0-942a-06cdd30bdbfc', false, '2023-10-31 13:36:42.490925'),
       ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'd0d8a85d-5e3b-4fc4-a2ec-0b6972440a42', true, '2023-10-31 13:36:42.490925'),
       ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', '8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36', false, '2023-10-31 13:36:42.490925'),
       ('f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '874aa7a0-2a48-4e9b-85f5-301419def21a', false, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', false, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', true, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', '4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc', true, '2023-10-31 13:36:42.490925'),
       ('b1c7a27b-7a8b-4a39-9d1c-dbf1e3958911', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', true, '2023-10-31 13:36:42.490925'),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '2343117f-8279-4d0d-aa8c-d4a712d8848f', true, '2023-10-31 13:36:42.490925'),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '874aa7a0-2a48-4e9b-85f5-301419def21a', false, '2023-10-31 13:36:42.490925'),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', '71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3', true,'2023-10-31 13:36:42.490925'),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'cfab9d17-5342-4ca5-b0b8-2e1c15b70b21', false,'2023-10-31 13:36:42.490925'),
       ('36a9a74d-1c09-4ef7-bc89-865bb8dbf9cd', 'f8374eab-66a4-4daa-aa4f-e9d02b6eae4b', false,'2023-10-31 13:36:42.490925');