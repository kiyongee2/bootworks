package com.boot.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.boot.domain.Board;

//CrudRepository<엔티티, 기본키 필드의 자료형>
public interface BoardRepository extends JpaRepository<Board, Long>{
	
	//쿼리 메소드 - 글 제목을 검색
	List<Board> findByTitle(String searchKeyword);
	
	//특정 단어가 포함된 목록 검색(Containing - Like 역할)
	List<Board> findByContentContaining(String searchKeyWord);
	
	//제목 또는 내용에 특정 단어가 포함된 목록 검색(Or 연산자)
	List<Board> findByTitleContainingOrContentContaining(String title,
			String content);
	
	//글 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회
	List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
	
	//제목 검색어가 포함된 게시글 목록을 조회하되 페이징 처리하여 내림차순 정렬하기
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	//글 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회(위치기반 파라미터)
	//@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	//List<Board> queryAnnotationTest1(String searchKeyword);
	
	//글 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회(이름기반 파라미터)
	@Query("SELECT b FROM Board b WHERE b.title LIKE %:searchKeyword% "
			+ "ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	//글 번호를 기준으로 내림차순 정렬하기(페이징 처리)
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(Pageable paging);
	
}









