package com.aura.www.action.admin.emp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
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
		
		int totalCount = dao.getTotalCountSearch(vo); // 전체수

		HashMap<String, Object> page = getPage(totalCount, req);
		
		int limitNo = (int) page.get("limitNo");
		int offsetNo = (int) page.get("offsetNo");
		
		ArrayList<EmpVO> list = dao.selectEmpSearchPage(vo, limitNo, offsetNo);
		// System.out.println("list 수 = " + list.size());
		
		// vo를 json으로
		JSONArray jArr = listmap_to_json(list);
		// 페이지 map jsonObject
		JSONObject pageObject = new JSONObject(page);
		
		JSONObject obj = new JSONObject();
		obj.put("empList", jArr); // 리스트
		obj.put("pageObject", pageObject); // 페이징처리
		
		return obj.toJSONString(); // jArr.toJSONString(); // JSON -> Array
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
	
	public HashMap<String, Object> getPage(int totalCount, HttpServletRequest req) {
		HashMap<String, Object> page = new HashMap<String, Object>();

		int recordPerPage = 6; // 한 페이지당 게시물 6
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
		// 끝 페이지 미세 조정
		endPage = Math.min(totalPage, startPage + 9);
		
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
