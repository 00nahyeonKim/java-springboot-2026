package com.pknu26.studygroup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pknu26.studygroup.dto.Board;

// resources/mapper/BoardMapper.xml과 매핑
@Mapper
public interface BoardMapper {

    List<Board> findAll(@Param("offset") int offset, @Param("size") int size);

    Board findById(Long boardId);

    int insertBoard(Board board);

    int updateBoard(Board board);

    int deleteBoard(Long board);

    int getTotalCount(); 

}
