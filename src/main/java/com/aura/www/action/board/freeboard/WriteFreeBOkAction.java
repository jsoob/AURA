package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.EmpVO;
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
		String fbn = req.getParameter("freeBNotice");
		String fbp = req.getParameter("freeBPblc");
		
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		int freeBCrtr = loginEmp.getEmpNo();
		int freeBNotice=0;	// default값=0, 공지를 누르지 않았다면 fbn==null
		int freeBStatus=1; // 등록하면 status = 1
		
		if(fbn != null) { // 공지가 눌렸다면
			freeBNotice = Integer.parseInt(fbn);		 
		}
			int freeBPblc = Integer.parseInt(fbp);

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
