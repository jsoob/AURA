package com.aura.www.action.attendance;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.dao.PositionDAO;
import com.aura.www.vo.AttendanceVO;
import com.aura.www.vo.EmpVO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 특정 직책(Position)에 대한 데이터를 데이터베이스로부터 가져와 JSON 형식으로 변환하여 반환하는 역할
//SelectPosActionAsync 클래스는 Action 인터페이스를 구현
public class SelectWorkActionAsync implements Action {

	@SuppressWarnings("unchecked")			// unchecked 경고를 무시하라는 어노테이션
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		AttendanceDAO dao = new AttendanceDAO();
		
	    // 임의의 AttendanceVO 객체 생성 (필요하다면 특정 값으로 초기화 가능)
		// 기본값 설정을 위한 임시 vo 객체
	    AttendanceVO tempVO = new AttendanceVO(); 
	    
	    HttpSession session = req.getSession();
//		// 세션에서 EmpVO 객체 가져오기 (강제 형변환)
	    EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
//	    
	    tempVO.setEmpNo(loginEmp.getEmpNo());
	    
	    // selectAll() 호출 시 tempVO 전달
	    // DAO의 selectAll 메서드 호출
	    ArrayList<AttendanceVO> list = dao.selectAll(tempVO);
	    
	    // dao.selectAll() 오류가 나서 위 tempVO 객체를 따로 생성하여 선언
		// ArrayList<AttendanceVO> list = dao.selectAll();
	    
	    System.out.println("list = " + list.size());
		
		req.setAttribute("list", list);		// 조회 결과 list를 request 객체에 저장
		
		// PositionVO 리스트를 JSON 배열로 변환
		JSONArray jArr = listmap_to_json(list);
		
		JSONObject obj = new JSONObject();
		obj.put("workList", jArr);
		
		// JSON 배열을 문자열로 변환하여 반환
		return obj.toJSONString(); // JSON -> Array
	}
	
	// 리스트를 JSON 배열로 변환하는 메서드
	private JSONArray listmap_to_json(List<AttendanceVO> list){       
        JSONArray json_arr = new JSONArray();							// JSON 배열 객체 생성
        
        for (AttendanceVO vo : list) {
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
	         json_obj.put("empNo", vo.getEmpNo());
	         json_obj.put("empName", vo.getEmpName());
	         
	         json_obj.put("deptNo", vo.getDeptNo());
	         json_obj.put("deptName", vo.getDeptName());
	         
	         json_obj.put("posNo", vo.getPosNo());
	         json_obj.put("posName", vo.getPosName());
	         json_arr.add(json_obj);
        }
        
//
//        for (AttendanceVO vo : list) {
//            json_arr.add(vo); // VO 객체를 그대로 추가
//        }
//        
        return json_arr;											// JSON 배열 반환
    }
}
