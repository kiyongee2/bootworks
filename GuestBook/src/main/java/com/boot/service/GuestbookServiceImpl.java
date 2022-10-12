package com.boot.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.GuestbookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Guestbook;
import com.boot.entity.QGuestbook;
import com.boot.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor  //생성자 주입(final 키워드 사용)
@Log4j2
@Service
public class GuestbookServiceImpl implements GuestbookService{
	
	private final GuestbookRepository repository;

	//글 등록
	@Override
	public Long register(GuestbookDto dto) {
		log.info("DTO..............................");
		log.info(dto);
		
		Guestbook entity = dtoToEntity(dto);  //dto를 엔티티로 변환 메서드 호출
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getGno();
	}

	//게시글 목록 보기
	@Override
	public PageResultDto<GuestbookDto, Guestbook> 
			getList(PageRequestDto requestDto){
		//페이지 처리
		Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
		
		//검색 처리
		BooleanBuilder booleanBuilder = getSearch(requestDto);
		
		Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);
		
		Function<Guestbook, GuestbookDto> fn = (entity -> entityToDto(entity));
		
		return new PageResultDto<>(result, fn);
	}

	//게시글 상세 보기
	@Override
	public GuestbookDto read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		
		//찾아온 객체가 있으면 entityToDto를 호출 아니면 null 반환(삼항 연산자)
		return result.isPresent() ? entityToDto(result.get()) : null;
	}
	
	//검색 처리
	private BooleanBuilder getSearch(PageRequestDto requestDto) {
		String type = requestDto.getType();
		String keyword = requestDto.getKeyword();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		BooleanExpression expression = qGuestbook.gno.gt(0L);  //gno > 0
		booleanBuilder.and(expression);
		
		//검색 조건이 없는 경우(null 처리)
		if(type == null || type.trim().length() == 0) {
			return booleanBuilder;
		}

		//검색 조건 작성하기
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		if(type.contains("t")) {
			conditionBuilder.or(qGuestbook.title.contains(keyword));
		}
		if(type.contains("c")) {
			conditionBuilder.or(qGuestbook.content.contains(keyword));
		}
		if(type.contains("w")) {
			conditionBuilder.or(qGuestbook.writer.contains(keyword));
		}
		
		//모든 조건 통합(페이지 + 검색)
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
	}
}










