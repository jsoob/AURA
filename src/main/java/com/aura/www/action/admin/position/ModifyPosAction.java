package com.aura.www.action.admin.position;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyPosAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/view/admin/modifyPos.jsp";
	}

}
