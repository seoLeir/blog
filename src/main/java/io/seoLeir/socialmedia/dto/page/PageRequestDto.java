package io.seoLeir.socialmedia.dto.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageRequestDto(int pageNumber, int pageSize, Sort sort) {

    public static Pageable toPageable(PageRequestDto pageRequestDto){
        return PageRequest.of(
                pageRequestDto.pageNumber(),
                pageRequestDto.pageNumber(),
                (pageRequestDto.sort() == null) ? Sort.unsorted() : pageRequestDto.sort()
        );
    }
}
