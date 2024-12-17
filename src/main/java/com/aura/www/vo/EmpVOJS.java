package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpVOJS {
//	EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, 
//	CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, 
//	POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE
	
	private int empNo;
	private String empPw;
	private String empName;
	private String empImage;
	private String cmpEmail;
	private String empEmail;
	private String cellphone;
	private String hiredate;
	private String quitdate;
	private String birthdate;
	private int posNo;
	private int deptNo;
	private String createDate;
	private String updateDate;
	
}
