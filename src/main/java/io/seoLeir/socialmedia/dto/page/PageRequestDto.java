package io.seoLeir.socialmedia.dto.page;

import org.springframework.data.domain.Sort;

public record PageRequestDto(int pageNumber, int pageSize, Sort sort) {
}
