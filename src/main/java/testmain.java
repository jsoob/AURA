import java.util.ArrayList;

import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;

public class testmain {
	public static void main(String[] args) {
		DeptBoardDAO dao = new DeptBoardDAO();
		DeptBoardVO vo = new DeptBoardVO();
		ArrayList<DeptBoardVO> list =  dao.selectAll();

		
		System.out.println(list);
	}
}
