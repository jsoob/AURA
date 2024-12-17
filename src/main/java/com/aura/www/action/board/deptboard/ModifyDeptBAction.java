package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ModifyDeptBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		   
		   HttpSession session = req.getSession();
		   
		   if(session.isNew() || session.getAttribute("deleteDeptB") == null)
			   url = "view/dept/deptBoard.jsp";
		  
		  
	      return url;	}

}