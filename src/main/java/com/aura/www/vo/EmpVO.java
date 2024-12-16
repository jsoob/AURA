package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpVO {
//	EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, 
//	CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, 
//	POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE
	
	private int emp_no;
	private String emp_pw;
	private String emp_name;
	private String emp_image;
	private String cmp_email;
	private String emp_email;
	private String cellphone;
	private String hiredate;
	private String quitdate;
	private String birthdate;
	private int pos_no;
	private int dept_no;
	private String create_date;
	private String update_date;
	
}
