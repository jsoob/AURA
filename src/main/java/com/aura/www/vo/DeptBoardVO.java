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
    private int deptBCrtr;       // 작성자 사원번호
    private String createDate;
    private String updateDate;

    private String empName;      // 작성자 이름 (추가)
    // private int empNo;      // 작성자 이름 (추가)
}