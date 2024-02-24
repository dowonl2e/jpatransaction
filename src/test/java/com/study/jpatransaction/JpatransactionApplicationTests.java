package com.study.jpatransaction;

import com.study.jpatransaction.board.dto.BoardDto;
import com.study.jpatransaction.board.entity.Board;
import com.study.jpatransaction.board.service.BoardService;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class JpatransactionApplicationTests {

	@Autowired
	private HikariDataSource dataSource;

	@Autowired
	private BoardService boardService;

	@Autowired
	private EntityManagerFactory entityManagerFactory;


	@Test
	void contextLoads() {
	}

	@Test
	public void testToPersistenceContext() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		BoardDto boardDto = new BoardDto();
		boardDto.setId((long)21);
		boardDto.setTitle("JPA 트랜잭션 테스트 제목1");
		boardDto.setContents("JPA 트랜잭션 테스트 내용1");
		Board board1 = boardDto.toEntity();
		entityManager.persist(board1);

		transaction.commit();

		entityManager.detach(board1);

		Board board2 = entityManager.find(Board.class, 21);
		assertEquals("board1 == board2", board1, board2);
	}

	@Test
	public void testToHikariDatasource(){
		System.out.println("testToHikariDatasource: " + dataSource);
	}

	@Test
	public void testToSave(){
		BoardDto boardDto = new BoardDto();
		boardDto.setTitle("JPA 트랜잭션 테스트 제목1");
		boardDto.setContents("JPA 트랜잭션 테스트 내용1");
		boardService.save(boardDto);
	}

	@Test
	public void testToMultiSave1() throws Exception {
		boardService.multiSaveTransactional();
	}

	@Test
	public void testToMultiSave2() throws Exception {
		boardService.multiSaveNoTransactional();
	}
}
