package com.example.profilresearch.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String title;
    private String format;

    @Nullable
    private List<String> choices;
}
