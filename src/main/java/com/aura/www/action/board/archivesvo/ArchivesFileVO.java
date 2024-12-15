package com.aura.www.action.board.archivesvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArchivesFileVO {
	
	private int fileNo;					// 파일번호
	private String fileName;			// 파일명
	private String fileRoute;			// 파일경로
	private int arcNo;					// 게시판번호
	

}
