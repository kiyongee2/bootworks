package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.persistence.BoardRepository;
import com.boot.persistence.MemberRepository;

@SpringBootTest
public class ReleationMappingTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	/*@Test
	public void testManyToOneInsert() {
		//회원 생성
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("뽀로로");
		member1.setRole("User");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("관리자");
		member2.setRole("Admin");
		memberRepo.save(member2);
		
		//게시글 생성
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("뽀로로가 등록한 게시글 " + i);
			board.setContent("뽀로로가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("관리자가 등록한 게시글 " + i);
			board.setContent("관리자가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}*/
	
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).get();
		
		System.out.println("[" + board.getSeq() + "번 게시글 정보]");
		System.out.println("제목\t: " + board.getTitle());
		System.out.println("작성자\t: " + board.getMember().getName());
		System.out.println("내용\t: " + board.getContent());
		System.out.println("권한\t: " + board.getMember().getRole());
	}
}
