package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionVO {
	
	private int POS_NO;					// 직급번호
	private String POS_NAME;			// 직급명

}
