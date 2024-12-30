package com.aura.www.action.board.freeboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import com.aura.www.dao.FreeBoardFileDAO;
import com.aura.www.vo.FreeBoardFileVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/downloadFBFile")
public class DownloadFBFile extends HttpServlet{
	private static final String UPLOAD_DIRECTORY = "upload";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// no 파라미터 값 가져오기
		String n = req.getParameter("no");
		
		if(n != null) {
			int no = Integer.parseInt(n);
			System.out.println("no : " + no);
			FreeBoardFileDAO dao = new FreeBoardFileDAO();
			
			FreeBoardFileVO vo = dao.selectFileOne(no);
			
			// 파일명 확인
			System.out.println(vo.getFileName());
			
			// 파일이 저장되어있는 경로
			String uploadPath = getServletContext().getRealPath("")+File.separator+UPLOAD_DIRECTORY;
			
			System.out.println("저장 경로 : " + uploadPath);
//			if(vo != null) {
//				// 파일 객체 생성
//				File f = new File(uploadPath, vo.getFileName());
//				// 파일이 존재하고 파일이 맞다면
//				if(f.exists() && f.isFile()) {
//					// 응답객체에 컨텐츠의 종류를 지정
//					resp.setContentType("application/octet-stream");
//					// 파일명이 어떤 값인지 헤더에 지정
//					resp.setHeader("Content-Disposition", "attachment;filename="+ vo.getFileName());
//					
//					FileInputStream fis = new FileInputStream(f);
//					OutputStream os = resp.getOutputStream();
//					
//					byte[] buffer = new byte[4096];
//					int b = 0;
//					
//					while((b = fis.read(buffer))!=-1) {
//						os.write(buffer,0,b);
//					}
//				}
//			}
			
			// file에 맞는 mimetype
	        if (vo != null) {
	          
	            File f = new File(uploadPath, vo.getFileName());

	            if (f.exists() && f.isFile()) {
	                String fileName = vo.getFileName();
	                String mimeType = getServletContext().getMimeType(fileName);

	                if (mimeType == null) {
	                    // MIME 타입이 없으면 일반 바이너리 파일로 처리
	                    mimeType = "application/octet-stream";
	                }

	                resp.setContentType(mimeType);

	                // 한글 파일명도 가능하게
	                fileName = URLEncoder.encode(fileName,"UTF-8");
	                
	                // 다운로드 헤더 설정 (브라우저에서 바로 열리는 파일을 원할 경우 수정 가능)
	                resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
	                resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	                resp.setHeader("Pragma", "no-cache");
	                resp.setHeader("Expires", "0");
	                
	                try (FileInputStream fis = new FileInputStream(f);
	                     OutputStream os = resp.getOutputStream()) {

	                    byte[] buffer = new byte[4096];
	                    int bytesRead;

	                    while ((bytesRead = fis.read(buffer)) != -1) {
	                        os.write(buffer, 0, bytesRead);
	                    }
	                }
	            }
	        }
	    }
	}
}
