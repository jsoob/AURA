package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.dept.DeleteDeptAction;
import com.aura.www.action.admin.dept.InsertDeptAction;
import com.aura.www.action.admin.dept.InsertDeptOkAction;
import com.aura.www.action.admin.dept.ModifyDeptAction;
import com.aura.www.action.admin.dept.ModifyDeptOkAction;
import com.aura.www.action.admin.dept.SelectDeptAction;
import com.aura.www.action.admin.emp.InsertEmpAction;
import com.aura.www.action.admin.emp.SelectEmpAction;
import com.aura.www.action.admin.management.AdminLoadAction;
import com.aura.www.action.admin.position.SelectPosAction;
import com.aura.www.action.main.MainAction;

import com.aura.www.action.admin.position.DeletePosAction;
import com.aura.www.action.admin.position.InsertPosAction;
import com.aura.www.action.admin.position.InsertPosOkAction;
import com.aura.www.action.admin.position.ModifyPosAction;
import com.aura.www.action.admin.position.ModifyPosOkAction;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";
//		System.out.println("cmd = " + cmd);
		
		// 메인
		if(cmd==null) {
	    	Action bc = new MainAction();
	        url = bc.execute(req, resp);
	    } else if (cmd.equals("adminLoad")) {
	    	Action bc = new AdminLoadAction();
	        url = bc.execute(req, resp);
	    } 
		
	    // 직급 관리 
		
	    else if (cmd.equals("selectPos")) {
			Action action = new SelectPosAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("insertPos")) {
            Action action = new InsertPosAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("insertPosOk")) {
            Action action = new InsertPosOkAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("modifyPos")) {
            Action action = new ModifyPosAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("modifyPosOk")) {
            Action action = new ModifyPosOkAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("deletePos")) {
            Action action = new DeletePosAction();
            url = action.execute(req, resp);
        } 
        // 부서 관리 
        
        else if (cmd.equals("selectDept")) {
			Action action = new SelectDeptAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("insertDept")) {
            Action action = new InsertDeptAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("insertDeptOk")) {
            Action action = new InsertDeptOkAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("modifyDept")) {
            Action action = new ModifyDeptAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("modifyDeptOk")) {
            Action action = new ModifyDeptOkAction();
            url = action.execute(req, resp);
        } else if (cmd.equals("deleteDept")) {
            Action action = new DeleteDeptAction();
            url = action.execute(req, resp);
        }
		
		// 사원 관리
		else if (cmd.equals("selectEmp")) {
			Action action = new SelectEmpAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("insertEmp")) {
			Action action = new InsertEmpAction();
			url = action.execute(req, resp);
		}
		
		if(url=="main") {
	    	resp.sendRedirect("main");
	    } else {
	    	RequestDispatcher rd = req.getRequestDispatcher(url);
	    	rd.forward(req, resp);
	    }
	}
}
