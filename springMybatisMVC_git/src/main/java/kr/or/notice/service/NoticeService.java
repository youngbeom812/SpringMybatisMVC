package kr.or.notice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.notice.model.dao.NoticeDao;
import kr.or.notice.model.vo.Notice;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;

	public NoticeService() {
		super();
		System.out.println("notice서비스 호출!");
	}
	
	public ArrayList<Notice> noticeList() {
		List list = dao.noticeList();
		return (ArrayList<Notice>)list;
	}

	public Notice selectOneNotice(int noticeNo) {
		List list = dao.selectOneNotice(noticeNo);
		Notice n = null;
		if(!list.isEmpty()) {			
			n = (Notice)list.get(0);
		}
		return n;
	}

	public int noticeDelete(int noticeNo) {
		int result = dao.noticeDelete(noticeNo);
		return result;
	}

	public int noticeUpdate(Notice n) {
		int result = dao.noticeUpdate(n);
		return result;
	}

	public int writeNotice(Notice n) {
		int result = dao.writeNotice(n);
		return result;
	}
	
}
