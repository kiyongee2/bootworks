package com.boot.persistence;

import org.springframework.data.repository.CrudRepository;

import com.boot.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{
	
}
