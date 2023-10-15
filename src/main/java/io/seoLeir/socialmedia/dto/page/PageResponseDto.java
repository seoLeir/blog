package io.seoLeir.socialmedia.dto.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDto<T> {
    List<T> content;
    int totalPages;
    long totalElements;
    int pageSize;
    int number;
    boolean last;
    boolean first;

    public PageResponseDto(List<T> content, int totalPages, long totalElements, int size, int i, boolean last, boolean first) {

    }

    public static <T> PageResponseDto<T> of(org.springframework.data.domain.Page<T> page) {
        return new PageResponseDto<>(page.getContent(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber() + 1, page.isLast(), page.isFirst());
    }
}
