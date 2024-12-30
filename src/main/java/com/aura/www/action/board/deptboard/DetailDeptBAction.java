package com.aura.www.action.board.deptboard;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DetailDeptBAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String deptno = req.getParameter("deptBNo");

        if (deptno != null) {
            int deptBNo = Integer.parseInt(deptno);
            DeptBoardDAO dao = new DeptBoardDAO();
            DeptBoardVO vo = dao.selectOne(deptBNo);

            HttpSession session = req.getSession();
            EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");

            // 비공개 게시글 접근 제한
            if (vo.getDeptBPblc() == 0 && vo.getDeptBCrtr() != loginEmp.getEmpNo() && loginEmp.getEmpNo() != 2024000) {
                sendAlert(resp, "비공개 게시글에 접근할 수 없습니다.");
                return null;
            }

            // 조회수 증가 및 게시글 설정
            dao.raiseViews(deptBNo);
            req.setAttribute("vo", vo);
        }

        setPageAttributes(req);
        return "view/board/deptboard/detailDeptB.jsp";
    }

    // 경고 메시지 출력 함수
    private void sendAlert(HttpServletResponse resp, String message) {
        try {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>");
            resp.getWriter().println("alert('" + message + "');");
            resp.getWriter().println("history.back();");
            resp.getWriter().println("</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 페이지 정보 설정 함수
    private void setPageAttributes(HttpServletRequest req) {
        HashMap<String, String> map = new HashMap<>();
        map.put("title", "AURA 부서게시판 페이지");
        map.put("category", "deptboard");
        map.put("categoryName", "부서게시판 페이지");
        map.put("pages", "detailDeptB");
        map.put("pagesName", "게시글 상세보기");
        req.setAttribute("commAt", map);
    }
}