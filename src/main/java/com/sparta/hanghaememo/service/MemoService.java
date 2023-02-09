package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.SuccessRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Memo findId(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return memo;
    }

    @Transactional
    public Memo update(Long id, MemoRequestDto requestDto) {
        String currentpassword = requestDto.getPassword();
        Memo memo = memoRepository.findByIdAndPassword(id,currentpassword).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo;
    }

    @Transactional
    public SuccessRequestDto  deleteMemo(Long id, MemoRequestDto requestDto) {

        String currentpassword = requestDto.getPassword();
        Memo memo = memoRepository.findByIdAndPassword(id,currentpassword).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        memoRepository.delete(memo);
        SuccessRequestDto successRequestDto = new SuccessRequestDto(true);
        return successRequestDto;
    }
}
