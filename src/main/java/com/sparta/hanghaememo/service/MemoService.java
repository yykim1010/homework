package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.dto.UserResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.jwt.JwtUtil;
import com.sparta.hanghaememo.repository.MemoRepository;
import com.sparta.hanghaememo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //게시글 목록 조회
//    @Transactional(readOnly = true)
//    public List<MemoResponseDto> getBoard() {
//        return memoRepository.findAllByOrderByCreatedAtDesc();
//    }

    //게시글 등록
    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user));
            return new MemoResponseDto(memo);

        } else {
            return null;
        }
    }

    //게시글 상세 조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        ArrayList<MemoResponseDto> list = new ArrayList<>();
        for (Memo memo : memoRepository.findAllByOrderByCreatedAtDesc()) {
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            list.add(memoResponseDto);
        }
        //for ( memo 1set(4개변수) : memo set many(memo1 memo2 memo3 memo4 )
        //4번
        //첫번째, 제일먼저 생성된 memo 1set 두번째, 그다음 생성된 memo 1set
        // []->list = [mempresponsedto1,memoresponsedto2,~,~]
        return list;
    }

    //선택한 게시글 조회
    @Transactional
    public MemoResponseDto findId(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(                  //아이디 존재하는지 확인
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    //선택한 게시글 수정
    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            memo.update(requestDto);
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            return memoResponseDto;
        } else {
            return null;
        }
    }

    //선택한 게시글 삭제
    @Transactional
    public UserResponseDto delete(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );
            //requestDto를 받지 않고 지울때
            memoRepository.deleteById(memo.getId());
            //memo.delete(requestDto);
            UserResponseDto userResponseDto = new UserResponseDto("게시글 삭제 성공", 200);
            return userResponseDto;
        } else {
            return null;
        }

    }
}
  // id1 id=2 id=3
//
//    @Transactional
//    public UserResponseDto delete(Long id, UserRequestDto requestDto) {
//
//        String currentpassword = requestDto.getPassword(); //12345
//        Memo memo = memoRepository.findByIdAndPassword(id,currentpassword).orElseThrow(  //아이디 존재하는지 확인
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//
//        memoRepository.delete(memo);// memo.delete(userRequestDto) 아닌지?
//        UserRequestDto userRequestDto = new UserRequestDto(true);
//        return userRequestDto;
//    }
//}