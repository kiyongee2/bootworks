package com.boot.presistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, 
QuerydslPredicateExecutor<Board>{
	//페이지 처리 - 글 목록
	@Query("SELECT b FROM Board b")
	Page<Board> getBoardList(Pageable pageable);
	
	//조회수
	@Modifying
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.seq = :seq")
	void updateCount(Long seq);
}
