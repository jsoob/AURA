package com.aura.www.action.board.freeboard;

import java.io.IOException;

import com.aura.www.dao.FreeBoardFileDAO;
import com.aura.www.vo.FreeBoardFileVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteFBFile")
public class DeleteFBFile extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String n = req.getParameter("no");
		if(n!=null) {
			int fileNo = Integer.parseInt(n);
			FreeBoardFileDAO dao = new FreeBoardFileDAO();
			dao.deleteFileOne(fileNo);
			
		}
	}
}
