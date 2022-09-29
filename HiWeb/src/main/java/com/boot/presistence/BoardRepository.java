package com.boot.presistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
