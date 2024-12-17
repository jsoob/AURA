package com.aura.www.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//	SQL> CREATE TABLE BOARD
//	2  (BNO NUMBER(5),
//	3  WRITER VARCHAR2(20),
//	4  TITLE VARCHAR2(50),
//	5  CONTENTS CLOB,
//	6  REGDATE DATE,
//	7  HITS NUMBER(5),
//	8  IP VARCHAR2(16),
//	9  STATUS NUMBER(2));
//
//	테이블이 생성되었습니다.
//
//	SQL> CREATE SEQUENCE BOARD_BNO_SEQ
//	2  START WITH 1
//	3  INCREMENT BY 1
//	4  NOCACHE
//	5  NOCYCLE;
//
//	시퀀스가 생성되었습니다.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeptBoardVO {

	private int bno;
	private String writer;
	private String title;
	private String contents;
	private String regdate;
	private int hits;
	private String ip;
	private int status;
}
