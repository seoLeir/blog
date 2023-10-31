package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.OrderType;
import io.seoLeir.socialmedia.dto.publication.PeriodType;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedService {
    private final PublicationService publicationService;

    public PageResponseDto<PublicationGetResponseDto> defaultFeed(PageRequestDto pageRequestDto) {
        return publicationService.getFeedWithSpecification(pageRequestDto, null);
    }

    public PageResponseDto<PublicationGetResponseDto> feedWithRangeByNewPost(PageRequestDto pageRequestDto,
                                                                             Integer rangeFilter) {
        return null;
//        TODO 31.10.2023
    }

    public PageResponseDto<PublicationGetResponseDto> feedWithPeriodByPopularPost(PageRequestDto pageRequestDto,
                                                                                  PeriodType period) {
        return null;
//        TODO 31.10.2023
    }

    public PageResponseDto<PublicationGetResponseDto> searchPosts(PageRequestDto pageRequestDto,
                                                                  String searchFilterText,
                                                                  OrderType orderType) {
//        TODO 31.10.2023
        return null;
    }
}
