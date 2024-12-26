package com.aura.www.action.board.freeboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/freeboard2")
public class SearchFreeBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FreeBoardDAO dao = new FreeBoardDAO();

		FreeBoardVO vo = new FreeBoardVO();

		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();

		// 검색
		// 검색키워드가져오기
		String search = req.getParameter("search");
		String searchWord = req.getParameter("searchWord");

		String order = req.getParameter("order");
		
		
		System.out.println("search : " + search);
		System.out.println("searchWord : " + searchWord);

		// vo에 값 담아주기
		if (searchWord != null && search.equals("title")) {
			vo.setFreeBTitle(searchWord);
			System.out.println(vo);
		} else if (searchWord != null && search.equals("content")) {
			vo.setFreeBContent(searchWord);
		} else if (searchWord != null && search.equals("writer")) {
			vo.setFreeBCrtr(Integer.parseInt(searchWord));
		}


		System.out.println("vo : " + vo);
		// 검색결과 가져오기
		list = dao.searchFreeBoard(vo,order);

		System.out.println(list);

		JSONArray freeBoardArray = new JSONArray();

		for (FreeBoardVO fbvo : list) {
			JSONObject freeboardObj = new JSONObject();

			freeboardObj.put("freeBNo", fbvo.getFreeBNo());
			freeboardObj.put("freeBTitle", fbvo.getFreeBTitle());
			freeboardObj.put("freeBContent", fbvo.getFreeBContent());
			freeboardObj.put("freeBView", fbvo.getFreeBView());
			freeboardObj.put("freeBNotice", fbvo.getFreeBNotice());
			freeboardObj.put("freeBStatus", fbvo.getFreeBStatus());
			freeboardObj.put("freeBPblc", fbvo.getFreeBPblc());
			freeboardObj.put("freeBCrtr", fbvo.getFreeBCrtr());
			freeboardObj.put("createDate", fbvo.getCreateDate());
			freeboardObj.put("updateDate", fbvo.getUpdateDate());

			freeBoardArray.add(freeboardObj);
		}
		PrintWriter out = resp.getWriter();
		out.println(freeBoardArray.toJSONString());

	}
}
