package com.aura.www.action.board.archivesvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchivesVO {
	
	private int arcNo;						// 게시판번호
	private String arcTitle;				// 제목
	private String arcContent;				// 내용
	private int arcView;					// 조회수
	private int arcNotice;					// 공지
	private int empNo;						// 사원번호
	private String createDate;				// 등록일자

}
