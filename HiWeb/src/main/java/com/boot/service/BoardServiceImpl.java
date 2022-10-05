package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.domain.QBoard;
import com.boot.domain.Search;
import com.boot.presistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	//목록 보기
	/*@Override
	public List<Board> getBoardList() {
		//글번호로 내림 차순 정렬
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
	}*/
	
	@Override
	public Page<Board> getBoardList(Search search) {
		//검색 처리
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		}else if(search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		}
		
		//페이징 처리
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		return boardRepo.findAll(builder, pageable);
	}

	//상세 보기
	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}

	//글 쓰기
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	//글 수정
	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
	}

	//글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

	//조회수
	@Transactional  //트랜잭션 처리 어노테이션
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);
	}

	
}
