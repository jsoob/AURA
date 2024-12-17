package com.aura.www.vo.freeboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeBoardCommentVO {
	private int fBCmntNo;
	private String fBCmntContent;
	private String createDate;
	private String updateDate;
	private int empNo;
	private int freeBNo;
}
