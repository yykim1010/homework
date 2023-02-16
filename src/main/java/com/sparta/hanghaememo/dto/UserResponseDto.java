package com.sparta.hanghaememo.dto;


import com.sparta.hanghaememo.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String msg;
    private int statusCode;

    public UserResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
