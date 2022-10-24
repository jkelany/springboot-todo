package com.jkelany.todo.api.cdm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageResponse<T> {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> content;
}