package io.seoLeir.blog.repository;

import io.seoLeir.blog.AbstractRepositoryTestBase;
import io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

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
    private static UUID publicationUuid;
    private static Pageable pageable;

    @BeforeEach
    public void setUp(){
        userUuid = UUID.fromString("d0d8a85d-5e3b-4fc4-a2ec-0b6972440a42");
        publicationUuid = UUID.fromString("a0e7e0a0-2e44-4e9b-85f9-30a119def21e");
        pageable = PageRequest.of(0, 100, Sort.unsorted());
    }

    @Test
    @DisplayName("get user all comments count")
    void getUserAllCommentsTest() {
        Long userAllCommentsCount = publicationCommentRepository.getUserAllCommentsCount(userUuid);
        assertThat(userAllCommentsCount).isEqualTo(14L);
    }

    @Test
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
    void getUserComments() {
    }

    @Test
    void updateCommentMessage() {
    }

    @Test
    void getCommentByUserUuid() {
    }

    @Test
    void deleteByCommentUuid() {
    }

    @Test
    void findPublicationCommentByUuid() {
    }
}