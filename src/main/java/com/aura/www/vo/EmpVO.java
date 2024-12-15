package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpVO {
	
	private int EMP_NO;					// 사원번호
	private String EMP_PW;				// 패스워드
	private String EMP_NAME;			// 사원명
	private String EMP_IMAGE;			// 사원이미지
	private String CMP_EMAIL;			// 사내 이메일
	private String EMP_EMAIL;			// 개인 이메일
	private String CELLPHONE;			// 휴대폰번호
	private String HIREDATE;			// 입사일자
	private String QUITDATE;			// 퇴사일자
	private String BIRTHDATE;			// 생년월일
	private int POS_NO;					// 직급번호
	private int DEPT_NO;				// 부서번호
	private String CREATE_DATE;			// 등록일자
	private String UPDATE_DATE;			// 수정일자
	

}
