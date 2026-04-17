package com.pknu26.studygroup.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PageResponse<T> {

    private final List<T> dtoList;
    private final int totalCount;
    private final int currentPage;
    private final int size;
    private final int totalPage;

    private final int startPage;
    private final int endPage;
    private final boolean prev;
    private final boolean next;

    public PageResponse(List<T> dtoList, int totalCount, int currentPage, int size) {
        this.dtoList = dtoList;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.size = size;

        this.totalPage = (int) Math.ceil((double) totalCount / size);

        int tempEnd = (int) (Math.ceil(currentPage / 10.0) * 10);
        this.startPage = tempEnd - 9;
        this.endPage = Math.min(tempEnd, totalPage);

        this.prev = startPage > 1;
        this.next = totalPage > tempEnd;
    }
}