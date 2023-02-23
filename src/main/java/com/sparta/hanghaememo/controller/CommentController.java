package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.*;
import com.sparta.hanghaememo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.createComment(id, requestDto, request);

    }

    @PutMapping("/{id}")
    public CommentResponseDto update(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.update(id, requestDto, request);

    }

    @DeleteMapping("/{id}")
    public UserResponseDto delete(@PathVariable Long id, HttpServletRequest request) {
        return commentService.delete(id, request);
    }
}
