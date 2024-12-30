package com.aura.www.action.board.freeboard;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardCommentDAO;
import com.aura.www.vo.FreeBoardCommentVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectCommentAction implements Action {
	


	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String fbno = req.getParameter("freeBNo");
		int freeBNo = Integer.parseInt(fbno);
		
		FreeBoardCommentDAO dao = new FreeBoardCommentDAO();
		ArrayList<FreeBoardCommentVO> list = dao.selectCommentAll(freeBNo);
		
		JSONArray commentArray = new JSONArray();
		
		for(FreeBoardCommentVO vo : list){
			JSONObject comment = new JSONObject();
			
			comment.put("content",vo.getFBCmntContent());
			comment.put("userId",vo.getEmpNo());
			comment.put("cmntNo",vo.getFBCmntNo());
			comment.put("createDate",vo.getCreateDate());
			comment.put("empName",vo.getEmpName());
			comment.put("deptName",vo.getDeptName());
			comment.put("posName",vo.getPosName());
			commentArray.add(comment);
			
		}
		
		resp.setContentType("application/json; charset=UTF-8");
		try {
			resp.getWriter().print(commentArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return commentArray.toJSONString();
	}}
