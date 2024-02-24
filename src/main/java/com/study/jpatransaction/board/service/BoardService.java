package com.study.jpatransaction.board.service;

import com.study.jpatransaction.board.dto.BoardDto;
import com.study.jpatransaction.board.entity.Board;
import com.study.jpatransaction.board.entity.BoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;


  public Board findById(){
    log.info("======== 10번 게시물 조회 ========");
    return boardRepository.findById((long)10).get();
  }

  @Transactional
  public Long save(BoardDto boardDto){
    return boardRepository.save(boardDto.toEntity()).getId();
  }

  @Transactional(rollbackFor = Exception.class)
  public void multiSaveTransactional() throws Exception {
    for(int i = 0 ; i < 10 ; i++){
      BoardDto boardDto = new BoardDto();
      boardDto.setTitle("JPA 트랜잭션 테스트 제목1");
      boardDto.setContents("JPA 트랜잭션 테스트 내용1");
      boardRepository.save(boardDto.toEntity());

      if(i == 5)
        throw new IOException();
    }
  }

  public void multiSaveNoTransactional() throws Exception {
    for(int i = 0 ; i < 10 ; i++){
      BoardDto boardDto = new BoardDto();
      boardDto.setTitle("JPA 트랜잭션 테스트 제목1");
      boardDto.setContents("JPA 트랜잭션 테스트 내용1");
      boardRepository.save(boardDto.toEntity());
      if(i == 5)
        throw new IOException();
    }
  }

}
