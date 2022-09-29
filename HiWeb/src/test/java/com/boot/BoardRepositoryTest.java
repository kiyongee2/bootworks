package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.presistence.BoardRepository;
import com.boot.presistence.MemberRepository;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	//회원 및 게시글 등록
	/*@Test
	public void testInsert() {
		//회원 생성
		Member member1 = new Member();
		member1.setId("member");
		member1.setPassword("member123");
		member1.setName("회원");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		//회원중 관리자 생성
		Member member2 = new Member();
		member2.setId("admin");
		member2.setPassword("admin123");
		member2.setName("관리자");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);
		
		//회원이 작성한 게시글 입력
		for(int i=1; i<=13; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "이(가) 등록한 게시글 " + i);
			board.setContent(member1.getName() + "이(가) 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		
		//관리자가 작성한 게시글
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "이(가) 등록한 게시글 " + i);
			board.setContent(member2.getName() + "이(가) 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}*/
	
	//상세 조회 테스트 : 게시글을 통해서 회원 정보 조회
	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("[ " + board.getSeq() + "번 게시글 정보 ]");
		System.out.println("제목: " + board.getTitle());
		System.out.println("작성자: " + board.getMember().getName());
		System.out.println("내용: " + board.getContent());
		System.out.println("등록일: " + board.getCreateDate());
		System.out.println("조회수: " + board.getCnt());
		System.out.println("권한: " + board.getMember().getRole());
	}
	
	//게시글 목록 검색 - 회원이나 관리자가 등록한 게시글 조회
	@Test
	public void testGetBoardList() {
		//Member member = memberRepo.findById("member").get();
		Member member = memberRepo.findById("admin").get();
		
		System.out.println("[ " + member.getName() + "이(가) 등록한 게시글 ]");
		for(Board board : member.getBoardList())
			System.out.println("--->" + board.toString());
			
	}
	
}














