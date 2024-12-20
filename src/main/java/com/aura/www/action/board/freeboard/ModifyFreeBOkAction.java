package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyFreeBOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String fbno = req.getParameter("freeBNo");
		String freeBTitle = req.getParameter("freeBTitle");
		String freeBContent = req.getParameter("freeBContent");
		String fbn = req.getParameter("freeBNotice");
		String fbp = req.getParameter("freeBPblc");
		int freeBNotice=0;
		int freeBStatus=1;
		
		int freeBNo = Integer.parseInt(fbno);
		
		if(fbn != null) {
			freeBNotice = Integer.parseInt(fbn);	
		}
		int freeBPblc = Integer.parseInt(fbp);

		FreeBoardDAO dao = new FreeBoardDAO();

		FreeBoardVO vo = new FreeBoardVO();
		
		vo.setFreeBNo(freeBNo);
		vo.setFreeBTitle(freeBTitle);
		vo.setFreeBContent(freeBContent);
		vo.setFreeBNotice(freeBNotice);
		vo.setFreeBStatus(freeBStatus);
		vo.setFreeBPblc(freeBPblc);
		
		dao.updateOne(vo);
		
		
		return "freeboard?cmd=selectFreeB";
	}

}
