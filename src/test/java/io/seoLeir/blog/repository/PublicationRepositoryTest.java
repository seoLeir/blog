package io.seoLeir.blog.repository;

import io.seoLeir.blog.IntegrationTestBase;
import io.seoLeir.blog.dto.publication.FeedDto;
import io.seoLeir.blog.entity.Publication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Publication repository integration tests")
@Tag("integration")
@ActiveProfiles("test")
@RequiredArgsConstructor
public class PublicationRepositoryTest extends IntegrationTestBase {

    private final PublicationRepository publicationRepository;
    private static UUID user10Uuid;
    private static UUID publicationUuid;
    private static Pageable pageable;

    @BeforeEach
    public void setup(){
        user10Uuid = UUID.fromString("8c5c4ff9-8c4d-4ff3-9d5c-0d3b1ca14e36");
        publicationUuid = UUID.fromString("3c59e157-05b9-4a00-a7d1-8fdebf0dcc1e");
        pageable = PageRequest.of(0, 20);
    }

    @Test
    @DisplayName("get user bookmarked publications")
    public void getAllUserBookmarkedPublicationTest(){
        List<UUID> userBookmarkedPublicationsUuid = List.of(
                UUID.fromString("2cb3c7e9-ad24-466e-8882-6a53b62f99f8"),
                UUID.fromString("1e144963-5ffa-4e7f-bd74-e6c08af1583f"),
                UUID.fromString("aa82a7c5-9aa5-4e2d-9863-fc69520c64c4"),
                UUID.fromString("09080b8c-2ea6-43a8-8b59-7446f56955ad"),
                UUID.fromString("e0d0a4d6-7e14-4f7c-9ac3-15a0e3963ab6"),
                UUID.fromString("78d0c387-77ab-4efb-b4a5-97b9999b969d"),
                UUID.fromString("1e9b3e9b-91ea-4d1e-937c-18c0e36eb3d1"),
                UUID.fromString("41f4a3d7-1a4e-4a2d-9d8e-1f7d6d2c64b2"));
        Pageable pageable = PageRequest.of(0, 20);
        Page<Publication> allUserBookmarkedPublication =
                publicationRepository.getAllUserBookmarkedPublication(userBookmarkedPublicationsUuid, pageable);
        assertThat(allUserBookmarkedPublication.getContent()).hasSize(8);
        assertThat(allUserBookmarkedPublication.getTotalElements()).isEqualTo(8L);
        assertThat(allUserBookmarkedPublication.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("get user publications count")
    public void getUserPublicationsCountTest(){
        Long actualResult = publicationRepository.getPublicationCountByUserUuid(user10Uuid);
        assertThat(actualResult).isEqualTo(4L);
    }

    @Test
    @DisplayName("get publication publisher username")
    public void getPublicationPublisherNameByPublicationUuidTest(){
        String name = publicationRepository.getPublicationPublisherNameByPublicationUuid(publicationUuid);
        assertThat(name).isEqualTo("user10");
    }

    @Test
    @DisplayName("get non exists publication publisher username")
    public void getPublicationPublisherNameByNonExistPublicationUuidTest(){
        String name = publicationRepository.getPublicationPublisherNameByPublicationUuid(UUID.randomUUID());
        assertThat(name).isEqualTo(null);
    }

    @Test
    @DisplayName("get non exist publication by id")
    public void getNonExistPublicationByUuid() {
        Optional<Publication> publicationById = publicationRepository.getPublicationById(UUID.randomUUID());
        assertThat(publicationById.isPresent()).isFalse();
    }

    @Test
    @DisplayName("get publication for feeder")
    public void getPublicationForFeedTest(){
        Publication publication = publicationRepository.getPublicationForFeeder(publicationUuid);
        assertAll(
                () -> assertThat(publication).isNotNull(),
                () -> assertThat(publication.getId()).isNotNull(),
                () -> assertThat(publication.getText()).isNotNull(),
                () -> assertThat(publication.getText()).hasSizeBetween(1, 20)
        );
    }

    @Test
    @DisplayName("get non exist publication for feeder")
    public void getNonExistPublicationForFeedTest(){
        Publication publication = publicationRepository.getPublicationForFeeder(UUID.randomUUID());
        assertThat(publication).isNull();
    }

    @Test
    @DisplayName("delete exist publication")
    public void deletePublicationTest(){
        Publication beforeDeletePublication = publicationRepository.getPublicationForFeeder(publicationUuid);
        assertAll(
                () -> assertThat(beforeDeletePublication).isNotNull(),
                () -> assertThat(beforeDeletePublication.getId()).isNotNull()
        );
        publicationRepository.deleteById(publicationUuid);
        Publication afterDeletePublication = publicationRepository.getPublicationForFeeder(publicationUuid);
        assertThat(afterDeletePublication).isNull();
    }

    @Test
    @DisplayName("update publication view count")
    public void updatePublicationViewCountTest(){
        publicationRepository.updateViewCount(231L, publicationUuid);
        Publication publication = publicationRepository.getReferenceById(publicationUuid);
        assertAll(
                () -> assertThat(publication).isNotNull(),
                () -> assertThat(publication.getViewCount()).isEqualTo(231)
        );
    }

    @Test
    @DisplayName("update publication title and text")
    public void updatePublicationTitleAndTextTest(){
        String newText = "new publication test text";
        String newTitle = "new publication test title";
        publicationRepository.updateTitleAndText(newTitle, newText, publicationUuid);
        Publication publicationAfter = publicationRepository.getReferenceById(publicationUuid);
        assertAll(
                () -> assertThat(publicationAfter).isNotNull(),
                () -> assertThat(publicationAfter.getText()).isEqualTo(newText),
                () -> assertThat(publicationAfter.getTitle()).isEqualTo(newTitle)
        );
    }

    @Test
    @DisplayName("get top publications by period(DAY, WEEK, MONTH, YEAR)")
    public void getTopPublicationsByPeriodTest(){
        Instant startByDay = Instant.now().minus(1, DAYS);
        Instant end = Instant.now();
        Page<FeedDto> topPublicationsByPeriodDay = publicationRepository.getTopPublicationsByPeriod(startByDay, end, pageable);
        assertThat(topPublicationsByPeriodDay).isEmpty();

        Instant startByWeek = Instant.now().minus(7, DAYS);
        Page<FeedDto> topPublicationsByPeriodWeek = publicationRepository.getTopPublicationsByPeriod(startByWeek, end, pageable);
        assertThat(topPublicationsByPeriodWeek).isEmpty();

        Instant startByMonth = LocalDateTime.now().minus(1, MONTHS).toInstant(UTC);
        Page<FeedDto> topPublicationsByPeriodMonth = publicationRepository.getTopPublicationsByPeriod(startByMonth, end, pageable);
        assertThat(topPublicationsByPeriodMonth).isEmpty();

        Instant startByYear = LocalDateTime.now().minus(1, YEARS).toInstant(UTC);
        Page<FeedDto> topPublicationsByPeriodYear = publicationRepository.getTopPublicationsByPeriod(startByYear, end, pageable);
        assertAll(
                () -> assertThat(topPublicationsByPeriodYear.getContent()).isNotEmpty(),
                () -> assertThat(topPublicationsByPeriodYear.getContent()).hasSize(8)
        );
    }

    @Test
    @DisplayName("get top publications by all time")
    public void getTopPublicationsAllTimeTest(){
        Page<FeedDto> topPublicationsAllTime = publicationRepository.getTopPublicationsAllTime(Instant.now(), pageable);
        assertAll(
                () -> assertThat(topPublicationsAllTime.getTotalElements()).isEqualTo(31),
                () -> assertThat(topPublicationsAllTime.getTotalPages()).isEqualTo(2),
                () -> assertThat(topPublicationsAllTime.getContent()).isNotEmpty(),
                () -> assertThat(topPublicationsAllTime.getContent()).hasSize(20)
        );
    }

    @Test
    @DisplayName("get new publications by range")
    public void getNewPublicationsByRangeFilterTest() {
        Page<FeedDto> newPublicationBy10Range = publicationRepository.getNewPublicationsByRangeFilter(10, pageable);
        assertAll(
                () -> assertThat(newPublicationBy10Range).isNotNull(),
                () -> assertThat(newPublicationBy10Range.getContent()).isNotEmpty(),
                () -> assertThat(newPublicationBy10Range.getContent()).hasSize(20)
        );

        Pageable page = PageRequest.of(1, 20);
        Page<FeedDto> newPublicationBy10RangeSecondPage = publicationRepository.getNewPublicationsByRangeFilter(10, page);
        assertAll(
                () -> assertThat(newPublicationBy10RangeSecondPage).isNotNull(),
                () -> assertThat(newPublicationBy10RangeSecondPage.getContent()).isNotEmpty(),
                () -> assertThat(newPublicationBy10RangeSecondPage.getContent()).hasSize(11)
        );

        Page<FeedDto> newPublicationBy50Range = publicationRepository.getNewPublicationsByRangeFilter(50, pageable);
        assertAll(
                () -> assertThat(newPublicationBy50Range).isNotNull(),
                () -> assertThat(newPublicationBy50Range.getContent()).isNotEmpty(),
                () -> assertThat(newPublicationBy50Range.getContent()).hasSize(2)
        );
    }

    @Test
    @DisplayName("search publication by popularity order")
    public void searchPublicationByPopularityOrderTest() {
        String textToSearch1 = "wAr";
        Page<FeedDto> foundPublications1 = publicationRepository.searchPublicationByPopularityOrder(textToSearch1, pageable);
        assertAll(
                () -> assertThat(foundPublications1).isNotNull(),
                () -> assertThat(foundPublications1.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications1.getContent()).hasSize(1)
        );

        Pageable page = PageRequest.of(0, 50);
        String textToSearch2 = "a";
        Page<FeedDto> foundPublications2 = publicationRepository.searchPublicationByPopularityOrder(textToSearch2, page);
        assertAll(
                () -> assertThat(foundPublications2).isNotNull(),
                () -> assertThat(foundPublications2.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications2.getContent()).hasSize(31),
                () -> assertThat(foundPublications2.getContent())
                        .extracting("score", Double.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );

        String textToSearch3 = "b";
        Page<FeedDto> foundPublications3 = publicationRepository.searchPublicationByPopularityOrder(textToSearch3, page);
        assertAll(
                () -> assertThat(foundPublications3).isNotNull(),
                () -> assertThat(foundPublications3.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications3.getContent()).hasSize(14),
                () -> assertThat(foundPublications3.getContent())
                        .extracting("score", Double.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );

        String textToSearch4 = "c";
        Page<FeedDto> foundPublications4 = publicationRepository.searchPublicationByPopularityOrder(textToSearch4, page);
        assertAll(
                () -> assertThat(foundPublications4).isNotNull(),
                () -> assertThat(foundPublications4.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications4.getContent()).hasSize(29),
                () -> assertThat(foundPublications4.getContent())
                        .extracting("score", Double.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );
    }

    @Test
    @DisplayName("search publication by created date order")
    public void searchPublicationByCreatedDateDescOrderTest() {
        String textToSearch1 = "wAr";
        Page<Publication> foundPublications1 = publicationRepository.searchPublicationByCreatedDateOrder(textToSearch1, pageable);
        assertAll(
                () -> assertThat(foundPublications1).isNotNull(),
                () -> assertThat(foundPublications1.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications1.getContent()).hasSize(1)
        );

        Pageable page = PageRequest.of(0, 50);
        String textToSearch2 = "a";
        Page<Publication> foundPublications2 = publicationRepository.searchPublicationByCreatedDateOrder(textToSearch2, page);
        assertAll(
                () -> assertThat(foundPublications2).isNotNull(),
                () -> assertThat(foundPublications2.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications2.getContent()).hasSize(31),
                () -> assertThat(foundPublications2.getContent())
                        .extracting("createdDate", Instant.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );

        String textToSearch3 = "b";
        Page<Publication> foundPublications3 = publicationRepository.searchPublicationByCreatedDateOrder(textToSearch3, page);
        assertAll(
                () -> assertThat(foundPublications3).isNotNull(),
                () -> assertThat(foundPublications3.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications3.getContent()).hasSize(14),
                () -> assertThat(foundPublications3.getContent())
                        .extracting("createdDate", Instant.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );

        String textToSearch4 = "c";
        Page<Publication> foundPublications4 = publicationRepository.searchPublicationByCreatedDateOrder(textToSearch4, page);
        assertAll(
                () -> assertThat(foundPublications4).isNotNull(),
                () -> assertThat(foundPublications4.getContent()).isNotEmpty(),
                () -> assertThat(foundPublications4.getContent()).hasSize(29),
                () -> assertThat(foundPublications4.getContent())
                        .extracting("createdDate", Instant.class)
                        .isSortedAccordingTo(Comparator.reverseOrder())
        );
    }


}
