package com.aura.www.action.attendance;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminPositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertWorkOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터값 가져와서 db에 저장
		String eno = req.getParameter("empNo");
		String empName = req.getParameter("empName");

		if (eno != null) {
			int empNo = Integer.parseInt(eno);
			AdminPositionDAO dao = new AdminPositionDAO();

			PositionVO vo = new PositionVO();

			vo.setPosNo(empNo);
			vo.setPosName(empName);

			dao.insertPos(vo);
		}

		return "admin?cmd=selectWork";
	}

}
