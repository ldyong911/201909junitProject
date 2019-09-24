package wizeproject.junit.test;

import org.junit.Test;

import wizeproject.framework.util.CommonUtil;
import wizeproject.framework.util.LogUtil;
import wizeproject.junit.testconfig.LogicTestConfig;

public class DaoServiceTest extends LogicTestConfig {

	/**
	 * 개행문자 제거 확인
	 *
	 * @throws Exception
	 */
	@Test
	public void testHttpResponseStr() throws Exception {
		/***Given***/
		String url = "안녕하세요\n 저는 사람입니다.\r메롱";

		/***When***/
		LogUtil.println("[C]" + url);
		LogUtil.println("[C]" + CommonUtil.httpResponseStrCheck(url));

		/***Then***/
	}

	/**
	 * 공백 비교 확인
	 *
	 * @throws Exception
	 */
	@Test
	public void spaceExcludeLength() throws Exception {
		String spaceCheck = "안녕하세요 저는 자바입니다.";
		int length = spaceCheck.length();
		for (int i = 0; i < spaceCheck.length(); i++) {
			if (spaceCheck.charAt(i) == ' ') {
				length = length - 1;
				LogUtil.println("공백 : " + i);
			}
		}

	}
}