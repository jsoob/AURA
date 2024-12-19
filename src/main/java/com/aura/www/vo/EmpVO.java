package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class EmpVO extends DeptVO { 
//	EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, 
//	CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, 
//	POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE
	
	private int empNo;					// 사원번호
	private String empPw;				// 패스워드
	private String empName;				// 사원명
	private String empImage;			// 사원이미지
	private String cmpEmail;			// 사내 이메일
	private String empEmail;			// 개인 이메일
	private String cellphone;			// 휴대폰번호
	private String hiredate;			// 입사일자
	private String quitdate;			// 퇴사일자
	private String birthdate;			// 생년월일
	private int posNo;					// 직급번호
	private int deptNo;					// 부서번호
	private String createDate;			// 등록일자
	private String updateDate;			// 수정일자
	

}
