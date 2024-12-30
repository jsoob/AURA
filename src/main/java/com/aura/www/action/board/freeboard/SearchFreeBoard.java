package com.aura.www.action.board.freeboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

@WebServlet("/freeboard2")
public class SearchFreeBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FreeBoardDAO dao = new FreeBoardDAO();

		FreeBoardVO vo = new FreeBoardVO();

		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();

		// 검색키워드가져오기
		String search = req.getParameter("search");
		String searchWord = req.getParameter("searchWord");
		
		// 정렬기준
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
			vo.setEmpName(searchWord);
		}


		System.out.println("vo : " + vo);
		
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		vo.setEmpNo(loginEmp.getEmpNo());


		
		int total = dao.getTotalCountSearch(vo);
		System.out.println("total : " + total);
		HashMap<String, Object> page = getPage(total, req);
	      
	      int limitNo = (int) page.get("limitNo");
	      int offsetNo = (int) page.get("offsetNo");
	      
		
		// 검색결과 가져오기
		list = dao.searchFreeBoard(vo,order,limitNo,offsetNo);

		System.out.println(list.size());
		
		JSONArray freeBoardArray = listmap_to_json(list);
		JSONObject pageObject = new JSONObject(page);
		
		JSONObject obj = new JSONObject();
	      obj.put("freeBoardArray", freeBoardArray); // 리스트
	      obj.put("pageObject", pageObject); // 페이징처리
		
//		PrintWriter out = resp.getWriter();
//		out.println(freeBoardArray.toJSONString());
	      resp.getWriter().print(obj.toJSONString());
	      resp.setContentType("application/json; charset=UTF-8");

	}
	
	private JSONArray listmap_to_json(List<FreeBoardVO> list)
    {       
        JSONArray json_arr=new JSONArray();
        for (FreeBoardVO vo : list) {
            JSONObject json_obj = new JSONObject();
            
			Field[] fields = vo.getClass().getDeclaredFields();
			for(int i=0; i <fields.length; i++){
				fields[i].setAccessible(true);
				
				String key = fields[i].getName();
				Object value = null;
				try {
					value = fields[i].get(vo);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				json_obj.put(key,value);
			}
            json_arr.add(json_obj);
        }
        return json_arr;
    }
	
	
	public HashMap<String, Object> getPage(int totalCount, HttpServletRequest req) {
	      HashMap<String, Object> page = new HashMap<String, Object>();

	      int recordPerPage = 12; // 한 페이지당 게시물 10
	      // 총 페이지수 301/8 ==> 37 38
	      int totalPage = (totalCount%recordPerPage == 0) ? 
	            (totalCount/recordPerPage) : (totalCount/recordPerPage)+1;
	      // 현재 페이지 번호
	      int currentPage = -1;
	      // 현재 페이지 번호 가져오기
	      String cp = req.getParameter("cp");
	      
	      if(cp == null) {
	         currentPage = 1;
	      } else {
	         currentPage = Integer.parseInt(cp);
	      }
	      
	      // 이전 페이지
	      int prevCnt = (currentPage > 1) ? prevCnt=currentPage-1 : currentPage;
	      // 다음 페이지
	      int nextCnt = (currentPage < totalPage) ? currentPage+1 : currentPage;
	      
	      // 페이지 가져오는 갯수
	      int limitNo = recordPerPage;
	      // 페이지 가져오는 idx
	      int offsetNo = (currentPage-1)*recordPerPage;
	      
	      // 시작 페이지 번호
	      int startPage = 1;
	      // 끝 페이지 번호(맨 마지막 페이지 번호)
	      int endPage = totalPage;
	      
	      // 시작 페이지 미세조정
	      startPage = Math.max(1, currentPage - 4);
	      // 끝 페이지 미세 조정
	      endPage = Math.min(totalPage, startPage + 9);
	      
	      page.put("cp", cp);
	      page.put("totalCount", totalCount);
	      
	      page.put("recordPerPage", recordPerPage);
	      page.put("totalPage", totalPage);
	      
	      page.put("limitNo", limitNo);
	      page.put("offsetNo", offsetNo);
	      
	      page.put("startPage", startPage);
	      page.put("endPage", endPage);
	      
	      page.put("prevCnt", prevCnt);
	      page.put("nextCnt", nextCnt);
	      
	      return page;
	   }
	
	
}
