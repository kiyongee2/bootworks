package com.boot;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryAnnotationTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	/*@Test
	public void testQueryAnnotationTest1() {
		List<Board> boardList =
				boardRepo.queryAnnotationTest1("테스트 제목 10");
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}*/
	
	@Test
	public void testQueryAnnotationTest2() {
		//1페이지를 게시글 5개 출력 - 내림차순 조회
		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
		List<Board> boardList = boardRepo.queryAnnotationTest2(paging);
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}
}







