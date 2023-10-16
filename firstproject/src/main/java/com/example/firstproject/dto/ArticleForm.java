package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    //DTO를 엔티티로 변환
    public Article toEntity() {
        return new Article(id, title, content);
    }

}
