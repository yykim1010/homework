package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.SuccessRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;


    @PostMapping("/api/post")
    public Memo creatMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);

    }

    @GetMapping("/api/posts")
    public List<Memo> getMemos() {
        return memoService.getMemos();//{}[id2,~]]

    }

    @GetMapping("/api/post/{id}")
    public Memo getId(@PathVariable Long id) {
        return memoService.findId(id);//{}[id2,~]]

    }

    @PutMapping("/api/post/{id}")
    public Memo updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public SuccessRequestDto deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.deleteMemo(id,requestDto);
    }
}
