package com.aura.www.action.admin.position;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.PositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectPosActionAsync implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		PositionDAO dao = new PositionDAO();
		ArrayList<PositionVO> list = dao.selectPosAll();
		
		req.setAttribute("list", list);
		
		// vo를 json으로
		JSONArray jArr = listmap_to_json(list);
		
		return jArr.toJSONString(); // JSON -> Array
	}
	
	// vo key value 자동으로 찾아서 json array로 해줌.. 근데 상속으로 받았던 필드들은 찾지 못해서
	// 나머지는 직접 가져오자..
	private JSONArray listmap_to_json(List<PositionVO> list)
    {       
        JSONArray json_arr=new JSONArray();
        for (PositionVO vo : list) {
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
            json_arr.add(json_obj);
        }
        return json_arr;
    }
}
