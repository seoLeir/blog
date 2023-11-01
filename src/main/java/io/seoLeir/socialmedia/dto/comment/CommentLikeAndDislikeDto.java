package io.seoLeir.socialmedia.dto.comment;

import java.util.UUID;

public record CommentLikeAndDislikeDto(UUID commmentUuid,
                                       Long likes,
                                       Long dislikes) {
}
