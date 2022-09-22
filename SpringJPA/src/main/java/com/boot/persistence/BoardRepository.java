package com.boot.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boot.domain.Board;

//CrudRepository<엔티티, 기본키 필드의 자료형>
public interface BoardRepository extends CrudRepository<Board, Long>{
	
	//쿼리 메소드 - 글 제목을 검색
	List<Board> findByTitle(String searchKeyword);
	
	//특정 단어가 포함된 목록 검색(Containing - Like 역할)
	List<Board> findByContentContaining(String searchKeyWord);
}
