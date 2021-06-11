package kr.or.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.notice.model.vo.Notice;
import kr.or.notice.model.vo.NoticeRowMapper;

@Repository // 자동으로 만드는 조건
public class NoticeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NoticeDao() {
		super();
		System.out.println("noticeDAO 호출!");
	}
	
	public List noticeList() {
		String query = "select * from notice";
		List list = jdbcTemplate.query(query, new NoticeRowMapper());
		return list;
	}

	public List selectOneNotice(int noticeNo) {
		String query = "select * from notice where notice_no = ?";
		Object[] params = { noticeNo };
		List list = jdbcTemplate.query(query, params, new NoticeRowMapper());
		return list;
	}

	public int noticeDelete(int noticeNo) {
		String query = "delete from notice where notice_no=?";
		Object[] params = { noticeNo };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public int noticeUpdate(Notice n) {
		String query = " update notice set notice_title = ?, notice_content = ? where notice_no = ?";
		Object[] params = { n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeNo() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}

	public int writeNotice(Notice n) {
		String query = " insert into notice values(notice_seq.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
		Object[] params = { n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeWriter() };
		int result = jdbcTemplate.update(query, params);
		return result;
	}
	
}
