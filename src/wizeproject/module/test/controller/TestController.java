package wizeproject.module.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wizeproject.framework.util.CommonUtil;
import wizeproject.framework.util.LogUtil;

@Controller
public class TestController {

	/**
	 * TMPL 테스트
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test/tmplTest.do")
	public ModelAndView tmplTest() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/tmplTest");
		
		return mav;
	}

	/**
	 * HTTP 응답 헤더 개행문자 제거 테스트
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test/httpResponseStrTest.do")
	public ModelAndView httpResponseStrTest() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/httpResponseStrTest");

		String url = "안녕하세요\n 저는 사람입니다.\r메롱";

		LogUtil.println("[C]" + url);
		LogUtil.println("[C]" + CommonUtil.httpResponseStrCheck(url));

		return mav;
	}

	/**
	 * HTTP 응답 URL 확인
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test/httpResponseURLTest.do")
	public ModelAndView httpResponseURLTest(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test/httpResponseStrTest");

		LogUtil.println("[C]" + request.getRequestURI());
		LogUtil.println("[C]" + request.getRequestURL());

		return mav;
	}
}