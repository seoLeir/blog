package io.seoLeir.blog.dto.page;

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

    private PageResponseDto(List<T> content, int totalPages, long totalElements, int pageSize, int number, boolean last, boolean first) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.number = number;
        this.last = last;
        this.first = first;
    }

    public static <T> PageResponseDto<T> of(org.springframework.data.domain.Page<T> page) {
        return new PageResponseDto<>(
                page.getContent(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber() + 1,
                page.isLast(),
                page.isFirst());
    }

    public static <T> PageResponseDto<T> of(org.springframework.data.domain.Page<?> page, List<T> content){
        return new PageResponseDto<>(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber() + 1,
                page.isLast(),
                page.isFirst());
    }
}
