
-- PUBLICATION
-- certain publication with page by id
select *
from publications p where p.id = ''
limit 20 offset 0;

-- all user's publication with page
select *
from publications p where p.publisher_username = ''
limit 20 offset 0;

-- all user's publication with filter by: newest, oldest, view_count ASC | DESC  with page
-- filter by default uses newest
select *
from publications p
order by 'newest|oldest|view_count'desc;

-- all user's publication with order by filter: newest, oldest, view_count ASC | DESC with page
-- but also with search filter - searching by a word in publication title
select *
from publications p
where lower(p.tittle) like lower('word_to_search_in_tittle')
order by 'newest|oldest|view_count' desc
limit 20 offset 0;

-- user's all publication with order by filter:  newest, oldest, view_count ASC | DESC with page
select *
from publications p
where p.publisher_username = 'username'
order by 'newest|oldest|view_count' desc
limit 20 offset 0;

-- user's all publication with order by filter:  newest, oldest, view_count ASC | DESC with page
-- but also with search filter within user's publications
select *
from publications p
where p.publisher_username = 'username' and
      lower(p.tittle) like lower('word_to_search_in_tittle')
order by 'newest|oldest|view_count' desc
limit 20 offset 0;








