package com.aura.www.action.board.freeboard;

import java.io.IOException;

import com.aura.www.dao.FreeBoardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteTempSave")
public class DeleteTempSave extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fbn = req.getParameter("freeBNo");
		System.out.println(fbn);
		
		if(fbn != null) {
			int freeBNo = Integer.parseInt(fbn);
			FreeBoardDAO dao = new FreeBoardDAO();
			dao.deleteOne(freeBNo);
		}
				
	}
}
