package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.dto.UserResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;


    @PostMapping("/api/post")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request ) {  //memo, user 바꾸기
        return memoService.createMemo(requestDto,request);

    }

    @GetMapping("/api/posts")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();

    }

    @GetMapping("/api/post/{id}")
    public MemoResponseDto getId(@PathVariable Long id) {
        return memoService.findId(id);

    }

    @PutMapping("/api/post/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto,HttpServletRequest request){
        return memoService.update(id, requestDto, request);

    }


    @DeleteMapping("/api/post/{id}")
    public UserResponseDto deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        return memoService.delete(id, request);
    }
}