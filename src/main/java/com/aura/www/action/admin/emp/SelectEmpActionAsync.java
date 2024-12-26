package com.aura.www.action.admin.emp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectEmpActionAsync implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		EmpVO vo = new EmpVO();
		
		// select 조건문
		String deptName = req.getParameter("deptName");
		String empNo = req.getParameter("empNo");
		String empName = req.getParameter("empName");
		String hiredate_st = req.getParameter("hiredate_st");
		String hiredate_ed = req.getParameter("hiredate_ed");
		String qdYN = req.getParameter("qdYN");
		
		if(deptName == null) { }
		else if(!deptName.equals("")) { vo.setDeptName(deptName); };
		
		if(empNo == null) { }
		else if(!empNo.equals("")) { vo.setEmpNo(Integer.parseInt(empNo)); };
		
		if(empName == null) { }
		else if(!empName.equals("")) { vo.setEmpName(empName); };
		
		if(hiredate_st == null) { }
		else if(!hiredate_st.equals("")) { vo.setHiredate_st(hiredate_st); };
		
		if(hiredate_ed == null) { }
		else if(!hiredate_ed.equals("")) { vo.setHiredate_ed(hiredate_ed); };
		
		if(qdYN == null) { }
		else if(!qdYN.equals("")) { vo.setQdYN(qdYN); };
		
		AdminEmpDAO dao = new AdminEmpDAO();
		ArrayList<EmpVO> list = dao.selectEmp(vo);
		System.out.println("list 수 = " + list.size());
		
		// vo를 json으로
		JSONArray jArr = listmap_to_json(list);
		
		return jArr.toJSONString(); // JSON -> Array
	}
	
	// vo key value 자동으로 찾아서 json array로 해줌.. 근데 상속으로 받았던 필드들은 찾지 못해서
	// 나머지는 직접 가져오자..
	private JSONArray listmap_to_json(List<EmpVO> list)
    {       
        JSONArray json_arr=new JSONArray();
        for (EmpVO vo : list) {
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
			// 상속받은 vo들 변수(필드)값
			json_obj.put("deptNo", vo.getDeptNo());
			json_obj.put("deptName", vo.getDeptName());
			
			json_obj.put("posNo", vo.getPosNo());
			json_obj.put("posName", vo.getPosName());
			
            json_arr.add(json_obj);
        }
        return json_arr;
    }
}
