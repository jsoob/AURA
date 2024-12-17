package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFreeBOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터값 가져와서 db에 저장
		
		String freeBTitle = req.getParameter("freeBTitle");
		String freeBContent = req.getParameter("freeBContent");
		int freeBNotice = Integer.parseInt(req.getParameter("freeBNotice"));
		int freeBStatus = Integer.parseInt(req.getParameter("freeBStatus"));
		int freeBPblc = Integer.parseInt(req.getParameter("freeBPblc"));
		int freeBCrtr = Integer.parseInt(req.getParameter("freeBCrtr"));
		
		FreeBoardDAO dao = new FreeBoardDAO();

		FreeBoardVO vo = new FreeBoardVO();

		vo.setFreeBTitle(freeBTitle);
		vo.setFreeBContent(freeBContent);
		vo.setFreeBNotice(freeBNotice);
		vo.setFreeBStatus(freeBStatus);
		vo.setFreeBPblc(freeBPblc);
		vo.setFreeBCrtr(freeBCrtr);
		
		dao.insertOne(vo);

		return "freeboard?cmd=selectFreeB";
	}

}
