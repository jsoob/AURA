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
    private int userDeptNo;

    public SelectDeptBAction(int userDeptNo) {
        this.userDeptNo = userDeptNo;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DeptBoardDAO dao = new DeptBoardDAO();
        HttpSession session = req.getSession();
        EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");
        
        boolean isAdmin = (loginEmp.getEmpNo() == 2024000); // 관리자 여부 판단
        int empNo = loginEmp.getEmpNo(); // 로그인한 사용자 사번
        
        
        
        // 페이징 처리
        int recordPerPage = 9; // 페이지당 출력할 게시글 수
        int currentPage = 1;
        String cp = req.getParameter("cp");
        if (cp != null) {
            currentPage = Integer.parseInt(cp);
           
        }
        System.out.println(userDeptNo + " : " +  empNo + " : " + currentPage + " : " +  recordPerPage);
        
        // 페이지 가져오는 idx
        int offsetNo = (currentPage-1)*recordPerPage;
        
        
        // 게시글 가져오기
        ArrayList<DeptBoardVO> list = dao.getDeptBoardWithPaging(userDeptNo, empNo, offsetNo, recordPerPage);
        
        
        
        System.out.println("ddddddd = "+list.get(0).toString());
        // 총 게시글 수 가져오기
        int totalCount = dao.getDeptBoardCount(userDeptNo, empNo, isAdmin);
        int totalPage = (int) Math.ceil((double) totalCount / recordPerPage); 
        
        // 페이징 범위 계산
        int startPage = Math.max(1, currentPage - 4);
        int endPage = Math.min(totalPage, startPage + 9);

        // JSP로 전달
        req.setAttribute("list", list);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);

        // 페이지 정보 설정
        HashMap<String, String> map = new HashMap<>();
        map.put("title", "부서 게시판");
        map.put("category", "deptboard");
        map.put("categoryName", "부서 게시판");
        map.put("pages", "selectDeptB");
        map.put("pagesName", "게시글 목록");
        req.setAttribute("commAt", map);

        return "view/board/deptboard/selectDeptB.jsp";
    }
}