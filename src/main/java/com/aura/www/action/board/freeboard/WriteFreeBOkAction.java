package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WriteFreeBOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터값 가져와서 db에 저장
		
		String freeBTitle = req.getParameter("freeBTitle");
		String freeBContent = req.getParameter("freeBContent");
		int freeBNotice = Integer.parseInt(req.getParameter("freeBNotice"));
		int freeBStatus = Integer.parseInt(req.getParameter("freeBStatus"));
		int freeBPblc = Integer.parseInt(req.getParameter("freeBPblc"));
		HttpSession session = req.getSession();
		int freeBCrtr = (int)session.getAttribute("loginEmp");
		
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
