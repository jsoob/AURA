package com.aura.www.action.admin.position;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminPositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertPosOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터값 가져와서 db에 저장
		String pno = req.getParameter("posNo");
		String posName = req.getParameter("posName");

		if (pno != null) {
			int posNo = Integer.parseInt(pno);
			AdminPositionDAO dao = new AdminPositionDAO();

			PositionVO vo = new PositionVO();

			vo.setPosNo(posNo);
			vo.setPosName(posName);

			dao.insertPos(vo);
		}

		return "admin?cmd=selectPos";
	}

}
