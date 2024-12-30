package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceVO extends EmpVO {

	private String attenDate;			// 날짜
	private int empNo;					// 사원번호
	private String startworkTime;		// 출근시간
	private String endworkTime;			// 퇴근시간
	
	private String workGubun; 			// start / end
	private String searchEmpNo;			// 관리자 계정에서 근태관리 검색조건을 특정할 때 사용 (사원번호)
	
//	private EmpVO empVO;				// 사원 정보 (empVO 객체)
//	private DeptVO deptVO;				// 부서 정보 (deptVO 객체)
//	private PositionVO positionVO;		// 직급 정보 (positionVO 객체)

}