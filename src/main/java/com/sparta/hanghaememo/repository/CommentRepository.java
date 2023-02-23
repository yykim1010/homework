package com.sparta.hanghaememo.repository;

import com.sparta.hanghaememo.dto.CommentRequestDto;
import com.sparta.hanghaememo.dto.CommentResponseDto;
import com.sparta.hanghaememo.entity.Comment;
import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentResponseDto> findAllByCommentId(Long commentId);
    List<Memo> findAllByOrderByCreatedAtDesc();

    Optional<Comment> findByIdAndUserId(Long id, Long userId);
}