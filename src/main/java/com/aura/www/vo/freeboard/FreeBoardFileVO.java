package com.aura.www.vo.freeboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeBoardFileVO {
	private int fileNo;
	private String fileName;
	private String fileRoute;
	private int freeBNo;
}