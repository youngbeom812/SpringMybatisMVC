package kr.or.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	//private JdbcTemplate jdbcTemplate;
	private SqlSessionTemplate sqlSession;
	
	  public MemberDao() { 
		  super(); 
		  System.out.println("MemberDao 호출완료"); 
		  }
	 
	
	public Member selectOneMember(Member m) {
		Member member = sqlSession.selectOne("member.selectOneMember", m);	//selectone이면 바로 그 타입 그대로 리턴, 리스트면 그 안에 들어가는 타입을 리절트타입으로 써준다.
		return member;
		/*
		 * //쿼리문 String query =
		 * "select * from member where member_id = ? and member_pw = ?"; Object[] params
		 * = {m.getMemberId(),m.getMemberPw()}; //위치홀더에 들어갈 값 배열에 순서에 맞게 넣어준다 //조회 결과와
		 * 상관없이 무조건 List타입으로 List list = jdbcTemplate.query(query, params, new
		 * MemberRowMapper()); //쿼리, 파람배열, 조회된 결과를 멤버로우랩퍼로 묶어줄거 return list;
		 */
	}
	
	public int insertMember(Member m) {
		int result = sqlSession.insert("member.insertMember", m);
		return result;
		/*
		 * String query = "insert into member values(?,?,?,?,?,?)"; Object[] params =
		 * {m.getMemberId(),m.getMemberPw(),m.getMemberName(),m.getPhone(),m.getAddress(
		 * ),m.getGender()}; int result = jdbcTemplate.update(query,params); return
		 * result;
		 */
	}
	
	public Member searchId(Member m) {
		Member member = sqlSession.selectOne("member.searchId", m);
		return member;
		/*
		 * String query = "select * from member where member_name = ? and phone = ?";
		 * Object [] params = {m.getMemberName(),m.getPhone()}; List list =
		 * jdbcTemplate.query(query, params, new MemberRowMapper()); return list;
		 */
	}
	
	public Member searchPw(Member m) {
		Member member = sqlSession.selectOne("member.searchPw", m);
		return member;
		/*
		 * String query = "select * from member where member_id = ? and phone = ?";
		 * Object [] params = {m.getMemberId(), m.getPhone()}; List list =
		 * jdbcTemplate.query(query, params, new MemberRowMapper()); return list;
		 */
	}
	
	public int deleteMember(String memberId) {
		int result = sqlSession.delete("member.deleteMember",memberId);
		return result;
		/*
		 * String query = "delete from member where member_id = ?"; Object [] params =
		 * {memberId}; int result = jdbcTemplate.update(query,params); return result;
		 */
	}
	
	public Member selectOneMember(String memberId) {
		Member member = sqlSession.selectOne("member.selectOneMember", memberId);
		return member;
		/*
		 * String query = "select * from member where member_id = ?"; Object [] params =
		 * {memberId}; List list = jdbcTemplate.query(query, params, new
		 * MemberRowMapper()); return list;
		 */
	}
	
	public int memberUpdate(Member member) {
		int result = sqlSession.update("member.updateMember", member);
		return result;
		/*
		 * String query =
		 * "update member set phone=?,address=?,gender=? where member_id = ?"; Object []
		 * params =
		 * {member.getPhone(),member.getAddress(),member.getGender(),member.getMemberId(
		 * )}; int result = jdbcTemplate.update(query,params); return result;
		 */
	}
	
	public List selectAllMember() {
		List<Member> list = sqlSession.selectList("member.selectAllMember");
		return list;
		/*
		 * String query = "select * from member"; List list = jdbcTemplate.query(query,
		 * new MemberRowMapper()); return list;
		 */
	}
	
	public int allMemberCount() {
		return sqlSession.selectOne("member.allMemberCount");
		/*
		 * String query = "select count(*) from member"; int result =
		 * jdbcTemplate.queryForObject(query, int.class); //카운트 해야하는 단일 행 쓸 땐 이걸 써야한다
		 * (query, 리턴타입) int타입으로 변환해줌 int.class가 return result;
		 */
	}
	
	public int pwChangeMember(Member m) {
		int result = sqlSession.update("member.pwChangeMember",m);
		return result;
		/*
		 * String query = "update member set member_pw = ? where member_id = ?"; Object
		 * [] params = {m.getMemberPw(),m.getMemberId()}; int result =
		 * jdbcTemplate.update(query,params); return result;
		 */
	}
	
}
