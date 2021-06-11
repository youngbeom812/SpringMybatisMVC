package kr.or.board.model.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardRowMapper;
import kr.or.board.model.vo.File;
import kr.or.board.model.vo.FileRowMapper;

@Repository
public class BoardDao {

   @Autowired
   //private JdbcTemplate jdbcTemplate;
   //이제 mybatis라서 jdbctemplateㄴㄴ, sqlsessiontemplate 쓸거임
   private SqlSessionTemplate sqlSession;

public int insertBoard(Board b) {
	int result = sqlSession.insert("board.insertBoard",b);
	return result;
	/*
	 * String query =
	 * "insert into board values(board_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))"
	 * ; Object [] params =
	 * {b.getBoardTitle(),b.getBoardWriter(),b.getBoardContent()}; int result =
	 * jdbcTemplate.update(query,params); return result;
	 */
}

public int selectBoardNo() {
	int result = sqlSession.selectOne("board.selectBoardNo");	//셀렉원으로 한개의 정수값 받아오는거
	return result;
	/*
	 * String query = "select max(board_no) from board"; //max(컬럼명) 하면 어차피 시퀀스 써서
	 * 하기때문에 맥스로 찾아오면 가장 최근 걸 가져오겠죠 int boardNo = jdbcTemplate.queryForObject(query,
	 * int.class); //정수 하나만 받아오면 쿼리뽀오브젝트써서 받아오면 된다 return boardNo;
	 */
}

public int insertFile(File f) {
	int result = sqlSession.insert("board.insertFile",f);
	return result;
	/*
	 * String query = "insert into file_tbl values(file_seq.nextval,?,?,?)"; Object
	 * [] params = {f.getFilename(),f.getFilepath(),f.getBoardNo()}; int result =
	 * jdbcTemplate.update(query,params); return result;
	 */
}

public List printAllBoard() {
	List<Board> list = sqlSession.selectList("board.selectBoardList");
	return list;
	/*
	 * String query = "select * from board"; List list =
	 * jdbcTemplate.query(query,new BoardRowMapper()); return list;
	 */
}

public Board selectOneBoard(int boardNo) {
	return sqlSession.selectOne("board.selectOneBoard2",boardNo);
}



}