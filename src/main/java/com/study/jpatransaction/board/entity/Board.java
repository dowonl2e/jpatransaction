package com.study.jpatransaction.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "tb_board")
@Getter
@NoArgsConstructor
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String contents;
  private LocalDateTime writeDate = LocalDateTime.now();
  private LocalDateTime modifyDate;

  @Builder
  public Board(
      Long id, String title, String contents,
      LocalDateTime writeDate, LocalDateTime modifyDate
  ){
    this.id = id;
    this.title = title;
    this.contents = contents;
    this.writeDate = writeDate;
    this.modifyDate = modifyDate;
  }
}
