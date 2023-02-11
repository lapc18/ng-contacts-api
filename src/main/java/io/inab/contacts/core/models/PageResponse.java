package io.inab.contacts.core.models;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    public List<T> body;
    public int currentPage;
    public long totalItems;
    public int totalPages;

    public PageResponse(Page<T> content) {
        this.body = content.getContent();
        this.currentPage = content.getNumber();
        this.totalItems = content.getTotalElements();
        this.totalPages = content.getTotalPages();
    }

    public PageResponse(List<T> body, int currentPage, long totalItems, int totalPages) {
        this.body = body;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
