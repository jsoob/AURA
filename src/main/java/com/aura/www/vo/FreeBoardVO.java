package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardVO {
	private int freeBNo;
	private String freeBTitle; 
	private String freeBContent;
	private int freeBView;
	private int freeBNotice;
	private int freeBStatus;
	private int freeBPblc;
	private int freeBCrtr;
	private String createDate;
	private String updateDate;
}
