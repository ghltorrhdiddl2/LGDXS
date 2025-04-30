package com.lgdx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgdx.entity.Board;
import com.lgdx.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	public List<Board> boardList() {
		return mapper.boardList();
	}

	public Board boardContents(int idx) {
		return mapper.boardContents(idx);
	}

	public void boardInsert(Board vo) { //insert는 return값이 없음!
		mapper.boardInsert(vo);
	}

	public void boardDelete(int idx) {
		mapper.boardDelete(idx);
	}

	public void boardUpdate(Board vo) {
		mapper.boardUpdate(vo);
	}

	public void boardCount(int idx) {
		mapper.boardCount(idx);
	}


}
