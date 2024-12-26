package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WriteDeptBOkAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 파라미터값 가져와서 DB에 저장
        String deptBTitle = req.getParameter("deptBTitle");
        String deptBContent = req.getParameter("deptBContent");
        String dbn = req.getParameter("deptBNotice");
        String dbp = req.getParameter("deptBPblc");

        // 세션에서 로그인 사용자 정보 가져오기
        HttpSession session = req.getSession();
        EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");
        int deptBCrtr = loginEmp.getEmpNo(); // 작성자 번호
        int deptNo = loginEmp.getDeptNo();  // 로그인한 사용자의 부서 번호
        int deptBNotice = (dbn != null) ? Integer.parseInt(dbn) : 0; // 공지 여부
        int deptBPblc = (dbp != null) ? Integer.parseInt(dbp) : 1;   // 공개 여부
        int deptBStatus = 1; // 상태: 등록됨

        // 게시글 데이터 생성
        DeptBoardVO vo = new DeptBoardVO();
        vo.setDeptBTitle(deptBTitle);
        vo.setDeptBContent(deptBContent);
        vo.setDeptBNotice(deptBNotice);
        vo.setDeptBStatus(deptBStatus);
        vo.setDeptBPblc(deptBPblc);
        vo.setDeptBCrtr(deptBCrtr);
        vo.setDeptNo(deptNo); // 부서 번호 추가

        // DB에 저장
        DeptBoardDAO dao = new DeptBoardDAO();
        dao.insertOne(vo);

        return "deptboard?cmd=selectDeptB";
    }
}