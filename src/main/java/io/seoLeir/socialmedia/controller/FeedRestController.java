package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.OrderType;
import io.seoLeir.socialmedia.dto.publication.PeriodType;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publications")
public class FeedRestController {
    private final FeedService feedService;
    @GetMapping
    public PageResponseDto<PublicationGetResponseDto> getPublicationsFeedWithDefaultFilter(@RequestBody PageRequestDto pageRequestDto) {
        return null;
//        TODO 31.10.2023
    }

    @GetMapping("/rated/{range}")
    public PageResponseDto<PublicationGetResponseDto> getPublicationsFeedWithNewFilter(@RequestBody PageRequestDto pageRequestDto,
                                                                                       @PathVariable("range") Integer rangeFilter) {
        return null;
//        TODO 31.10.2023
    }

    @GetMapping("/rated/{period}")
    public PageResponseDto<PublicationGetResponseDto> getPublicationsFeedWithFilter(@RequestBody PageRequestDto pageRequestDto,
                                                                                    @PathVariable("period") PeriodType period) {
        return null;
//        TODO 31.10.2023
    }

    @GetMapping("/search")
    public PageResponseDto<PublicationGetResponseDto> getPublicationsWithSearchFilter(@RequestBody PageRequestDto pageRequestDto,
                                                                                      @RequestParam("t") String searchFilterText,
                                                                                      @RequestParam("order") OrderType orderType) {
//        TODO 31.10.2023
        return null;
    }
}
