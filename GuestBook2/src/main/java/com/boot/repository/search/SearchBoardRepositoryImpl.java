package com.boot.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.boot.entity.Board;
import com.boot.entity.QBoard;
import com.boot.entity.QMember;
import com.boot.entity.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport
implements SearchBoardRepository{

	//생성자(부모 상속)
	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	//검색 기능
	@Override
	public Board search1() {
		log.info("Search1.....................");
		
		QBoard board = QBoard.board;
		QMember member = QMember.member;
		QReply reply = QReply.reply;
		
		//JPQLQuery 인터페이스 사용하기(조인 처리)
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		//게시글 1개 조회
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, 
				member.email, reply.count());
		
		//jpqlQuery.select(board).where(board.bno.eq(1L));
		
		log.info(tuple);
		
		List<Tuple> result = tuple.fetch();
		log.info(result);
		
		return null;
	}//search1() 끝

	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		QBoard board = QBoard.board;
		QMember member = QMember.member;
		QReply reply = QReply.reply;
		
		//JPQLQuery 인터페이스 사용하기(조인 처리)
		JPQLQuery<Board> jpqlQuery = from(board);
		jpqlQuery.leftJoin(member).on(board.writer.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(board, 
				member.email, reply.count());
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression expression = board.bno.gt(0L);
		
		booleanBuilder.and(expression);
		
		if(type != null) {
			String[] typeArr = type.split("");  //t, c, w
			BooleanBuilder conditonBuiler = new BooleanBuilder();
			
			for(String t : typeArr) {
				switch(t) {
				case "t":
					conditonBuiler.or(board.title.contains(keyword));
					break;
				case "c":
					conditonBuiler.or(board.content.contains(keyword));
					break;
				case "w":
					conditonBuiler.or(member.email.contains(keyword));
					break;
				}
			}//for 끝
			//bno > 0 and 검색 조건
			booleanBuilder.and(conditonBuiler);
		}//if 끝
		
		tuple.where(booleanBuilder);
		
		//정렬 처리 추가(Order by)
		
		//tuple.groupBy(board);
		//List<Tuple> result = tuple.fetch();
		
		//log.info(result);
	
		return null;
	}
}
