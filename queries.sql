select s.*
from subscriptions s
where s.subscriber_id = (select u.id from users u where u.username = 'admin')
  and s.target_user = '2343117f-8279-4d0d-aa8c-d4a712d8848f';

select s.*
from subscriptions s
where s.target_user = (select u.id from users u where u.username = 'admin')
  and s.subscriber_id = '2343117f-8279-4d0d-aa8c-d4a712d8848f';

select pc.*
from publications_comments pc
where pc.user_uuid = (select u.id from users u where u.username = 'admin');

select *
from publications;

select pc.*
from publications_comments pc
where pc.publication_uuid = 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4';

select *
from publications_comments where parent_comment_uuid =
                                 (select pc.parent_comment_uuid
                                  from publications_comments pc
                                  where pc.user_uuid = (select u.id from users u where u.username = 'admin')
                                    and pc.publication_uuid = 'aa82a7c5-9aa5-4e2d-9863-fc69520c64c4');

select s.*
from subscriptions s
where s.target_user = (select u.id from users u where u.username = 'admin');

select u.id
from users u
where u.username = 'test-username';

explain analyze SELECT p.id, p.tittle,
                       (count(pl.user_uuid) * 0.4 + p.view_count * 0.3 + count(pc.id) * 0.3) AS score
                FROM publications p
                         left join publications_likes pl on p.id = pl.publication_uuid
                         left join publications_comments pc on p.id = pc.publication_uuid
                group by p.id
                ORDER BY score DESC;

select pc.*, count()
from publications_comments pc
         join publications_comments_likes pcl on pc.id = pcl.publication_comment_uuid
group by pc.id;

SELECT
    pc.id AS comment_id,
    pc.comment_message AS comment_text,
    SUM(CASE WHEN pcl.is_like = true THEN 1 ELSE 0 END) AS like_count,
    SUM(CASE WHEN pcl.is_like = false THEN 1 ELSE 0 END) AS dislike_count,
    (CASE WHEN exists(select 1
                      from publications_comments_likes pcl
                      where pcl.user_uuid = (select u.id from users u where u.username = 'admin')
                        and pcl.publication_comment_uuid = pc.id) THEN TRUE ELSE FALSE END)
FROM
    publications_comments AS pc
        LEFT JOIN
    publications_comments_likes AS pcl
    ON pc.id = pcl.publication_comment_uuid
-- WHERE
--         pc.created_at between current_date - '1 year'::interval and current_date
GROUP BY
    pc.id
ORDER BY
    like_count DESC;

select *
from (

SELECT p.id, p.tittle,
       p.created_date,
       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.4 +
       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 +
       p.view_count * 0.4 as score
FROM publications p
WHERE
    p.created_date between current_date - '2 years'::interval and current_date) as scores
order by scores.score;

