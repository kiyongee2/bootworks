package com.boot;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;  //인터페이스형 객체임
	
	
	//게시글 등록
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("두 번째 게시글");
		//board.setWriter("테스터");
		board.setContent("등록이 잘 되나요?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);  //insert 기능(db에 저장)
	}
	
	//게시글 1개 보기
	/*@Test
	public void testGetBoard() {
		//상세 조회 - findById(기본키값)
		Board board = boardRepo.findById(1L).get();
		log.info(board.toString());
	}*/
	
	//게시글 삭제
	/*@Test
	public void testDeleteBoard() {
		
		log.info("2번 게시글 삭제");
		
		boardRepo.deleteById(3L);
	}*/
	
	//게시글 수정
	/*@Test
	public void testUpdateBoard() {
		log.info("1번 게시글 조회");
		Board board = boardRepo.findById(1L).get();
		
		log.info("1번 게시글 제목 수정");
		board.setTitle("제목을 수정합니다.");
		//수정후 저장
		boardRepo.save(board);
	}*/
	
	//게시글 목록 보기
	/*@Test
	public void testBoardAll() {
		List<Board> boardList = (List<Board>) boardRepo.findAll();
		
		for(Board board : boardList)
			log.info(board.toString());
	}*/
}







