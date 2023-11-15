package io.seoLeir.blog.dto.page;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageRequestDto(@PositiveOrZero int pageNumber, @PositiveOrZero int pageSize, Sort sort) {

    public static Pageable toPageable(PageRequestDto pageRequestDto){
        return PageRequest.of(
                pageRequestDto.pageNumber(),
                pageRequestDto.pageSize(),
                (pageRequestDto.sort() == null) ? Sort.unsorted() : pageRequestDto.sort()
        );
    }
}
