package kr.or.board.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;

@Controller
public class BoardController {
   @Autowired
   private BoardService service;
   
   @RequestMapping(value="/boardWriteFrm.do")
   public String boardWriteFrm() {
      return "board/boardWriteFrm";
   }
   
   @RequestMapping(value="/boardWrite.do")
   public String boardWrite(Board b , MultipartFile files[] , HttpServletRequest request, Model model) {	//체크박스마냥 같은 동일한 name에 여러가지 값을 스트링배열로 받아오기 때문에	 
	   								  //멀티파트파일이 배열의 길이는 1을 가진다 첨부를 하지 않더라도
      
      //파일 목록을 저장할 어레이리스트(근데 같은 명이 있어서 import가 이로케 들어감)
      ArrayList<kr.or.board.model.vo.File> fileList = new ArrayList<kr.or.board.model.vo.File>();
      
      //파일이 존재하지 않아도 ㅂ받아온 file의 배열의 길이는 무조건 1을가짐 첨부가 되면 파일 수만큼 1+x 되겠죠
      if(files[0].isEmpty()) {
    	  //첨부파일이 없는경우
      }else {
    	  //첨부파일이 있는 경우
    	  
    	  //파일처리
    	  //getRealPath() -> webapp 폴더
    	  String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
    	  
    	  for(MultipartFile file : files) {    	//파일들 처리를 위한 for문	 받아온 file하나씩을 files 배열에 하나하나 넣어줘ㅏ야지	  
    		  //실제 유저가 올린 파일명(filename)
    		  String filename = file.getOriginalFilename();
    		  
    		  //유저가 올린 파일명을 마지막. 기준으로 분리		ex) test.txt ->test_1.txt /img01.jpg -> img01_1.jpg 이렇게 저장 됐었다.
    		  //그래서 제일먼저 하는 일이 test 와 .txt이렇게 나눌건데 만약 겹치면 뒤에 넘버링 할거다   
    		  String onlyFilename = filename.substring(0 , filename.indexOf(".")); //0번부터 indexOf가 안에 인자가 몇번째에 있어 ? 라고 그래서 이 전까지 substrting 그니깐 . 전까지 substring이 짤라내 ! 라고 하는거다	==> test 가 들어가고
    		  String extention = filename.substring(filename.indexOf("."));//.부터 끝까지 다 가져와		-->.txt 가 들어가고 
    		  String filepath = null;
    		  int count = 0;
    		  while(true) {	//겹치는 파일명이 있는지 확인하고 겹치는 파일명이 없을 때 까지 반복문 있다면 +1,2,3이런식으로 뒤에 붙인다
    			  if(count == 0) {
    				  filepath = onlyFilename + extention; //-->test.txt 이런식으로 되겠죠
    			  }else {
    				  filepath = onlyFilename + "_" + count + extention; //-->test_1.txt 이런식으로 되겠죠
    			  }
    			  File checkFile = new File(savePath+filepath);
    			  if(!checkFile.exists()) { //체크파일이 존재안한다면 ? 겹치는 파일이 없다면 브레이꾸 있다면 while문으로 계속 돌겠죠
    				  //근데 존재하면 while문이 또 돌겠죠 그래서 test_1.txt 하고 그 파일패스로 다시 이 if문 검사 
    				  break;
    			  }
    			  count++;
    		  }
    		  kr.or.board.model.vo.File f = new kr.or.board.model.vo.File();
    		  f.setFilename(filename);
    		  f.setFilepath(filepath);
    		  fileList.add(f);
    		  System.out.println("filename : "  +filename);
    		  System.out.println("filepath : "  +filepath);
    		  try {
    			  FileOutputStream fos = new FileOutputStream(new File(savePath+filepath));		//파일 업로드하는 로직!
    			  BufferedOutputStream bos = new BufferedOutputStream(fos);
    			  byte[] bytes = file.getBytes();
    			  bos.write(bytes);
    			  bos.close();
    		  } catch (FileNotFoundException e) {
    			  // TODO Auto-generated catch block
    			  e.printStackTrace();
    		  } catch (IOException e) {
    			  // TODO Auto-generated catch block
    			  e.printStackTrace();
    		  }
    	  }//for끝
      }//else끝
      int result = service.insertBoard(b,fileList);
      if(result != -1 && result == fileList.size()) {	//보드테이블에도 잘 들어가고, 파일테이블에도 잘 들어가야지 가능
    	model.addAttribute("msg", "등록성공!");  
      }else {    	  
    	  model.addAttribute("msg", "등록실패..");  
      }
      model.addAttribute("loc", "/");
      return "common/msg";
   }
   
   @RequestMapping(value="/boardList.do")
   public String boardList(Model model) {
	   ArrayList<Board> list = service.printAllBoard();
	   model.addAttribute("list", list);
	   return "board/boardList";
   }
   @RequestMapping(value="/boardView.do")
   public String boardView(int boardNo,Model model) {
	   Board b = service.selectOneBoard(boardNo);
	   System.out.println(b.getFileList());
	   model.addAttribute("b", b);
	   return "board/boardView";
   }
}