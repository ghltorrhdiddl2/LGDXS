package com.lgdx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lgdx.entity.Member;

@Mapper
public interface MemberMapper {
	
	@Insert("INSERT INTO MEMBER VALUES(#{id},#{pw},#{nick})")
	public void join(Member vo);

	@Select("SELECT * FROM MEMBER WHERE ID=#{id} AND PW=#{pw}")
	public Member login(Member vo);

	@Update("UPDATE MEMBER SET PW=#{pw}, NICK=#{nick} WHERE ID=#{id}")
//	public Member update(Member vo);
	public int update(Member vo);
}
