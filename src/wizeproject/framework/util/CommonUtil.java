package wizeproject.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * @Title      : 공통 Utility 클래스
 * @Packgename : wizeproject.framework.util
 * @Filename   : CommonUtil.java
 *
 * @Author  : 
 * @Since   : 2017. 6. 27.
 * @Version : 1.0
 */
public class CommonUtil {

	/**
	 * 도메인 명을 조회한다.
	 *
	 * @param req HttpServletRequest 객체
	 * @return 도메인 명
	 */
	public static String getDomain(HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		sb.append(req.getScheme());
		sb.append("://");
		sb.append(req.getServerName());
		sb.append(":");
		sb.append(req.getServerPort());
		sb.append(req.getContextPath());
		return sb.toString();
	}

	/**
	 * 주어진 key에 해당하는 value를 반환한다.
	 * 
	 * @param map 대상Map
	 * @param key Key값
	 * @param defaultValue 데이터가 없는 경우 기본값
	 * @return Value
	 */
	public static String getMapValue(Map<String, ?> map, String key, String defaultValue) {
		if (map == null) return "";
		Object value = map.get(key);
		if (value == null) return defaultValue;
		if (StringUtils.isEmpty("" + value)) return defaultValue;
		return "" + value;
	}

	/**
	 * 주어진 parameter Object가 Null이면 공백문자를 반환한다.
	 * 
	 * @param parameter Object
	 * @return  String
	 */
	public static String toString(Object parameter) {
		return (parameter == null) ? "" : parameter.toString();
	}

	/**
	 * 주어진 parameter Object가 Null이면 대체문자열(replacement)을 반환한다.
	 * 
	 * @param parameter Object
	 * @param replacement Null인경우 대체문자열
	 * @return  String
	 */
	public static String toString(Object parameter, String replacement) {
		return (parameter == null) ? replacement : parameter.toString();
	}

	/**
	 * 주어진 parameter Object가 Null이면 0을 반환한다.
	 * 
	 * @param parameter Object
	 * @return Integer
	 */
	public static int toInt(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return 0;
		else return Integer.parseInt(parameter.toString().trim());
	}

	/**
	 * 주어진 parameter Object가 Null이면 0을 반환한다.
	 * 
	 * @param parameter Object
	 * @return Long
	 */
	public static long toLong(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return 0;
		else return Long.parseLong(parameter.toString().trim());
	}

	/**
	 * 주어진 parameter Object가 Null이면 0을 반환한다.
	 * 
	 * @param parameter Object
	 * @return Float
	 */
	public static float toFloat(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return 0;
		else return Float.parseFloat(parameter.toString().trim());
	}

	/**
	 * 주어진 parameter Object가 Null이면 false를 반환한다.
	 * 
	 * @param parameter Object
	 * @return Boolean
	 */
	public static boolean toBoolean(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return false;
		else return (Boolean) parameter;
	}

	/**
	 * 주어진 parameter Object가 Null이면 0을 반환한다.
	 * 
	 * @param parameter Object
	 * @return Double
	 */
	public static double toDouble(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return 0;
		else return Double.parseDouble(parameter.toString().trim());
	}

	/**
	 * 주어진 parameter Object가 Null이면 0을 반환한다.
	 * 
	 * @param parameter Object
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object parameter) {
		if (parameter == null || parameter.toString().trim().equals("")) return new BigDecimal("0");
		else return new BigDecimal(parameter.toString());
	}

	/**
	 * 숫자형 데이터를 회계단위로 표현한다.
	 * 
	 * @param num String 텍스트형 숫자 데이타(10000)
	 * @return String 변형된 데이타(10,000)
	 */
	public static String addComma(String num) {

		String dec = "";
		String returnVal = "";
		String number = "";

		if (num.indexOf(".") > -1) {
			dec = num.substring(num.indexOf("."));
			number = num.substring(0, num.indexOf("."));
		}

		try {
			NumberFormat nf = NumberFormat.getInstance();
			if (num == null || num.trim().equals("")) number = "0";

			String tempDec = "0";
			if (dec.length() > 1) {
				tempDec = dec.substring(1);
			}

			if (new BigDecimal(tempDec).compareTo(new BigDecimal("0")) < 1) {
				returnVal = nf.format(Long.parseLong(number));
			} else {
				returnVal = nf.format(Long.parseLong(number)) + dec;
			}

		} catch (NumberFormatException e) {
			returnVal = number;
		}

		return returnVal;
	}

