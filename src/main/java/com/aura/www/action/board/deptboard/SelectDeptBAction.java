package com.aura.www.action.board.deptboard;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SelectDeptBAction implements Action {
	private int userDeptNo; // 로그인한 사용자의 부서 ID

	public SelectDeptBAction(int userDeptNo) {
		this.userDeptNo = userDeptNo;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	    DeptBoardDAO dao = new DeptBoardDAO();
	    HttpSession session = req.getSession();
	    EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");

	    ArrayList<DeptBoardVO> list;
	    String deptName;

	    if (loginEmp.getEmpNo() == 2024000) { 
	        // 관리자는 모든 데이터 조회
	        list = dao.selectAll();
	        deptName = "전체 부서"; // 관리자 화면용
	    } else {
	        // 일반 사용자는 본인 부서 게시판만 조회
	        list = dao.selectByDeptNo(loginEmp.getDeptNo());
	        deptName = dao.getDeptNameByDeptNo(loginEmp.getDeptNo()); // 부서명 가져오기
	    }

	    int totalCount = list.size();

	    req.setAttribute("list", list);
	    req.setAttribute("totalCount", totalCount);

	    HashMap<String, String> map = new HashMap<>();
	    map.put("title", deptName + " 부서 게시판 페이지"); // 부서명 포함
	    map.put("category", "deptboard");
	    map.put("categoryName", deptName + " 부서 게시판"); // 사용자 표시 이름
	    map.put("pages", "selectDeptB");
	    map.put("pagesName", deptName + " 부서 게시판");

	    req.setAttribute("commAt", map);

	    return "view/board/deptboard/selectDeptB.jsp";
	}
}