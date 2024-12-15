package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptVO {
	
	private int DEPT_NO;
	private String DEPT_NAME;
	private String UPDATE_DATE;

}
