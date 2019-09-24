package wizeproject.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @Title      : Date Utility Class
 * @Packgename : wizeproject.framework.util
 * @Filename   : DateUtil.java
 *
 * @Author  : 
 * @Since   : 2017. 6. 27.
 * @Version : 1.0
 */
public final class DateUtil {

	/**
	 * 현재일자를 포맷에 따라 반환한다. 
	 * @param format 포맷 문자열 ("yyyy-MM-dd HH:mm:ss")
	 * @return 포맷된 일자 문자열
	 */
	/*
	 * ex) String today = DateUtil.getToday("yyyy-MM-dd");
	 *     today -> "2012-03-16"
	 */
	public static String getToday(String format) {

		String formatType = format;

		if (formatType == null || formatType.equals("")) formatType = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(formatType, Locale.KOREA);
		return sdf.format(new Date());
	}

	/**
	 * 현재일자를 배열로 반환한다. 
	 * @return 현재일자 배열(년, 월, 일)
	 */
	/*
	 * ex) String[] today = DateUtil.getToday();
	 *     today[0] -> 년도("2012")
	 *     today[1] -> 월("03")
	 *     today[2] -> 일("16")
	 */
	public static String[] getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.KOREA);
		String today = sdf.format(new Date());
		return CommonUtil.toArray(today, "-");
	}

	/**
	 * 현재년도(yyyy)를 조회한다.
	 *
	 * @return 현재년도
	 */
	public static String getYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.KOREA);
		return sdf.format(new Date());
	}

	/**
	 * 현재월(MM)을 조회한다.
	 *
	 * @return 현재월
	 */
	public static String getMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.KOREA);
		return sdf.format(new Date());
	}

	/**
	 * 현재일(dd)을 조회한다.
	 *
	 * @return 현재일
	 */
	public static String getDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd", Locale.KOREA);
		return sdf.format(new Date());
	}

	/**
	 * 특정년/월의 마지막 날자(문자열)를 반환(28~31)한다.
	 *
	 * @param year 년도(yyyy)
	 * @param month 월(MM)
	 * @return 마지막날
	 */
	public static String getLastDay(String year, String month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month), 1);//다음달 1일
		cal.add(Calendar.DATE, -1);
		int last = cal.get(Calendar.DATE);
		return last + "";
	}

	/**
	 * 특정일에 대해 년/월/일을 더하거나 빼서 계산을 한다.
	 *
	 * @param date 일자(yyyyMMdd,yyyy-MM-dd형태 - 20120316,2012-03-16)
	 * @param format 반환날짜의 구분자
	 * @param field 년/월/일구분(YEAR|MONTH|DAY)
	 * @param amount 증/감분
	 * @return 계산된 날짜
	 */
	public static String addDate(String date, String format, String field, int amount) {
		String newDate = "";
		int yy = 0;
		int mm = 0;
		int dd = 0;
		if (date != null && (date.trim().length() == 8 || date.trim().length() == 10)) {
			//기준일자
			if (date.trim().length() == 8) {
				yy = Integer.parseInt(date.substring(0, 4));
				mm = Integer.parseInt(date.substring(4, 6)) - 1;//calendar의 월은 (0 ~ 11)
				dd = Integer.parseInt(date.substring(6, 8));
			} else if (date.trim().length() == 10) {
				yy = Integer.parseInt(date.substring(0, 4));
				mm = Integer.parseInt(date.substring(5, 7)) - 1;//calendar의 월은 (0 ~ 11)
				dd = Integer.parseInt(date.substring(8, 10));
			}
			Calendar cal = Calendar.getInstance();
			cal.set(yy, mm, dd);
			//계산
			if (field.equals("YEAR")) {
				cal.add(Calendar.YEAR, amount);
			} else if (field.equals("MONTH")) {
				cal.add(Calendar.MONTH, amount);
			} else if (field.equals("DAY")) {
				cal.add(Calendar.DATE, amount);
			}
			//계산된일자
			yy = cal.get(Calendar.YEAR);
			mm = (cal.get(Calendar.MONTH) + 1);
			dd = cal.get(Calendar.DATE);
			newDate = yy + format + (mm < 10 ? "0" + mm : mm) + format + (dd < 10 ? "0" + dd : dd);
		}
		return newDate;
	}

	/**
	 * 입력된 날짜 검증
	 *
	 * @param date 일자(yyyyMMdd - 20120316)
	 * @param 빈값 허용 여부(yyyyMMdd - 20120316)
	 * @return 입력 날짜 검증 여부
	 */
	public static boolean getDateIsCorrect(String date, boolean blank) {

		if (blank && "".equals(date)) { return true; }

		boolean flag = false;
		if (date.length() == 0) return true;

		try {
			int yyyy = Integer.parseInt(date.substring(0, 4));
			int mm = Integer.parseInt(date.substring(4, 6));
			int dd = Integer.parseInt(date.substring(6));

			flag = DateUtil.getDateIsCorrect(yyyy, mm, dd);
		} catch (Exception ex) {
			LogUtil.println("Exception position : DateUtil - getDateIsCorrect(String date, boolean blank)");
			flag = false;
		}

		return flag;
	}

	/**
	 * 입력된 날짜 검증
	 *
	 * @param yyyy 년(yyyy - 2015)
	 * @param mm 월(yyyy - 05)
	 * @param dd 일(yyyy - 01)
	 * @return 입력 날짜 검증 여부
	 */
	public static boolean getDateIsCorrect(int yyyy, int mm, int dd) {
		if (yyyy < 0 || mm < 0 || dd < 0) return false;
		if (mm > 12 || dd > 31) return false;

		String year = "" + yyyy;
		String month = "00" + mm;
		String yearStr = year + month.substring(month.length() - 2);
		String endDay = DateUtil.getLastDay(yearStr.substring(0, 4), yearStr.substring(4, 6));

		if (dd > Integer.parseInt(endDay)) return false;

		return true;
	}

	public static List<?> getDateBetweenList(String startDate, String endDate) {
		List<String> dateList = new ArrayList<String>();
		String tempDate = startDate;

		//일자별로 loop
		while (!tempDate.equals(endDate)) {
			dateList.add(tempDate);
			tempDate = DateUtil.addDate(tempDate, "", "DAY", 1);
		} //end while (!tempDate.equals(endDate))
		dateList.add(endDate);
		return dateList;
	}

	/**
	 * 날짜 비교
	 * @param date1(yyyyMMdd)
	 * @param date2(yyyyMMdd)
	 * @return
	 */
	public static int compareDate(String date1, String date2) {
		int result = 0;

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(Integer.parseInt(date1.substring(0, 4)), Integer.parseInt(date1.substring(4, 6)), Integer.parseInt(date1.substring(6, 8)));
		c2.set(Integer.parseInt(date2.substring(0, 4)), Integer.parseInt(date2.substring(4, 6)), Integer.parseInt(date2.substring(6, 8)));

		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		if (c1.after(c2)) {
			result = 1;
		} else if (c1.before(c2)) {
			result = -1;
		} else {
			result = 0;
		} //end if(c1.after(c2))

		return result;
	}

	public static java.util.Date check(String s, String format) throws java.text.ParseException {
		if (s == null) throw new java.text.ParseException("date string to check is null", 0);
		if (format == null) throw new java.text.ParseException("format string to check date is null", 0);

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;
		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		if (!formatter.format(date).equals(s)) throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
		return date;
	}

	/**
	 * return days between two date strings with user defined format.
	 * 날짜 형식이 맞고, 존재하는 날짜일 때 요일을 리턴<br>
	 * 형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생<br>
	 * 1: 일요일 (java.util.Calendar.SUNDAY 와 비교)<br>
	 * 2: 월요일 (java.util.Calendar.MONDAY 와 비교)<br>
	 * 3: 화요일 (java.util.Calendar.TUESDAY 와 비교)<br>
	 * 4: 수요일 (java.util.Calendar.WENDESDAY 와 비교)<br>
	 * 5: 목요일 (java.util.Calendar.THURSDAY 와 비교)<br>
	 * 6: 금요일 (java.util.Calendar.FRIDAY 와 비교)<br>
	 * 7: 토요일 (java.util.Calendar.SATURDAY 와 비교)<br>
	 * 예) String s = "2000-05-29";<br>
	 * int dayOfWeek = whichDay(s, "yyyy-MM-dd");<br>
	 * if (dayOfWeek == java.util.Calendar.MONDAY)<br>
	 * LogUtil.println(" 월요일: " + dayOfWeek);<br>
	 * if (dayOfWeek == java.util.Calendar.TUESDAY)<br>
	 * LogUtil.println(" 화요일: " + dayOfWeek);
	 *
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int
	 * @throws ParseException the parse exception
	 */
	public static int whichDay(String s, String format) throws java.text.ParseException {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = check(s, format);

		java.util.Calendar calendar = formatter.getCalendar();
		calendar.setTime(date);
		return calendar.get(java.util.Calendar.DAY_OF_WEEK);
	}

	public static String getWeekDay(String date) throws Exception {
		int weekNum = DateUtil.whichDay(date, "yyyyMMdd");
		String week = "";

		if (weekNum == java.util.Calendar.MONDAY) {
			week = "월";
		} else if (weekNum == java.util.Calendar.TUESDAY) {
			week = "화";
		} else if (weekNum == java.util.Calendar.WEDNESDAY) {
			week = "수";
		} else if (weekNum == java.util.Calendar.THURSDAY) {
			week = "목";
		} else if (weekNum == java.util.Calendar.FRIDAY) {
			week = "금";
		} else if (weekNum == java.util.Calendar.SATURDAY) {
			week = "토";
		} else if (weekNum == java.util.Calendar.SUNDAY) {
			week = "일";
		} else {
			week = "";
		}

		return week;
	}

	/**
	 * 주어진 날짜/시간 스트팅 문자열의 날짜/시간 포멧을 변경한다. 
	 *
	 * @param dateTime
	 * @param dateTimeStartFormat
	 * @param dateTimeEndFormat
	 * @return
	 */
	public static String converDateString(String dateTime, String dateTimeStartFormat, String dateTimeEndFormat) {
		Date date = null;
		try {
			SimpleDateFormat converter = new SimpleDateFormat(dateTimeStartFormat, Locale.KOREA);
			date = converter.parse(dateTime);

			SimpleDateFormat formatter = new SimpleDateFormat(dateTimeEndFormat, Locale.KOREA);
			return formatter.format(date);
		} catch (java.text.ParseException pe) {
			LogUtil.println("Connection Exception occurred");
		}

		return "";
	}
}
