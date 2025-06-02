package com.example.DigitalAssetNewFeatures.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int number;   // current page number
    private int size;
}
