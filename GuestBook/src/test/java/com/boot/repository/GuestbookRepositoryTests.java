package com.boot.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Guestbook;

@SpringBootTest
public class GuestbookRepositoryTests {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	//300개 데이터 저장
	/*@Test
	public void insertData() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Guestbook guestbook = Guestbook.builder()
					.title("Title...." + i)
					.content("Content..." + i)
					.writer("user" + (i % 10))
					.build();
			
			System.out.println(guestbookRepository.save(guestbook));
		});
	}*/
	
	@Test
	public void updateTest() {
		Optional<Guestbook> result = guestbookRepository.findById(300L);
		
		if(result.isPresent()) {
			Guestbook guestbook = result.get();
			
			guestbook.changeTitle("제목 수정...");
			guestbook.changeContent("내용 수정....");
			
			guestbookRepository.save(guestbook);
		}
	}
	
}









