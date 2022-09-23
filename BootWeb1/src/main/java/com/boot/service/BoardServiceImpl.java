package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	//목록 보기
	@Override
	public List<Board> getBoardList() {
		//findAll() - select * from board가 내장됨
		return boardRepo.findAll();
	}

	//새글 등록
	@Override
	public void insetBoard(Board board) {
		//save() - insert into ~ values
		boardRepo.save(board);
	}

	//게시글 상세보기
	@Override
	public Board getBoard(Long seq) {
		//findById().get() - select * from ~ where seq=?
		return boardRepo.findById(seq).get();
	}

	//글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

	//글 수정
	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle()); //입력 폼의 제목을 세팅
		findBoard.setContent(board.getContent()); //입력 폼의 내용을 세팅
		
		boardRepo.save(findBoard);  //다시 저장(수정)
	}

}
