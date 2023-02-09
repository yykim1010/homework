package com.sparta.hanghaememo.repository;


import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByCreatedAtDesc();

    Optional<Memo> findByIdAndPassword(Long id, String password);
}
// [[id1, password1, title1] , [id2,passwrod2,title2]]
// [[id2,passwrod2,title2] ,[id1, password1, title1]]