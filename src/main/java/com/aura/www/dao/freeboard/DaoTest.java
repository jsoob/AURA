package com.aura.www.dao.freeboard;

import java.util.ArrayList;

import com.aura.www.vo.freeboard.FreeBoardVO;

public class DaoTest {
	public static void main(String[] args) {
		FreeBoardDAO dao = new FreeBoardDAO();
		ArrayList<FreeBoardVO> list = dao.selectAll();
		
		System.out.println(list);
	}
}
