package com.aura.www.action.board.archivesdao;

import java.util.ArrayList;

import com.aura.www.action.board.archivesvo.ArchivesVO;

public class Testmain {
	public static void main(String[] args) {
		ArchivesDAO dao = new ArchivesDAO();
		ArrayList<ArchivesVO> vo = dao.selectAll();
		
		
	}

}
