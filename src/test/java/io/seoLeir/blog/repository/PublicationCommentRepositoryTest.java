package io.seoLeir.blog.repository;

import io.seoLeir.blog.AbstractRepositoryTestBase;
import io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.blog.entity.PublicationComment;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("PublicationComment repository integration tests")
@Tags({@Tag("integration"), @Tag("slow")})
@ActiveProfiles("test")
@RequiredArgsConstructor
class PublicationCommentRepositoryTest extends AbstractRepositoryTestBase {
    private final PublicationCommentRepository publicationCommentRepository;

    private static UUID userUuid;
    private static UUID currentUser;
    private static UUID commentUuid;
    private static UUID publicationUuid;
    private static Pageable pageable;

    @BeforeEach
    public void setUp(){
        userUuid = UUID.fromString("d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42");
        currentUser = UUID.fromString("5df3b797-b1cc-43c0-942a-06cdd30bdbfc");
        publicationUuid = UUID.fromString("a0e7e0a0-2e44-4e9b-85f9-30a119def21e");
        commentUuid = UUID.fromString("8b934389-4961-47ea-a0f0-14252faaeb20");
        pageable = PageRequest.of(0, 100, Sort.unsorted());
    }

    @Test
    @DisplayName("get user all comments count")
    void getUserAllCommentsTest() {
        Long userAllCommentsCount = publicationCommentRepository.getUserAllCommentsCount(userUuid);
        assertThat(userAllCommentsCount).isEqualTo(14L);
    }

    @Test
    @DisplayName("get publication comments")
    void getPublicationComments() {
        Page<PublicationCommentGetResponseDto> publicationComments = publicationCommentRepository.getPublicationComments(publicationUuid, userUuid, pageable);
        assertAll(
                () -> assertThat(publicationComments).isNotNull(),
                () -> assertThat(publicationComments).isNotEmpty(),
                () -> assertThat(publicationComments.getContent()).isNotNull(),
                () -> assertThat(publicationComments.getContent()).isNotEmpty(),
                () -> assertThat(publicationComments.getContent()).hasSize(10),
                () -> {
                    publicationComments.getContent().forEach(publicationComment -> {
                        assertThat(publicationComment.getPublicationUuid()).isEqualTo(publicationUuid);
                        assertThat(publicationComment.getPublicationUuid()).isEqualTo(publicationUuid);
                        assertThat(publicationComment.getUserUuid()).isNotNull();
                        assertThat(publicationComment.getCommentMessage()).isNotEmpty();
                    });
                }
        );
    }

    @Test
    @DisplayName("get user comments")
    void getUserCommentTest() {
        Page<PublicationCommentGetResponseDto> publicationComments = publicationCommentRepository.getUserComments(userUuid, currentUser, pageable);
        assertAll(
                () -> assertThat(publicationComments).isNotNull(),
                () -> assertThat(publicationComments).isNotEmpty(),
                () -> assertThat(publicationComments.getContent()).isNotNull(),
                () -> assertThat(publicationComments.getContent()).isNotEmpty(),
                () -> assertThat(publicationComments.getContent()).hasSize(14),
                () -> publicationComments.getContent().forEach(publicationComment -> {
                    assertThat(publicationComment.getUserUuid()).isNotNull();
                    assertThat(publicationComment.getUserUuid()).isEqualTo(userUuid);
                    assertThat(publicationComment.getCommentMessage()).isNotEmpty();
                })
        );
    }

    @Test
    @DisplayName("update user comment")
    void updateCommentMessage() {
        Optional<PublicationComment> publicationCommentBeforeOptional = publicationCommentRepository.getPublicationCommentByUuid(commentUuid);
        assertAll(
                () -> assertThat(publicationCommentBeforeOptional).isNotNull(),
                () -> assertThat(publicationCommentBeforeOptional).isNotEmpty()
        );
        PublicationComment publicationCommentBefore = publicationCommentBeforeOptional.get();
        String updatedText = "Updated text";
        publicationCommentRepository.updateCommentMessage(updatedText, commentUuid);
        Optional<PublicationComment> publicationCommentAfterOptional = publicationCommentRepository.getPublicationCommentByUuid(commentUuid);
        assertAll(
                () -> assertThat(publicationCommentAfterOptional).isNotNull(),
                () -> assertThat(publicationCommentAfterOptional).isNotEmpty()
        );
        PublicationComment publicationCommentAfter = publicationCommentAfterOptional.get();
        assertAll(
                () -> assertThat(publicationCommentBefore.getCommentMessage()).isNotEqualTo(publicationCommentAfter.getCommentMessage()),
                () -> assertThat(publicationCommentAfter.getCommentMessage()).isEqualTo(updatedText)
        );
    }

    @Test
    @DisplayName("get user comment by id")
    void getCommentById() {
        Optional<PublicationComment> publicationComment = publicationCommentRepository.getPublicationCommentByUuid(commentUuid);
        assertAll(
                () -> assertThat(publicationComment).isNotNull(),
                () -> assertThat(publicationComment).isNotEmpty()
        );
        assertAll(
                () -> assertThat(publicationComment.get()).isNotNull(),
                () -> assertThat(publicationComment.get().getId()).isEqualTo(commentUuid)
        );
    }

    @Test
    @DisplayName("delete user comment by id")
    void deleteByCommentUuid() {
        Optional<PublicationComment> publicationComment = publicationCommentRepository.getPublicationCommentByUuid(commentUuid);
        assertAll(
                () -> assertThat(publicationComment).isNotNull(),
                () -> assertThat(publicationComment).isNotEmpty()
        );
        publicationCommentRepository.deleteByCommentUuid(commentUuid);
        Optional<PublicationComment> afterDelete = publicationCommentRepository.getPublicationCommentByUuid(commentUuid);
        assertThat(afterDelete).isEmpty();
    }

}