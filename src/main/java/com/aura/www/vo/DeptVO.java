
package com.aura.www.vo;

import com.aura.www.vo.archives.ArchivesVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptVO extends ArchivesVO {
	
	private int detpNo;					// 부서번호
	private String deptName;			// 부서명

}