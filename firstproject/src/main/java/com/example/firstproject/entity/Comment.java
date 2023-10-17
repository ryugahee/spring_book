package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString /// 모든 필드를 출력 가능
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 갱성자 자동 생성
@NoArgsConstructor // 매개변수가 아예 없는 기본 생성자 자동 생성
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 대표키

    @ManyToOne // Comment와 Article 엔티티를 다대일 관게로 설정(댓글과 게시글은 다대일 관계)
    @JoinColumn(name="article_id") //외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Article article; // 해당 댓글의 부모 게시글

    @Column
    private String nickname; // 댓글을 단 사람

    @Column
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if (dto.getId() != null)
            throw new IllegalStateException("댓글 생성 실패. 댓글의 id가 없어야행");
        if (dto.getArticleId() != article.getId())
            throw new IllegalStateException("댓글 생성 실패. 게시글의 id가 잘못됐어잉");
        //dto에 id가 존재하거나, dto에서 가져온 부모 게시글과 엔티티에서 가져온 부모게시글의 id가 다를 때 예외 발생시킴
            
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(), //댓글 아이디
                article, //부모 게시글
                dto.getNickname(), //댓글 닉네임
                dto.getBody()); //댓글 본문
    }


    public void patch(CommentDto dto) {
        //예외 발생
        if (this.id != dto.getId())
            throw new IllegalStateException("댓글 수정 실패! 잘못된 id가 입력됐다능");
        //객체 갱신
        if (dto.getNickname() != null) //수정할 닉네임이 있다면
            this.nickname = dto.getNickname(); //내용 반영
        if (dto.getBody() != null) //수정할 본문이 있다면
            this.body = dto.getBody(); //내용 반영
    }
}
