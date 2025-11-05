package com.korit.servlet_study.ch08;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor //Builder의 AllArgs랑 NoArgs 중 우선순위는 NoArgs
@Builder
@Data
public class Board {
    private String title;
    private String content;
    private String writer;



}
