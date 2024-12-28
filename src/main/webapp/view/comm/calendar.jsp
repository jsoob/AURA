<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
  <!-- // .my-calendar -->
</div>