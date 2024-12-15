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
	
	private int DEPT_NO;				// 부서번호
	private String DEPT_NAME;			// 부서명

}
