package com.aura.www.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.aura.www.action.Action;
import com.aura.www.action.board.freeboard.DeleteFreeBAction;
import com.aura.www.action.board.freeboard.DetailFreeBAction;
import com.aura.www.action.board.freeboard.InsertTempSaveOkAction;
import com.aura.www.action.board.freeboard.ModifyFreeBAction;
import com.aura.www.action.board.freeboard.ModifyFreeBOkAction;
import com.aura.www.action.board.freeboard.SelectFreeBAction;
import com.aura.www.action.board.freeboard.WriteFreeBFormAction;
import com.aura.www.action.board.freeboard.WriteFreeBOkAction;
import com.aura.www.dao.FreeBoardFileDAO;
import com.aura.www.vo.FreeBoardFileVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/freeboard")
@MultipartConfig( // 파일 업로드 처리를 위한 설정 정보
		fileSizeThreshold = 1024 * 1024 * 2, // 2MB, 이 크기를 초과하면 디스크에 임시 파일로 저장
		maxFileSize = 1034 * 1024 * 30, // 30MB, 파일 한 개의 최대 사이즈
		maxRequestSize = 1024 * 1024 * 60 // 최대 요청 사이즈
)
public class FreeBoardController extends HttpServlet {

	// 파일 저장 경로
	private static final String UPLOAD_DIRECTORY = "upload";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String freeBStatus = req.getParameter("freeBStatus");
		
		String url = "";

		// 3. page==null or SelectFreeB 라면
		if (cmd == null || cmd.equals("selectFreeB")) {
			Action action = new SelectFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("detailFreeB")) {
			Action action = new DetailFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("writeFreeBForm")) {
			Action action = new WriteFreeBFormAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("writeFreeBOk")) {
			Action action = new WriteFreeBOkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyFreeB")) {
			Action action = new ModifyFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyFreeBOk")) {
			// 임시저장한 글 불러와서 등록한 경우에도 이거 실행
			Action action = new ModifyFreeBOkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("deleteFreeB")) {
			Action action = new DeleteFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("insertTempSaveOk")) {
			Action action = new InsertTempSaveOkAction();
			url = action.execute(req, resp);
		}

		if (cmd == null) {
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		} else if (cmd.equals("writeFreeBOk")) {
			resp.sendRedirect("freeboard");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		}
	}

	// 일단 테스트용으로 복붙한거니까 이대로 할거면 doProcess 이용
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String freeBStatus = req.getParameter("freeBStatus");
		String freeBNo = req.getParameter("freeBNo");
		String url = "";

		// 3. page==null or SelectFreeB 라면
		if (cmd == null || cmd.equals("selectFreeB")) {
			Action action = new SelectFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("detailFreeB")) {
			Action action = new DetailFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("writeFreeBForm")) {
			Action action = new WriteFreeBFormAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("writeFreeBOk")) {
			Action action = new WriteFreeBOkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyFreeB")) {
			Action action = new ModifyFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyFreeBOk")) {
			// 임시저장한 글 불러와서 등록한 경우에도 이거 실행
			Action action = new ModifyFreeBOkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("deleteFreeB")) {
			Action action = new DeleteFreeBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("insertTempSaveOk")) {
			Action action = new InsertTempSaveOkAction();
			url = action.execute(req, resp);
		}

		// 파일첨부기능
		String file = req.getParameter("file");
		
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
				
				// 처음 글 쓸때 파일을 함께 등록한다면
				if(file !=null && file.equals("insert")) {
					if(freeBNo != null && !freeBNo.equals("")) {
						//int fbno = Integer.parseInt(freeBNo);
						vo.setFreeBNo(Integer.parseInt(freeBNo));
					}
					vo.setFileName(fileName);
					vo.setFileRoute(filePath);
					System.out.println("freeBNo : " + freeBNo + "vo : " + vo);
					dao.insertFile(vo);
				// 수정에서 파일 새로 추가한다면
				}
//				else if(file !=null && file.equals("modify") && freeBNo!=null) {
//					
//					vo.setFreeBNo(Integer.parseInt(freeBNo));
//					vo.setFileName(fileName);
//					vo.setFileRoute(filePath);
//					System.out.println("fileName : " + fileName + " - " + "vo : " + vo);
//					dao.addOne(vo);
//					
//				}
				// resp.getWriter().println("file uploaded successfully " + fileName);

			}
		}

		// 파일이 없는 경우
//		resp.getWriter().println("No file was uploaded");

		// resp.sendRedirect("/web/day08/view.jsp");

		if (cmd == null) {
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
		} else if (cmd.equals("writeFreeBOk") || cmd.equals("modifyFreeBOk") || cmd.equals("insertTempSaveOk")) {
			resp.sendRedirect("freeboard");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, resp);
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
