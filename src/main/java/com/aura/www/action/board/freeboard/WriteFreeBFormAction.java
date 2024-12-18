package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFreeBFormAction implements Action {

   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
      req.setAttribute("pages", "자유게시판 글쓰기");
      
      return "view/board/freeboard/writeFreeB.jsp";
   }

}

