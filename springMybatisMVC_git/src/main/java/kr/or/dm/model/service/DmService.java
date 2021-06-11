package kr.or.dm.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.dm.model.dao.DmDao;
import kr.or.dm.model.vo.Dm;

@Service
public class DmService {
	@Autowired
	private DmDao dao;

	public ArrayList<Dm> selectGetDmList(String memberId) {
		List<Dm> list = dao.selectGetDmList(memberId);
		return (ArrayList<Dm>)list;
	}

	public ArrayList<Dm> selectSendDmList(String memberId) {
		List<Dm> list = dao.selectSendDmList(memberId);
		return (ArrayList<Dm>)list;
	}
	@Transactional
	public int sendDm(Dm dm) {		
		return dao.sendDm(dm);
	}
}
