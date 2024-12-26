package com.aura.www.action.admin.emp;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class ModifyEmpOkAction implements Action {
	// 파일 저장경로
	private static final String UPLOAD_DIRECTORY = "upload\\emp"; 
	private static final String UPLOAD_PATH = "upload/emp"; 
	// "/upload/emp";
		
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
		    
		 // file
		    Part filePart;
			try {
				filePart = req.getPart("changeImg");
				
				if(filePart != null) {
			    	// 절대 경로 위치
			    	String uploadPath = req.getServletContext().getRealPath("")/* + File.separator */ + UPLOAD_DIRECTORY;
			    	
			    	System.out.println("uploadPath = " + uploadPath);
			    	
			    	File uploadDir = new File(uploadPath);
			    	if(!uploadDir.exists()) {
						uploadDir.mkdir(); // 디렉토리 생성
					}
			    	
			    	// 업로드된 파일 이름 가져오기
					String fileName = getFileName(filePart); 
					System.out.println("원래 파일명 : " + fileName);
					
					// 파일명에 붙일 랜덤 문자열
	                UUID uuid = UUID.randomUUID();
	 
	                // 실제 업로드되는 파일명
	                String newFileName = uuid + "_" + fileName;
					
					if(fileName != null && !fileName.isEmpty()) {
						// 파일 저장 경로
						String filePath = uploadPath + File.separator + newFileName;
						
						System.out.println("filePath = " + filePath);
						// filePath 파일 저장
						filePart.write(filePath);
					}
					String empImage= "/"+UPLOAD_PATH+"/"+newFileName;
					System.out.println("empImage = " + empImage);
					// DB에 저장
					vo.setEmpImage(empImage);
			    }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
	        dao.updateEmpOne(vo);
	    }
	    // 처리 후 부서 조회 화면으로 이동
	    return "admin?cmd=selectEmp";
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
