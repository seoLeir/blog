package io.seoLeir.blog.repository;

import io.seoLeir.blog.AbstractRepositoryTestBase;
import io.seoLeir.blog.dto.comment.CommentLikeAndDislikeDto;
import io.seoLeir.blog.entity.PublicationCommentLike;
import io.seoLeir.blog.entity.keys.PublicationCommentsLikeId;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("PublicationCommentLike repository integration tests")
@Tags({@Tag("integration"), @Tag("slow")})
@ActiveProfiles("test")
@RequiredArgsConstructor
class PublicationCommentLikeRepositoryTest extends AbstractRepositoryTestBase {

    private final PublicationCommentLikeRepository publicationCommentLikeRepository;
    private static UUID publicationCommentUuid;
    private static PublicationCommentsLikeId publicationCommentsLikeId;

    @BeforeEach
    public void setUp(){
        publicationCommentUuid = UUID.fromString("d59cf130-cf4e-4710-bbca-ca3cc46e5cf1");
        publicationCommentsLikeId = new PublicationCommentsLikeId(
                UUID.fromString("874aa7a0-2a48-4e9b-85f5-301419def21a"),
                UUID.fromString("d3b54811-baa0-4c8f-99dc-2226f5c0cbb8"));
    }

    @ParameterizedTest(name = "Index: {index} -- Arguments: [{0} - {1}]")
    @DisplayName("is user liked")
    @MethodSource("io.seoLeir.blog.repository.PublicationCommentLikeRepositoryTest#getArgumentsForTestingIfUserLikesComment")
    void isUserLikedOrDislikedCommentTest(UUID userUuid, UUID commentUuid) {
        boolean userLikedOrDislikedComment = publicationCommentLikeRepository.isUserLikedOrDislikedComment(userUuid, commentUuid);
        assertThat(userLikedOrDislikedComment).isTrue();
    }

    @Test
    @DisplayName("get publication comment likes and dislikes")
    void getPublicationCommentLikesAndDislikes() {
        CommentLikeAndDislikeDto publicationCommentLikesAndDislikes = publicationCommentLikeRepository.getPublicationCommentLikesAndDislikes(publicationCommentUuid);
        assertAll(
                () -> assertThat(publicationCommentLikesAndDislikes).isNotNull(),
                () -> assertThat(publicationCommentLikesAndDislikes.getLikes()).isEqualTo(3L),
                () -> assertThat(publicationCommentLikesAndDislikes.getDislikes()).isEqualTo(2L)
        );
    }

    @Test
    @DisplayName("update publication comment status to like or dislike")
    void updateLikeStatus() {
        PublicationCommentLike referenceBeforeUpdate = publicationCommentLikeRepository.getReferenceById(publicationCommentsLikeId);
        Boolean newValue = !referenceBeforeUpdate.getIsLike();
        publicationCommentLikeRepository.updateLikeStatus(newValue, publicationCommentsLikeId);
        assertThat(publicationCommentLikeRepository.getReferenceById(publicationCommentsLikeId).getIsLike()).isEqualTo(newValue);
    }

    static Stream<Arguments> getArgumentsForTestingIfUserLikesComment() {
        return Stream.of(
                Arguments.of(UUID.fromString("874aa7a0-2a48-4e9b-85f5-301419def21a"), UUID.fromString("d3b54811-baa0-4c8f-99dc-2226f5c0cbb8")),
                Arguments.of(UUID.fromString("874aa7a0-2a48-4e9b-85f5-301419def21a"), UUID.fromString("d59cf130-cf4e-4710-bbca-ca3cc46e5cf1")),
                Arguments.of(UUID.fromString("a871dbd6-7f14-467c-9ac2-d5a0e3963ab2"), UUID.fromString("d3b54811-baa0-4c8f-99dc-2226f5c0cbb8")),
                Arguments.of(UUID.fromString("71edf8b7-8a4e-4f98-8e06-5d4e4c9f65f3"), UUID.fromString("8b934389-4961-47ea-a0f0-14252faaeb20")),
                Arguments.of(UUID.fromString("a871dbd6-7f14-467c-9ac2-d5a0e3963ab2"), UUID.fromString("d59cf130-cf4e-4710-bbca-ca3cc46e5cf1")),
                Arguments.of(UUID.fromString("4e2f28ab-6b33-45ed-8915-6d3a27cfa1cc"), UUID.fromString("0c6a594d-36b1-48cd-8db9-5b9794604416"))
        );
    }
}