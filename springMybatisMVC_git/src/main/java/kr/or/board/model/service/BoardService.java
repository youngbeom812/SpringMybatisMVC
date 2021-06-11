package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.File;

@Service
public class BoardService {

   @Autowired
   private BoardDao dao;
   @Transactional
public int insertBoard(Board b, ArrayList<File> fileList) {
	int result1 = dao.insertBoard(b);
	int result = 0;		//최종 결과처리할 것
	if(result1 > 0) {
		int boardNo = dao.selectBoardNo();
		for(File f : fileList) {
			f.setBoardNo(boardNo);	//보드넘버 받아와서 
			result += dao.insertFile(f);	//파일 수만큼 for문 반복하고 그 수만큼 dao왔다갔다
		}
	}else {//게시판 안드갔다! 그러면 파일리스트 안해도 됌
		return -1;		//그러면 바로 return -1
	}
	
	return result;
}

public ArrayList<Board> printAllBoard() {
	List list = dao.printAllBoard();
	return (ArrayList<Board>)list;
}

public Board selectOneBoard(int boardNo) {
	return dao.selectOneBoard(boardNo);
}

}