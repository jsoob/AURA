package com.aura.www.action.admin.emp;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectEmpAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// comm 설정
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 사원관리 페이지"); // 웹 제목?
		map.put("category", "emp"); // 카테고리 찾는 key
		map.put("categoryName", "사원관리"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectEmp"); // 페이지명
		map.put("pagesName", "사원 조회"); // 사용자에게 보여주는 페이지명
		
		req.setAttribute("commAt", map);
		
		EmpVO vo = new EmpVO();
		
		AdminEmpDAO dao = new AdminEmpDAO();
		
		int totalCount = dao.getTotalCount(vo); // 전체수
		
		HashMap<String, Object> page = getPage(totalCount, req);
		
		int limitNo = (int) page.get("limitNo");
		int offsetNo = (int) page.get("offsetNo");
		
		// 전달
		ArrayList<EmpVO> list = dao.selectEmpAllPage(vo, limitNo, offsetNo);
		req.setAttribute("empList", list);
		
		req.setAttribute("page", page);
		
		return "view/admin/selectEmp.jsp";
	}
	
	public HashMap<String, Object> getPage(int totalCount, HttpServletRequest req) {
		HashMap<String, Object> page = new HashMap<String, Object>();

		int recordPerPage = 9; // 한 페이지당 게시물 8
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
		
		// 페이지
		// limit는 0부터 시작함. 
		// 1페이지 시작번호0 끝번호 9
		// 2페이지 시작번호 9 끝번호 18
		// 3페이지 시작번호 18 끝번호 27
		
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
		// Math.max(첫페이지(1), 현재 페이지(currentPage) - 4)
		// 끝 페이지 미세 조정
		endPage = Math.min(totalPage, startPage + 9);
		
//		System.out.println("cp = " + cp);
//		System.out.println("totalCount = " + totalCount);
//		
//		System.out.println("recordPerPage = " + recordPerPage);
//		System.out.println("totalPage = " + totalPage);
//		
//		System.out.println("limitNo = " + limitNo);
//		System.out.println("offsetNo = " + offsetNo);
//		
//		System.out.println("startPage = " + startPage);
//		System.out.println("endPage = " + endPage);
//		
//		System.out.println("prevCnt = " + prevCnt);
//		System.out.println("nextCnt = " + nextCnt);
		
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
