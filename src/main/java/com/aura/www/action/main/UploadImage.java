package com.aura.www.action.main;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upload")
@MultipartConfig( // 파일 업로드 처리를 위한 설정 정보
		fileSizeThreshold = 1024*1024*2, // 2MB / 이 크기를 초과하면 디스크에 임시 파일로 저장
		maxFileSize = 1024*1024*30, // 30MB
		maxRequestSize = 1024*1024*60 // 최대 요청 사이즈
 )
public class UploadImage extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		resp.getWriter().println("test");
	}
	

}
