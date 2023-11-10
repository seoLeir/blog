--liquibase formatted sql
--changeset leir:1 splitStatements:false
CREATE FUNCTION get_publication_score(publication_uuid UUID)
    RETURNS float
    LANGUAGE plpgsql
AS
$$
DECLARE
    score float8;
BEGIN
    SELECT INTO score (SELECT COUNT(pl.user_uuid)
                       FROM publications_likes pl
                       WHERE pl.publication_uuid = p.id AND pl.is_like = TRUE) * 0.5 +
                      (SELECT COUNT(pl.user_uuid)
                       FROM publications_likes pl
                       WHERE pl.publication_uuid = p.id AND pl.is_like = FALSE) * (-0.1) +
                      (SELECT COUNT(pc.id) FROM publications_comments pc WHERE pc.publication_uuid = p.id) * 0.2 +
                      (SELECT COUNT(ub.user_uuid) FROM users_bookmarks ub WHERE ub.publication_uuid = p.id) * 0.3 +
                      p.view_count * 0.1
    FROM publications p
    WHERE p.id = publication_uuid;
    RETURN score;
END;
$$;