package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVO extends PositionVO {
	
	private int deptNo;					// 부서번호
	private String deptName;			// 부서명

}
