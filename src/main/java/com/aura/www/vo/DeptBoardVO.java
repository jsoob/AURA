package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptBoardVO {
	private int deptBNo;
	private String deptBTitle; 
	private String deptBContent;
	private int deptBView;
	private int deptBNotice;
	private int deptBStatus;
	private int deptBPblc;
	private int deptNo;	
	private int deptBCrtr;
	private String createDate;
	private String updateDate;
}