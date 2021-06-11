package kr.or.dm.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.Dm;

@Repository
public class DmDao {
	@Autowired
	private SqlSessionTemplate session;

	public List<Dm> selectGetDmList(String memberId) {
		return session.selectList("dm.selectGetDmList",memberId);
	}

	public List<Dm> selectSendDmList(String memberId) {	
		return session.selectList("dm.selectSendList",memberId);
	}

	public int sendDm(Dm dm) {
		return session.insert("dm.sendDm",dm);
	}
}
