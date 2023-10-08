insert into users (id, username, email, password)
values ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', 'seoLeir', 'mirzayevmiralim28@gmail.com', '123'),
       ('e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', 'david', 'davidbilbuliyan', '321');

insert into publications (id, tittle, publication_text, publisher_username, view_count, created_date)
values ('66adccd2-94d2-498d-978f-7e8e0b66bc4b', 'Nature in the past 50 years', 'Climate was changed into a bad sight in last 100 years', 'seoLeir', 45, now()),
       ('e277d488-fefa-40f4-bf7f-b4dacddcd00a', 'World war 2', 'During WW2 80 millions of people died', 'david', 64, now());

insert into subscriptions (subscriber_id, target_user, is_mutual, subscription_datetime)
values ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', 'e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', true, now()),
       ('e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', 'e8fa8044-50ee-42b1-a85e-ef992ad56c26', true, now());

insert into files (name, real_name, mime_type, loaded_time, loaded_by)
values ('e563ff6b-6290-4ad7-8d6d-d0ba11e37df5', 'real-photo-name', 'image/jpeg', now(), 'e8fa8044-50ee-42b1-a85e-ef992ad56c26'),
       ('61d79add-2e3a-4f06-abc7-49c606d8a82b', 'real-video-name', 'video/mp4', now(), 'e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7');

insert into publication_files (publication_uuid, file_uuid)
values ('66adccd2-94d2-498d-978f-7e8e0b66bc4b', 'e563ff6b-6290-4ad7-8d6d-d0ba11e37df5'),
       ('e277d488-fefa-40f4-bf7f-b4dacddcd00a', '61d79add-2e3a-4f06-abc7-49c606d8a82b');

insert into publication_likes (user_uuid, publication_uuid, like_datetime)
values ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', '66adccd2-94d2-498d-978f-7e8e0b66bc4b', now()),
       ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', 'e277d488-fefa-40f4-bf7f-b4dacddcd00a', now());

insert into publication_comments (id, user_uuid, publication_uuid, parent_comment_uuid, comment_message, created_at)
values ('7fcfc797-c148-4b11-b918-6fd5ee0cce77', 'e8fa8044-50ee-42b1-a85e-ef992ad56c26', '66adccd2-94d2-498d-978f-7e8e0b66bc4b', null, 'Self-publicationComment by leir seoLeir', now()),
       ('5513b18f-9607-4c99-bbb9-ad72863b3fb9', 'e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', '66adccd2-94d2-498d-978f-7e8e0b66bc4b', '7fcfc797-c148-4b11-b918-6fd5ee0cce77', 'Comment to seoLeir publicationComment by david', now());

insert into publication_comments_likes (user_uuid, publication_comment_uuid, like_datetime)
values ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', '7fcfc797-c148-4b11-b918-6fd5ee0cce77', now()),
       ('e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', '7fcfc797-c148-4b11-b918-6fd5ee0cce77', now()),
       ('e8fa8044-50ee-42b1-a85e-ef992ad56c26', '5513b18f-9607-4c99-bbb9-ad72863b3fb9', now());

insert into messages (id, user_from, user_to, sent_datetime, message_body, is_read, is_edited)
values ('dc47c172-a98d-4900-a88d-574d8435a114',
        'e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7',
        'e8fa8044-50ee-42b1-a85e-ef992ad56c26', now(),
        'message from david to seoLeir', true, false);
insert into messages (id, user_from, user_to, sent_datetime, message_body, is_read, is_edited, updated_at, parent_message)
values ('154121a9-16ed-4296-bc59-3b868699ff6c',
        'e8fa8044-50ee-42b1-a85e-ef992ad56c26',
        'e7d90ffe-1e27-49f2-8556-c7cda2f1e4f7', now(),
        'message from seoLeir to david', false, true, now(), 'dc47c172-a98d-4900-a88d-574d8435a114');

insert into message_files (message_uuid, file_uuid)
values ('dc47c172-a98d-4900-a88d-574d8435a114', '61d79add-2e3a-4f06-abc7-49c606d8a82b'),
       ('154121a9-16ed-4296-bc59-3b868699ff6c', 'e563ff6b-6290-4ad7-8d6d-d0ba11e37df5');

insert into user_bookmarks (user_username, publication_uuid, bookmarked_date)
values ('seoLeir', '66adccd2-94d2-498d-978f-7e8e0b66bc4b', '2022-12-12'),
       ('seoLeir', 'e277d488-fefa-40f4-bf7f-b4dacddcd00a', '2023-10-10'),
       ('david', '66adccd2-94d2-498d-978f-7e8e0b66bc4b', now()::date);