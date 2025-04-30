package com.lgdx.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lgdx.entity.Board;

@Mapper // MyBatis가 해당 Mapper를 찾기위한 맵핑
public interface BoardMapper {
	// Spring과 MyBatis를 연결해주는 중간연결체
	// Spring에서는 MyBatis에게 요청하고 싶은 기능을
	// 이곳에 작성한다
	@Select("SELECT * FROM BOARD ORDER BY INDATE DESC")
	public ArrayList<Board> boardList();

	@Insert("INSERT INTO BOARD(ID, FILENAME, TITLE, CONTENTS, WRITER) VALUES(#{id}, #{filename}, #{title}, #{contents}, #{writer})")
	public void boardInsert(Board vo);

	@Select("SELECT * FROM BOARD WHERE IDX = #{idx}")
	public Board boardContents(int idx);

	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
	public void boardDelete(int idx);

	@Update("UPDATE BOARD SET TITLE = #{title}, CONTENTS = #{contents}, WRITER = #{writer} WHERE IDX = #{idx}")
	public void boardUpdate(Board vo);

	@Update("UPDATE BOARD SET COUNT = COUNT + 1 WHERE IDX = #{idx}")
	public void boardCount(int idx);

	
}














