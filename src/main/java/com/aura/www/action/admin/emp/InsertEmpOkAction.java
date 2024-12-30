package com.aura.www.action.admin.emp;


import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertEmpOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	    String empName = req.getParameter("empName");
	    String pNo = req.getParameter("posNo");
	    String dNo = req.getParameter("deptNo");
	    String hiredate = req.getParameter("hiredate");
	    
	    if (empName != null && !empName.isEmpty()) {
	        AdminEmpDAO dao = new AdminEmpDAO();
	        EmpVO vo = new EmpVO();
	        
	        String getKey = dao.getEmpKey();
	        if (getKey != null && !getKey.isEmpty()) {
		        
		        int empNo = Integer.parseInt(getKey);
		        int deptNo = Integer.parseInt(dNo);
		        int posNo = Integer.parseInt(pNo);
		        
		        vo.setEmpNo(empNo);
		        vo.setEmpPw(getKey); // 비번 초기값 사원번호
		        vo.setEmpName(empName);
		        vo.setCmpEmail(getKey+"@aura.com"); // 사내이메일 사원번호@aura.com
		        vo.setPosNo(posNo);
		        vo.setDeptNo(deptNo);
			    if (hiredate != null && !hiredate.isEmpty()) vo.setHiredate(hiredate);
			    
		        dao.insertEmp(vo);
		    }
	    }
	    // 처리 후 부서 조회 화면으로 이동
	    return "admin?cmd=selectEmp";
	}
}
