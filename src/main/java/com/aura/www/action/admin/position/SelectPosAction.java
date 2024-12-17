package com.aura.www.action.admin.position;

import java.util.ArrayList;

import com.aura.www.action.Action;
import com.aura.www.dao.PositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectPosAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		PositionDAO dao = new PositionDAO();
		ArrayList<PositionVO> list = dao.selectPosAll();
		
		req.setAttribute("list", list);
		
		
		return "view/admin/selectPos.jsp";
	}

}
