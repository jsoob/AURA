package com.aura.www.action.admin.position;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminPositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyPosOkAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String pno = req.getParameter("posNo");
		
		if(pno != null) {
			int posNo = Integer.parseInt(pno);
			String posName = req.getParameter("posName");
			
			AdminPositionDAO dao = new AdminPositionDAO();
			PositionVO vo = dao.selectPosOne(posNo);
			vo.setPosName(posName);
			dao.updatePos(vo);
			
		}
		
		return "admin?cmd=selectPos";
	}

}