	/**
	 * 천단위 구분기호와 소수점(반올림)을 표시한다.
	 * 
	 * @param num 텍스트형 숫자 데이터
	 * @param point 소수점 자리수
	 * @return
	 */
	public static String addCommaAndPoint(Object num, String point) {

		String str = "";

		try {

			if (new BigDecimal(String.valueOf(num)).compareTo(new BigDecimal("0")) == 0) { return "0"; }

			String numberData = String.valueOf(num);
			String pointData = empty(point) ? "0" : point;
			str = String.format("%,." + pointData + "f", new BigDecimal(numberData));
		} catch (NumberFormatException e) {
			str = num.toString();
		}

		return str;
	}

	/**
	 * 구분자가 있는 문자열을 배열로 반환(StringTokenizer)한다.
	 * 
	 * @param parameter 대상 문자열
	 * @param separator 구분자(ex. ",")
	 * @return 배열로 변환된 문자열
	 */
	public static String[] toArray(String parameter, String separator) {
		String[] array = null;
		if (parameter != null && !parameter.trim().equals("")) {
			StringTokenizer st = new StringTokenizer(parameter, separator);
			array = new String[st.countTokens()];
			int i = 0;
			while (st.hasMoreTokens()) {
				array[i] = st.nextToken();
				i++;
			}
		}
		return array;
	}

	/**
	 * 주어진 parameter Object에서 regex에 해당하는 문자열을 변환(replace)한다.
	 * 
	 * @param parameter 대상 문자열
	 * @param regex 변환할 문자 ex) '-' , ','
	 * @return 변환된 문자열
	 */
	public static String stripExp(Object parameter, String regex) {
		return toString(parameter).replaceAll(regex, "");
	}

	/**
	 * 주어진 parameter Object를 구분(cls)에 따라 포맷하여 반환한다.
	 * 
	 * @param parameter Object 포맷대상
	 * @param cls String 포맷종류(JUMIN|POST|DATE)
	 * @param format String 포맷문자(ex. "-", "/")
	 * @return 포맷된 문자열
	 */
	public static String formatString(Object parameter, String cls, String format) {
		String formattedStr = "";
		formattedStr = toString(parameter);
		formattedStr = stripExp(formattedStr, format);
		if (cls == null || cls.trim().equals("") || format == null || format.trim().equals("")) {
			// 할수있는게 없음
			return formattedStr;
		} else if (cls.equals("JUMIN")) {
			if (formattedStr.trim().length() == 13) {
				formattedStr = formattedStr.substring(0, 6) + format + formattedStr.substring(6, 13);
			}
		} else if (cls.equals("POST")) {
			if (formattedStr.trim().length() == 6) {
				formattedStr = formattedStr.substring(0, 3) + format + formattedStr.substring(3, 6);
			}
		} else if (cls.equals("DATE")) {
			if (formattedStr.trim().length() == 8) {
				formattedStr = formattedStr.substring(0, 4) + format + formattedStr.substring(4, 6) + format + formattedStr.substring(6, 8);
			} else if (formattedStr.trim().length() == 6) {
				formattedStr = formattedStr.substring(0, 4) + format + formattedStr.substring(4, 6);
			}
		}
		return formattedStr;
	}

	/**
	 * StringTokenizer를 이용해서 문자열을 분리해서 쿼리용(IN ())으로 문자열을 생성한다.
	 *
	 * @param str 대상문자열
	 * @param delim 분리자
	 * @return 쿼리용(IN) 문자열
	 */
	public static String makeQueryString(String str, String delim) {
		StringTokenizer stz = new StringTokenizer(str, delim);
		int i = 0;
		StringBuffer buf = new StringBuffer();

		while (stz.hasMoreTokens()) {
			buf.append((i == 0 ? "" : ",") + "'" + stz.nextToken().trim() + "'");
			i++;
		}
		return buf.toString();
	}

	/**
	 * StringTokenizer를 이용해서 문자열을 분리해서 쿼리용(IN ())으로 문자열을 생성한다.
	 * 1개이상 데이터 사용가능
	 *
	 * @param str 대상문자열
	 * @param delim 분리자
	 * @return 쿼리용(IN) 문자열
	 */
	public static String makeQueryString2(String str, String delim) {
		StringTokenizer stz = new StringTokenizer(str, delim);
		int i = 0;
		StringBuffer buf = new StringBuffer();

		while (stz.hasMoreTokens()) {
			buf.append((i == 0 ? "" : ",") + (i == 0 ? "" : "'") + stz.nextToken().trim() + (stz.hasMoreTokens() ? "'" : ""));
			i++;
		}
		return buf.toString();
	}

