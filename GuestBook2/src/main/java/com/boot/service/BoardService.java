package com.boot.service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;

public interface BoardService {
	
	//게시글 등록
	Long register(BoardDto dto);
	
	//게시글 목록 보기
	PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto);
	
	//게시글 상세 보기
	BoardDto get(Long bno);
	
	//dto에서 Entity로 변환
	default Board dtoToEntity(BoardDto dto) {
		//회원 생성
		Member member = Member.builder().email(dto.getWriterEmail()).build();
		
		//게시글 작성
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		return board;
	}
	
	//Entity에서 dto로 변환
	default BoardDto entityToDto(Board board, Member member, Long replyCount) {
		
		BoardDto boardDto = BoardDto.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())  //int로 변환
				.build();
		return boardDto;		
	}
}





