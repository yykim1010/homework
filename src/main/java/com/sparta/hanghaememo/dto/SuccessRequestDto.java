package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class SuccessRequestDto {
    private boolean success;

    public SuccessRequestDto(boolean bool) {
        this.success = bool;
    }

}