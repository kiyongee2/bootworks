package com.boot.service;

import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;

	//게시글 등록
	@Override
	public Long register(BoardDto dto) {
		Board board = dtoToEntity(dto);  //db에 저장하기 위해 호출
		boardRepo.save(board);
		
		return board.getBno();
	}

	@Override
	public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
		//entityToDto 호출
		Function<Object[], BoardDto> fn = (en -> entityToDto((Board)en[0],
				(Member)en[1], (Long)en[2]));
		
		//Pageable pageable = pageRequestDto.getPageable(Sort.by("bno").descending());
		//Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
		
		Page<Object[]> result = boardRepo.searchPage(
				pageRequestDto.getType(), 
				pageRequestDto.getKeyword(), 
				pageRequestDto.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDto<>(result, fn);
	}

	@Override
	public BoardDto get(Long bno) {
		Object result = boardRepo.getBoardByBno(bno);
		Object[] arr = (Object[]) result;
		return entityToDto((Board)arr[0],
				(Member)arr[1], (Long)arr[2]);
	}

	

}
