package com.aura.www.action.board.freeboard;

import java.io.File;
import java.io.IOException;

import com.aura.www.dao.FreeBoardFileDAO;
import com.aura.www.vo.FreeBoardFileVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/insertFBFile")
public class InsertFBFile extends HttpServlet{
	
	// 파일 저장 경로
	private static final String UPLOAD_DIRECTORY = "upload";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일첨부기능

		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

		System.out.println(uploadPath);

		File uploadDir = new File(uploadPath);

		// 업로드 디렉토리가 없다면 생성
		if (!uploadDir.exists()) { // 해당 디렉토리가 없으면
			uploadDir.mkdir(); // 디렉토리를 생성해
		}
		FreeBoardFileDAO dao = new FreeBoardFileDAO();

		for (Part part : req.getParts()) {
			if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
				String fileName = getFileName(part);

				// 유니크한 파일명 만들기
				// Universally Unique Identifier
				// 전 세계적으로 고유한 식별자를 생성하기 위한 클래스
				// String uuid = ""+UUID.randomUUID();
				// fileName=uuid+"_"+fileName;
				System.out.println("파일명 : " + fileName);

				// FileVO vo = new FileVO(0, title, writer, contents, fileName);
				FreeBoardFileVO vo = new FreeBoardFileVO();
				String filePath = uploadPath + File.separator + fileName;
				part.write(filePath);

				vo.setFileName(fileName);
				vo.setFileRoute(filePath);
				
				dao.insertFile(vo);
				// resp.getWriter().println("file uploaded successfully " + fileName);

			}
		}
	
	}
	
	// 업로드된 파일의 이름만 가져오기
	private String getFileName(Part filePart) {
		for (String content : filePart.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
			}
		}
		return null;
	}
}