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

	    ArrayList<DeptBoardVO> returnList = new ArrayList<>();
	    String deptName;
	    System.out.println("inside selectDeptAction");

	    if (loginEmp.getEmpNo() == 2024000) { 
	        // 관리자는 모든 데이터 조회
	        System.out.println("admin is true");
	        
	        // 모든 부서 중에서 공지사항 3개를 가져옴 
	    	int num1 = -1;
	    	int num2 = -1;
	    	int num3 = -1;
	    	int count = 0;
	    	ArrayList<DeptBoardVO> list1 = dao.selectThreeRecentNotice();
	    	for(DeptBoardVO vo : list1) {
	    		if(count == 0) {
	    			num1 = vo.getDeptBNo();
	    		} else if(count == 1) {
	    			num2 = vo.getDeptBNo();
	    		} else if(count == 2) {
	    			num3 = vo.getDeptBNo();
	    		}
	    		returnList.add(vo);
	    		count++;
	    	}
	    	System.out.println("num1: " + num1);
	    	System.out.println("num2: " + num2);
	    	System.out.println("num3: " + num3);
	        
	    	
	        // 최근 공지사항 3개를 제외한 나머지 게시글을 가져옴 
	    	ArrayList<DeptBoardVO> list2 = dao.getRestOfAllDeptBoard(num1, num2, num3);
	    	for(DeptBoardVO vo : list2) {
	    		returnList.add(vo);
	    	}
	        
	        
	        deptName = "전체 "; // 관리자 화면용
	    } else {
	        // 일반 사용자는 본인 부서 게시판만 조회
	        // 내 부서에 공지사항 3개를 가져옴 
	    	System.out.println("is not an admin");
	    	int num1 = -1;
	    	int num2 = -1;
	    	int num3 = -1;
	    	int count = 0;
	    	ArrayList<DeptBoardVO> list1 = dao.selectThreeRecentNoticeByDept(userDeptNo);
	    	for(DeptBoardVO vo : list1) {
	    		if(count == 0) {
	    			num1 = vo.getDeptBNo();
	    		} else if(count == 1) {
	    			num2 = vo.getDeptBNo();
	    		} else if(count == 2) {
	    			num3 = vo.getDeptBNo();
	    		}
	    		returnList.add(vo);
	    		count++;
	    	}
	    	
	        // 내 부서에 공지사항 3개를 제외한 나머지 게시글을 가져옴 
	    	ArrayList<DeptBoardVO> list2 = dao.getRestOfDeptBoard(userDeptNo, num1, num2, num3);
	    	for(DeptBoardVO vo : list2) {
	    		returnList.add(vo);
	    	}

	        deptName = dao.getDeptNameByDeptNo(loginEmp.getDeptNo()); // 부서명 가져오기
	    }

	    int totalCount  = returnList.size();
	    System.out.println("returnList : " + returnList);
	    System.out.println("totalCount : " + totalCount);

	    req.setAttribute("list", returnList);
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