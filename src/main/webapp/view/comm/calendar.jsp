<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 2번째 방법 --%>

<script>
	// Calendar date 객체 생성하기!
	let Calendar = new Date();
	
	// getDay() 메서드는 (일요일:0 ~ 토요일:6)을 반환하니 0번째 인덱스부터 일요일을 넣는다.
	let day_of_week = ['일', '월', '화', '수', '목', '금', '토'];
	
	// getMonth() 메서드는 (1월:0 ~ 12월:11)을 반환하니 0번째 인덱스부터 1월을 넣는다.
	let month_of_year = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
	
	let year = Calendar.getFullYear();   // yyyy   년도
	let month = Calendar.getMonth();     // 0 ~ 11 (1 ~ 12월 인덱스)
	let today = Calendar.getDate();      // 1 ~ 31 (1 ~ 31일)
	let weekday = Calendar.getDay();     // 0 ~ 6  (일요일 ~ 토요일 인덱스)
	
	Calendar.setDate(1);            // 달력은 1일부터 표시해야하니 setDate() 메서드를 이용해서 1일로 마추자!
	
	let DAYS_OF_WEEK = 7;          // 일주일은 7일!
	let DAYS_OF_MONTH = 31;        // 한달은 최대 31일!
	let str;                       // html 코드를 넣을 str 변수
	
	// tr
	let TR_start = "<tr>";
	let TR_end = "</tr>";
	
	//td   css 클래스를 이용하여 요일마다 색을 다르게줍니다.(커스텀마이징 가능)
	let TD_week_start = "<td class='week'>";            // 일요일 ~ 토요일 을 나타낼 td
	let TD_blank_start = "<td class='blank'>";          // blank (1일 이전의 날짜)
	let TD_today_start = "<td class='today'>";          // 오늘 날짜
	let TD_day_start = "<td class='day'>";              // 평일
	let TD_saturday_start = "<td class='saturday'>";     // 토요일
	let TD_sunday_start = "<td class='sunday'>";          // 일요일
	let TD_end    = "</td>";    // 테이블 만들기
	
	str =  "<table  width = border=1 cellspacing=0 cellpadding=0 ><tr><td style='text-align: center'>";
	str +=  "<strong style = 'font-size:16px'>"+ year +". " + month_of_year[month] + "</strong>";
	str += "<table  class = 'auraC-calendar mg-tp-10' border = 0 cellspacing=0 cellpadding=2>";
	
	// 본격적인 tr 시작!
	// 일단 맨 첫 줄은 요일을 나타내자!
	str += TR_start;    // 요일 td
	
	for(let i = 0; i < DAYS_OF_WEEK; ++ i) {
	
	    // 7번 돌아가면서 day_of_week의 값을 빼내온다. 
	   str += TD_week_start + day_of_week[i] + TD_end;
	}
	
	// 요일이 끝나면 한 줄 내려가기 위해 </tr>
	str += TR_end;
	
	// 1일이 시작하기 전까지의 이전 요일들을 blank 하자!
	for(let i = 0; i < Calendar.getDay(); ++ i) {
	    str += TD_blank_start + TD_end;
	}
	
	// 자 이제 1일부터 시작!
	for (let i = 0; i < DAYS_OF_MONTH; ++i) {
	    // 날짜가 i보다 클 때만 표현!! 왜냐하면 -> 날짜가 i보다 작다는 건 다음 달로 넘어가서 1일이 되었다는 거다!
	    if(Calendar.getDate() > i) {
	        let day = Calendar.getDate();   // 날짜
	        let week_day = Calendar.getDay(); // 요일
	
	        // 만약 일요일이면 tr로 한 칸 내려간다!
	        if(week_day == 0) {
	            str += TR_start;
	        }
	
	        // 오늘 날짜라면
	        if(day == today) {
	            str += TD_today_start + day + TD_end;
	        }
	        else {
	            switch(week_day) {
	                case 0 : // 일요일
	                    str += TD_sunday_start + day + TD_end;
	                    break;
	                case 6 : // 토요일
	                    str += TD_saturday_start + day + TD_end;
	                    str += TR_end;  // 토요일이면 </tr>
	                    break;
	                default : // 평일
	                    str += TD_day_start + day + TD_end;
	                    break;
	            }
	        }
	    }// if end
	
	    // 다음 날짜로 넘어간다.
	    Calendar.setDate(Calendar.getDate() + 1);
	}// for end
	
	str += "</table></td></tr></table>";
	
	document.write(str);
</script>


<%-- 1번째 --%>
<%--
<div class="auraMC-container">
  <div class="auraMC-my-calendar auraMC-clearfix">
    <div class="auraMC-clicked-date" style="display: none;">
      <div class="auraMC-cal-day"></div>
      <div class="auraMC-cal-date"></div>
    </div>
    <div class="auraMC-calendar-box">
      <div class="auraMC-ctr-box auraMC-clearfix">
        <button type="button" title="prev" class="btn-cal auraMC-prev">
        </button>
        <span class="auraMC-cal-month"></span>
        <span class="auraMC-cal-year"></span>
        <button type="button" title="next" class="btn-cal auraMC-next">
        </button>
      </div>
      <table class="auraMC-cal-table">
        <thead>
          <tr>
            <th>S</th>
            <th>M</th>
            <th>T</th>
            <th>W</th>
            <th>T</th>
            <th>F</th>
            <th>S</th>
          </tr>
        </thead>
        <tbody class="auraMC-cal-body"></tbody>
      </table>
    </div>
  </div>
</div>
 --%>