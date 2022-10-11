package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	//목록 보기
	@Override
	public List<Board> getBoardList() {
		//return boardRepo.findAll();  //오름 차순 정렬
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq")); //내림차순
	}

	//글 상세 보기
	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}

	//글쓰기
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	@Transactional  //트랜잭션 처리
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);
	}

	//글 수정
	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

}
