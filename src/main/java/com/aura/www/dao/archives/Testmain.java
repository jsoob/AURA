package com.aura.www.dao.archives;

import java.util.ArrayList;

import com.aura.www.vo.archives.ArchivesVO;

public class Testmain {
	public static void main(String[] args) {
		ArchivesDAO dao = new ArchivesDAO();
		ArrayList<ArchivesVO> vo = dao.selectAll();
		
		
	}

}
