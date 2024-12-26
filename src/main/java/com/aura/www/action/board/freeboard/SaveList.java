package com.aura.www.action.board.freeboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.EmpVO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/savelist")
public class SaveList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FreeBoardDAO dao = new FreeBoardDAO();
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		int login = (loginEmp.getEmpNo());
		ArrayList<FreeBoardVO> list = dao.saveList(login);
		
		JSONArray saveListArray = new JSONArray();

		for (FreeBoardVO fbvo : list) {
			JSONObject saveListObj = new JSONObject();

			saveListObj.put("freeBNo", fbvo.getFreeBNo());
			saveListObj.put("freeBTitle", fbvo.getFreeBTitle());
			saveListObj.put("freeBContent", fbvo.getFreeBContent());
			saveListObj.put("freeBView", fbvo.getFreeBView());
			saveListObj.put("freeBNotice", fbvo.getFreeBNotice());
			saveListObj.put("freeBStatus", fbvo.getFreeBStatus());
			saveListObj.put("freeBPblc", fbvo.getFreeBPblc());
			saveListObj.put("freeBCrtr", fbvo.getFreeBCrtr());
			saveListObj.put("createDate", fbvo.getCreateDate());
			saveListObj.put("updateDate", fbvo.getUpdateDate());

			saveListArray.add(saveListObj);
		}
		PrintWriter out = resp.getWriter();
		out.println(saveListArray.toJSONString());
		
		
	}
}
