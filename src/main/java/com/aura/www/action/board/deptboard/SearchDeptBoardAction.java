package com.aura.www.action.board.deptboard;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SearchDeptBoardAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	    try {
	        String search = req.getParameter("search"); // 검색 박스  
	        String searchWord = req.getParameter("searchWord"); // 검색
	        String order = req.getParameter("order"); // 정렬 
	        HttpSession session = req.getSession();
	        EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");
	        
	        int empNo = loginEmp.getEmpNo();
	        int deptNo = loginEmp.getDeptNo();
	        // 페이지와 레코드 기본값 설정
	        int page = 1;
	        int recordPerPage = 9;

	        try {
	            page = Integer.parseInt(req.getParameter("page"));
	        } catch (NumberFormatException e) {
	            // 페이지 값이 없거나 잘못된 경우 기본값 사용
	        }

	        try {
	            recordPerPage = Integer.parseInt(req.getParameter("recordPerPage"));
	        } catch (NumberFormatException e) {
	            // recordPerPage 기본값 사용
	        }

	        DeptBoardDAO dao = new DeptBoardDAO();
	        int startIndex = (page - 1) * recordPerPage;

	        // 검색 결과 가져오기
	        ArrayList<DeptBoardVO> list = dao.searchDeptBoard(empNo, deptNo, search, searchWord, order, startIndex, recordPerPage);
	        System.out.println("검색된 게시글 수: " + list.size()); // 검색된 데이터 확인

	        int totalRecords = dao.getTotalCount();
	        int totalPages = (int) Math.ceil((double) totalRecords / recordPerPage);

	        // JSON 생성
	        JSONArray jsonArray = new JSONArray();
	     // JSON 생성 부분
	        for (DeptBoardVO vo : list) {
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("deptBNo", vo.getDeptBNo());
	            jsonObject.put("deptBTitle", vo.getDeptBTitle());
	            jsonObject.put("deptBCrtr", vo.getDeptBCrtr()); // 작성자 사원번호
	            jsonObject.put("createDate", vo.getCreateDate());
	            jsonObject.put("deptBView", vo.getDeptBView());
	            jsonObject.put("deptBPblc", vo.getDeptBPblc());
	            jsonObject.put("deptBNotice", vo.getDeptBNotice());
	            jsonObject.put("empName", vo.getEmpName());
	            System.out.println("JSON 데이터: " + jsonObject); // 디버깅용 출력
	            jsonArray.add(jsonObject);
	        }

	        JSONObject pagination = new JSONObject();
	        pagination.put("currentPage", page);
	        pagination.put("totalPages", totalPages);
	        pagination.put("startPage", Math.max(1, page - 2));
	        pagination.put("endPage", Math.min(totalPages, page + 2));

	        JSONObject result = new JSONObject();
	        result.put("list", jsonArray);
	        result.put("pagination", pagination);

	        // JSON 응답
	        resp.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = resp.getWriter();
	        out.print(result.toJSONString());
	        out.flush();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}