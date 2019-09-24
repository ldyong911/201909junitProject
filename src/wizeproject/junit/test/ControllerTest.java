package wizeproject.junit.test;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import wizeproject.junit.testconfig.WebTestConfig;

public class ControllerTest extends WebTestConfig {

	/**
	 * 컨트롤러 테스트
	 *
	 * @throws Exception
	 */
	@Test
	public void controllerTest() throws Exception {
		/***Given***/
		MvcResult mvcResult = mockMvc.perform(get("/test/httpResponseStrTest.do")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***When***/

		/***Then***/
		assertEquals("test/httpResponseStrTest", viewName);
	}
}