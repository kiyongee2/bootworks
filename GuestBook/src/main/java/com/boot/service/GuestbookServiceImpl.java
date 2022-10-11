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
import com.boot.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor  //생성자 주입(final 키워드 사용)
@Log4j2
@Service
public class GuestbookServiceImpl implements GuestbookService{
	
	private final GuestbookRepository repository;

	@Override
	public Long register(GuestbookDto dto) {
		log.info("DTO..............................");
		log.info(dto);
		
		Guestbook entity = dtoToEntity(dto);  //dto를 엔티티로 변환 메서드 호출
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getGno();
	}

	@Override
	public PageResultDto<GuestbookDto, Guestbook> 
			getList(PageRequestDto requestDto){
		Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
		Page<Guestbook> result = repository.findAll(pageable);
		Function<Guestbook, GuestbookDto> fn = (entity -> entityToDto(entity));
		return new PageResultDto<>(result, fn);
	}

	@Override
	public GuestbookDto read(Long gno) {
		Optional<Guestbook> result = repository.findById(gno);
		
		//찾아온 객체가 있으면 entityToDto를 호출 아니면 null 반환(삼항 연산자)
		return result.isPresent() ? entityToDto(result.get()) : null;
	}
}