	/**
	 * Base64Encoding 방식으로 바이트 배열을 아스키 문자열로 인코딩한다.
	 *
	 * @param strOrgin 대상 문자열
	 * @return 인코딩된 아스키 문자열
	 */
	public static String encode(String strOrgin) {
		byte[] encodeBytes = strOrgin.getBytes();
		byte[] buf = null;
		String strResult = null;

		BASE64Encoder base64Encoder = new BASE64Encoder();
		ByteArrayInputStream bin = new ByteArrayInputStream(encodeBytes);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			base64Encoder.encodeBuffer(bin, bout);

		} catch (IOException e) {
			LogUtil.println("Exception position : CommonUtil - encode(String strOrgin)");
		}

		buf = bout.toByteArray();
		strResult = new String(buf).trim();

		return strResult;
	}

	/**
	 * Base64Encoding 방식으로 아스키 문자열을 바이트 배열로 디코딩한다.
	 * 
	 * @param strDecode 대상 문자열
	 * @return 디코딩된 바이트 배열
	 */
	public static String decode(String strDecode) {
		byte[] buf = null;

		BASE64Decoder base64Decoder = new BASE64Decoder();
		ByteArrayInputStream bin = new ByteArrayInputStream(strDecode.getBytes());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		try {
			base64Decoder.decodeBuffer(bin, bout);
		} catch (IOException e) {
			LogUtil.println("Exception position : CommonUtil - decode(String strDecode)");
		}

		buf = bout.toByteArray();
		return new String(buf);
	}

	/**
	 * 주어진 str이 1자리일 경우 0을 붙여준다.
	 * 
	 * @param 대상 문자열
	 * @return 변환 문자열
	 */
	public static String getAppendZeroStr(String str) {
		if (str.length() == 1) {
			//문자열이 한자리이면 0을 붙여준다.
			return "0" + str;
		} else {
			return str;
		} //end if(str.length() == 1)
	}//end getAppendZeroStr method

	/**
	 * 주어진 text를 HTML 문자열로 변환하여 반환한다.
	 * 
	 * @param text 대상 문자열
	 * @return  변환된 HTML 문자열
	 */
	public static String convertHtmlText(String text) {

		String txt = "";

		txt = CommonUtil.toString(text);

		txt = txt.replaceAll("<", "&lt;");
		txt = txt.replaceAll(">", "&gt;");
		txt = txt.replaceAll("\n", "<br>");

		return txt;
	}

	/**
	 * Object type 변수값이 일치하는지 확인
	 * 
	 * @param obj1
	 * @param obj2
	 * @return Boolean : true / false
	 */
	public static Boolean toEquals(Object obj1, Object obj2) {
		if (obj1 != null && obj2 != null && !"".equals(obj1.toString()) && !"".equals(obj2.toString())) {
			if (obj1.toString().equals(obj2.toString())) { return true; }
		}
		return false;
	}

	/**
	 * Object type 변수가 비어있는지 체크
	 * 
	 * @param obj 
	 * @return Boolean : true / false
	 */
	public static Boolean empty(Object obj) {
		if (obj instanceof String) return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof List) return obj == null || ((List<?>) obj).isEmpty();
		else if (obj instanceof Map) return obj == null || ((Map<?, ?>) obj).isEmpty();
		else if (obj instanceof Object[]) return obj == null || Array.getLength(obj) == 0;
		else return obj == null;
	}

	/**
	 * Object type 변수가 비어있지 않은지 체크
	 * 
	 * @param obj
	 * @return Boolean : true / false
	 */
	public static Boolean notEmpty(Object obj) {
		return !empty(obj);
	}

	/**
	 * 주어진 testStr에 compareStr이 존재(포함)하는지 체크
	 * 
	 * @param testStr 확인 문자열
	 * @param compareStr 비교대상 문자열
	 * @return Boolean : true / false
	 */
	public static Boolean contains(String testStr, String compareStr) {
		return testStr.contains(compareStr);
	}

	/**
	 * 문자열 길이 검증 (byte)
	 * @param str
	 * @param length
	 * @return boolean
	 */
	public static boolean getByteLengthCheck(String str, int length) {
		if (str.getBytes().length > length) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 디렉토리 경로 조작 방지 체크
	 * @param str
	 * @param length
	 * @return boolean
	 */
	public static String pathStrCheck(Object parameter) {
		String str = CommonUtil.toString(parameter);
		if (!"".equals(str)) {
			str = str.replaceAll("/", "");
			str = str.replaceAll("./", "");
			//			str = str.replaceAll(".", "");
			str = str.replaceAll("../", "");
			//			str = str.replaceAll("\\", "");
			str = str.replaceAll("\\\\", "");
			str = str.replaceAll("&", "");
		}
		return str;
	}

	/**
	 * HTTP 응답 헤더 개행문자 제거 체크
	 * @param str
	 * @param length
	 * @return boolean
	 */
	public static String httpResponseStrCheck(Object parameter) {

		String str = CommonUtil.toString(parameter);
		if (!"".equals(str)) {
			str = str.replaceAll("\r", "").replaceAll("\n", "");
		}
		return str;
	}

	/**
	 *  html 특수문자를 html 엔티티코드로 변환
	 *  
	 * @param parameter
	 * @return
	 */
	public static String getSpclStrCnvr(String parameter) {

		String value = parameter;

		if (value == null) { return null; }

		value = value.replaceAll("<script>", "");
		value = value.replaceAll("</script>", "");

		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				strBuff.append("&lt;");
				break;
			case '>':
				strBuff.append("&gt;");
				break;
			case '"':
				strBuff.append("&quot;");
				break;
			case '\'':
				strBuff.append("&apos;");
				break;
			case '&':
				strBuff.append("&amp;");
				break;
			default:
				strBuff.append(c);
				break;
			}
		}
		value = strBuff.toString();

		return value;
	}

	/**
	 *  html 특수문자를 html 엔티티코드로 변환
	 *  
	 * @param parameter
	 * @return
	 */
	public static String[] getSpclStrCnvr(String[] parameter) {

		String[] values = parameter;

		if (values == null) { return null; }

		for (int i = 0; i < values.length; i++) {
			if (values[i] != null) {

				values[i] = values[i].replaceAll("<(?i)script[^<>]*>", "");
				values[i] = values[i].replaceAll("<[/](?i)script[^<>]*>", "");

				StringBuffer strBuff = new StringBuffer();
				for (int j = 0; j < values[i].length(); j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case '<':
						strBuff.append("&lt;");
						break;
					case '>':
						strBuff.append("&gt;");
						break;
					case '"':
						strBuff.append("&quot;");
						break;
					case '\'':
						strBuff.append("&apos;");
						break;
					case '&':
						strBuff.append("&amp;");
						break;
					default:
						strBuff.append(c);
						break;
					}
				}
				values[i] = strBuff.toString();
			} else {
				values[i] = null;
			}
		}

		return values;
	}

	/**
	 * html의 특수문자를 표현하기 위해
	 * 
	 * @param parameter
	 * @return
	 */
	public static String getHtmlStrCnvr(String parameter) {

		String value = parameter;

		if (value == null) { return null; }
		value = value.replaceAll("&amp;", "&");
		value = value.replaceAll("&lt;", "<");
		value = value.replaceAll("&gt;", ">");
		value = value.replaceAll("&quot;", "\"");
		value = value.replaceAll("&apos;", "\'");

		value = value.replaceAll("<(?i)script[^<>]*>", "");
		value = value.replaceAll("<[/](?i)script[^<>]*>", "");
		return value;
	}

	/**
	 * 숫자인지 체크
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isStringNumber(String s) {
		try {
			new BigDecimal(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * URL Encoder (UTF-8)
	 * 
	 * @param url
	 * @return
	 */
	public static String urlEncode(String url) {

		String urlData = url;

		try {
			urlData = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LogUtil.println("Exception position : CommonUtil - urlEncode(String url)");
		}
		return urlData;
	}

	/**
	 * 입력받은 url 에서 한글만 유니코드로 변환함 (UTF-8)
	 * 호환용 한글 자모 \u3130 ~ \u318E || 한글 소리 마디 \uAC00 ~ \uD7A3
	 * 
	 * @param url
	 * @return
	 */
	public static String korUrlEncode(String url) {
		String txt = url;
		char[] txtChar = txt.toCharArray();
		for (int j = 0; j < txtChar.length; j++) {
			if (txtChar[j] >= '\u3130' && txtChar[j] <= '\u318E' || txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
				String targetText = String.valueOf(txtChar[j]);
				txt = txt.replace(targetText, urlEncode(targetText));
			}
		}
		return txt;
	}

	/**
	 * String 문자열 합치기<br/>
	 * 정적검사에서 String + String 대신 StringBuffer.append() 를 권장함
	 *
	 * @param String... strings
	 * @return
	 */
	public static String stringAppend(String... strings) {
		StringBuffer rtnStringBuffer = new StringBuffer();

		if (notEmpty(strings)) {
			for (int i = 0; i < strings.length; i++) {
				StringBuffer str = new StringBuffer(strings[i]);
				rtnStringBuffer.append(str);
			}
		}

		return rtnStringBuffer.toString();
	}
}