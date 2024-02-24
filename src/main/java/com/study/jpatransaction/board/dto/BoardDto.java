package com.study.jpatransaction.board.dto;

import com.study.jpatransaction.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {

  private Long id;
  private String title;
  private String contents;

  public Board toEntity(){
    return Board.builder()
        .title(title)
        .contents(contents)
        .build();
  }
}
