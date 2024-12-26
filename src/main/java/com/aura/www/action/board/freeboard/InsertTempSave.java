package com.aura.www.action.board.freeboard;

import java.io.IOException;

import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/insertTempSave")
public class InsertTempSave extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String freeBTitle = req.getParameter("freeBTitle");
		String freeBContent = req.getParameter("freeBContent");
		String fbc = req.getParameter("freeBCrtr");
		String fbs = req.getParameter("freeBStatus");
		String fbn = req.getParameter("freeBNotice");
		String fbp = req.getParameter("freeBPblc");
		
		int	freeBCrtr = Integer.parseInt(fbc);
		int	freeBStatus = Integer.parseInt(fbs);
		int	freeBNotice = Integer.parseInt(fbn);
		int	freeBPblc = Integer.parseInt(fbp);
		
		
		System.out.println(freeBTitle);
		System.out.println(freeBContent);
		System.out.println(freeBCrtr);
		System.out.println(freeBStatus);
		System.out.println(freeBNotice);
		System.out.println(freeBPblc);
		
		FreeBoardDAO dao = new FreeBoardDAO();

		FreeBoardVO vo = new FreeBoardVO();
		vo.setFreeBTitle(freeBTitle);
		vo.setFreeBContent(freeBContent);
		vo.setFreeBCrtr(freeBCrtr);
		vo.setFreeBStatus(freeBStatus);
		vo.setFreeBNotice(freeBNotice);
		vo.setFreeBPblc(freeBPblc);
		
		dao.insertOne(vo);
		
	}
}
