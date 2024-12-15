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
	
	private int EMP_NO;
	private String EMP_PW;
	private String EMP_NAME;
	private String EMP_IMAGE;
	private String CMP_EMAIL;
	private String EMP_EMAIL;
	private String CELLPHONE;
	private String HIREDATE;
	private String QUITDATE;
	private String BIRTHDATE;
	private int POS_NO;
	private int DEPT_NO;
	private String CREATE_DATE;
	private String UPDATE_DATE;
	

}
