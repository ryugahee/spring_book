package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1 : 4번 게시글의 모든 댓글 조회 */
        {
            //1. 입력 데이터 준비
            Long articleId = 4L;
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글고고"); //부모 게시글 객체 생성
            Comment a = new Comment(1L, article, "Park", "타이타닉"); //댓글 객체 생성
            Comment b = new Comment(2L, article, "Kim", "라푼젤");
            Comment c = new Comment(3L, article, "Ryu", "해리포터");
            List<Comment> expected = Arrays.asList(a, b, c); //댓글 객체 합치기
            //4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력스");
        }
        /* Case 2 : 1번 게시글의 모든 댓글 조회 */
        {
            //1. 입력 데이터 준비
            Long articleId = 1L;
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111"); //부모 게시글 객체 생성
            List<Comment> expected = Arrays.asList(); //댓글 객체 합치기
            //4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1 : "Park"의 모든 댓글 조회 */
        {
            //1. 입력 데이터 준비
            String nickname = "Park";
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글고고"), nickname, "타이타닉");
            Comment b = new Comment(6L, new Article(6L, "당신의 취미는?", "댓고"), nickname, "발레");
            List<Comment> expected = Arrays.asList(a, b); //댓글 객체 합치기
            //4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력");
        }
    }
}