-- POPULARITY
SELECT p.id,
        ((select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.4 +
       (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) +
       (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.3 +
       p.view_count * 0.4) as score
FROM publications p
-- WHERE
--     p.created_date between current_date - '2 years'::interval and current_date
order by score desc;

select avg(extract(epoch from p.created_date))
from publications p;




update publications set view_count = (SELECT floor(random() * (1000 - 0 + 1))::int + 0) where publisher_uuid = (select pbs.id from publications pbs order by random() limit 1);

UPDATE publications
SET view_count = floor(random() * 1001);


UPDATE publications
SET view_count = FLOOR(RANDOM() * 1001)
WHERE id IN (SELECT id FROM publications ORDER BY RANDOM() LIMIT (SELECT COUNT(*) FROM publications));;


UPDATE publications AS p
SET view_count = subquery.random_view_count
FROM (
         SELECT id, floor(random() * 1001)::int AS random_view_count
         FROM publications
     ) AS subquery
WHERE p.id = subquery.id;


SELECT
    p.id AS publication_uuid,
    p.tittle AS title,
    p.created_date,
    SUM(CASE WHEN pl.is_like = true THEN 1 ELSE 0 END) AS like_count,
    SUM(CASE WHEN pl.is_like = false THEN 1 ELSE 0 END) AS dislike_count
FROM
    publications p
        left join publications_likes pl on p.id = pl.publication_uuid
WHERE
    p.created_date between current_date - '2 years'::interval and current_date
GROUP BY
    p.id
ORDER BY
    like_count DESC;


-- The world of nano technology
-- 3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e
-- 123.9
-- likes:4 dislikes:2
-- comments: 8
-- views: 301
-- all: 1.6 + 90.3 + 2.4 = 94.3


SELECT
    p.id AS publication_uuid,
    p.tittle AS title,
    p.created_date,
    SUM(CASE WHEN pl.is_like = true THEN 1 ELSE 0 END) AS like_count,
    SUM(CASE WHEN pl.is_like = false THEN 1 ELSE 0 END) AS dislike_count
FROM
    publications p
        left join publications_likes pl on p.id = pl.publication_uuid
WHERE
--     p.created_date between current_date - '2 years'::interval and current_date
        p.id = '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e'
GROUP BY
    p.id;

SELECT
    p.id AS publication_uuid,
    p.tittle AS title,
    p.view_count,
    p.created_date,
    count(pc.id)

FROM
    publications p
        left join publications_comments pc on p.id = pc.publication_uuid
WHERE
--     p.created_date between current_date - '2 years'::interval and current_date
p.id = '3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e'
GROUP BY
    p.id;

SELECT
    EXTRACT(YEAR FROM p.created_date) AS year,
    EXTRACT(MONTH FROM p.created_date) AS month
--     AVG(p.id) AS average_post_count
FROM
    publications p
GROUP BY
    year, month
ORDER BY
    year, month;

select
    p.id,
    SUM(CASE WHEN pl.is_like = true THEN 1 ELSE 0 END) * 0.5 +
    SUM(CASE WHEN pl.is_like = false THEN 1 ELSE 0 END) * (-0.1) +
    (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 +
    (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id)* 0.2 + p.view_count * 0.1 as popularity
from publications p
left join publications_likes pl on p.id = pl.publication_uuid
group by p.id
order by p.created_date desc;

-- BY MONTH
select
    extract(MONTH from p.created_date) as month,
    count(*) as publication_count
from publications p
where extract(YEAR FROM p.created_date) = 2022
group by extract(MONTH from p.created_date)
ORDER BY month;

-- BY YEAR
select
    extract(YEAR from p.created_date) as year,
    count(*) as publication_count
from publications p
where extract(YEAR FROM p.created_date) = 2020
group by extract(YEAR from p.created_date)
ORDER BY year;

-- BY YEAR AND MONTH
select
    extract(YEAR from p.created_date) as year,
    extract(MONTH from p.created_date) as month,
    count(*) as publication_count
from publications p
group by extract(YEAR from p.created_date), month
ORDER BY year;



select *
from publications p
where lower(p.tittle) like lower('%war%') or lower(p.publication_text) like lower('wisdom');

select count(1) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = true) * 0.5 +        (select count(pl.user_uuid) from publications_likes pl where pl.publication_uuid = p.id and pl.is_like = false) * (-0.1) +        (select count(pc.id) from publications_comments pc where pc.publication_uuid = p.id) * 0.2 +        (select count(ub.user_uuid) from users_bookmarks ub where ub.publication_uuid = p.id) * 0.3 +        p.view_count * 0.1) as score FROM publications p WHERE     p.created_date between ? and ? group by p.id

select p.id               as publicationUuid,
       sum(CASE WHEN (l.publication_uuid = p.id and l.is_like = true) THEN 1 ELSE 0 END) * 0.5 +
       sum(CASE WHEN (l.publication_uuid = p.id and l.is_like = false) THEN 1 ELSE 0 END) * (-0.1) +
       count(c.id) * 0.2 +
       count(ub.user_uuid) * 0.3 +
       p.view_count * 0.1 as score
from publications p
         left join publications_likes l on p.id = l.publication_uuid
         left join publications_comments c on p.id = c.publication_uuid
         left join users_bookmarks ub on p.id = ub.publication_uuid
group by p.id
order by score desc