package com.pknu26.studygroup.dto;

import lombok.Data;

@Data
public class PageRequest {

    private int page = 1;  /// 현재 페이지
    private int size = 10; // 한 페이지당 게시글 수

    public int getOffset() {
        return (page - 1) * size;
    }
}