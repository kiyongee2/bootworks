package com.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.presistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	//목록 보기
	@Override
	public List<Board> getBoardList() {
		//글번호로 내림 차순 정렬
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
	}

	//상세 보기
	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}

}
