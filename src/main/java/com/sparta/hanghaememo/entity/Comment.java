package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Memo_ID", nullable = false)
    private Memo memo;


    public Comment(CommentRequestDto requestDto, User user, Memo memo) {
        this.content = requestDto.getContent();
        this.user = user;
        this.memo = memo;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}