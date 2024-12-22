package com.aura.www.action.admin.emp;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyEmpOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String eNo = req.getParameter("empNo");
	    String empName = req.getParameter("empName");
	    String pNo = req.getParameter("posNo");
	    String dNo = req.getParameter("deptNo");
	    String hiredate = req.getParameter("hiredate");
	    String quitdate = req.getParameter("quitdate");
	    
	    if (eNo != null && !eNo.isEmpty()) {
	        AdminEmpDAO dao = new AdminEmpDAO();
	        EmpVO vo = new EmpVO();
	        
	        int empNo = Integer.parseInt(eNo);
	        int deptNo = Integer.parseInt(dNo);
	        int posNo = Integer.parseInt(pNo);
	        
	        vo.setEmpNo(empNo);
	        vo.setEmpName(empName);
	        vo.setPosNo(posNo);
	        vo.setDeptNo(deptNo);
		    if (hiredate != null && !hiredate.isEmpty()) vo.setHiredate(hiredate);
		    if (quitdate != null && !quitdate.isEmpty()) vo.setQuitdate(quitdate);
		    
	        dao.updateEmpOne(vo);
	    }
	    // 처리 후 부서 조회 화면으로 이동
	    return "admin?cmd=selectEmp";
	}
}
