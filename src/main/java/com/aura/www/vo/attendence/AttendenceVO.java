package com.aura.www.vo.attendence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendenceVO {

	private String attenDate;
	private int empNo;
	private String startworkTime;
	private String endworkTime;

}

