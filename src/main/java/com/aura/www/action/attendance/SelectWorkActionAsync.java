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
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 특정 직책(Position)에 대한 데이터를 데이터베이스로부터 가져와 JSON 형식으로 변환하여 반환하는 역할
//SelectPosActionAsync 클래스는 Action 인터페이스를 구현
public class SelectWorkActionAsync implements Action {

	@SuppressWarnings("unchecked")			// unchecked 경고를 무시하라는 어노테이션
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		AttendanceDAO dao = new AttendanceDAO();
		ArrayList<AttendanceVO> list = dao.selectAll();
		
		req.setAttribute("list", list);		// list를 request 객체에 저장
		
		// PositionVO 리스트를 JSON 배열로 변환
		JSONArray jArr = listmap_to_json(list);
		
		// JSON 배열을 문자열로 변환하여 반환
		return jArr.toJSONString(); // JSON -> Array
	}
	
	// 리스트를 JSON 배열로 변환하는 메서드
	private JSONArray listmap_to_json(List<AttendanceVO> list)
    {       
        JSONArray json_arr=new JSONArray();							// JSON 배열 객체 생성
        for (AttendanceVO vo : list) {								// 리스트의 각 VO를 순회
            JSONObject json_obj = new JSONObject();					// 각 VO를 JSON 객체로 변환
            
			Field[] fields = vo.getClass().getDeclaredFields();		// VO 클래스의 모든 필드 가져옴
			for(int i=0; i <fields.length; i++){			
				fields[i].setAccessible(true);						// private 필드에 접근 가능하게 설정
				
				String key = fields[i].getName();					// 필드 이름(key)
				Object value = null;
				try {
					value = fields[i].get(vo);						// 필드 값(value)
				} catch (IllegalArgumentException e) {
					e.printStackTrace();							// 예외 발생 시 스택 트레이스 출력
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				json_obj.put(key,value);							// JSON 객체에 key-value 쌍 추가
			}
            json_arr.add(json_obj);									// JSON 배열에 JSON 객체 추가
        }
        return json_arr;											// JSON 배열 반환
    }
}
