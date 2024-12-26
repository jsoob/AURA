package com.aura.www.action.main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class editMyEmpOkAction implements Action {
	// 파일 저장경로
	private static final String UPLOAD_DIRECTORY = "upload\\emp"; 
	private static final String UPLOAD_PATH = "upload/emp"; 
	// "/upload/emp";
		
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("editMyEmpOkAction");
		
		JSONObject obj = new JSONObject();
		
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		
		String eNo = loginEmp.getEmpNo()+"";
		String birthdate = req.getParameter("birthdate");
		String cellphone = req.getParameter("cellphone");
		String empEmail = req.getParameter("empEmail");
		
	    if (eNo != null && !eNo.isEmpty()) {
	        LoginDAO dao = new LoginDAO();
	        
	        EmpVO vo = new EmpVO();
	        int empNo = Integer.parseInt(eNo);
	        vo.setEmpNo(empNo);
	        if (birthdate != null && !birthdate.isEmpty()) vo.setBirthdate(birthdate);
	        if (cellphone != null && !cellphone.isEmpty()) vo.setCellphone(cellphone);
	        if (empEmail != null && !empEmail.isEmpty()) vo.setEmpEmail(empEmail);
		    
		 // file
		    Part filePart;
			try {
				filePart = req.getPart("myChangeImg");
				
				if(filePart != null) {
			    	// 절대 경로 위치
			    	String uploadPath = req.getServletContext().getRealPath("")/* + File.separator */ + UPLOAD_DIRECTORY;
			    	
			    	File uploadDir = new File(uploadPath);
			    	if(!uploadDir.exists()) {
						uploadDir.mkdir(); // 디렉토리 생성
					}
			    	
			    	// 업로드된 파일 이름 가져오기
					String fileName = getFileName(filePart);
					
	                if(fileName != null && !fileName.isEmpty()) {
	                	// 파일명에 붙일 랜덤 문자열
	                	UUID uuid = UUID.randomUUID();
	                	
	                	// 실제 업로드되는 파일명
	                	String newFileName = uuid + "_" + fileName;
	                	
						// 파일 저장 경로
						String filePath = uploadPath + File.separator + newFileName;
						
						// filePath 파일 저장
						filePart.write(filePath);
						String empImage= "/"+UPLOAD_PATH+"/"+newFileName;
						// DB에 저장
						vo.setEmpImage(empImage);
					}
			    }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
	        int status = dao.editMyEmp(vo);
	        if(status == 1) {
	        	loginEmp = dao.selectLogin(loginEmp.getEmpNo(), loginEmp.getEmpPw());
	        	if(loginEmp!=null) {
	        		System.out.println("loginEmp/setAttribute");
	        		session.setAttribute("loginEmp", loginEmp);
	        	}
	        }
	        obj.put("status", status);
	    }
	    // 처리 후 부서 조회 화면으로 이동
	    return obj.toJSONString();
	}
	
	// 업로드된 파일의 이름만 가져오기.
	private String getFileName(Part filePart) {
		// Content-Disposition: form-data; name="filename"; filename="ball11.png"
		for(String content : filePart.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) { // filename 으로 시작하면
				return content.substring(content.indexOf("=")+2, content.length()-1);
			}
		}
		return null;
	}
}
