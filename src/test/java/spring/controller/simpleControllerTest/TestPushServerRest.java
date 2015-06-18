package spring.controller.simpleControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import src.java.spring.controller.CallableTest.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestPushServerRest extends AbstractContextControllerTests {

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

  @Test
  public void responseBody() throws Exception {
    MockHttpServletRequestBuilder result = get("/test/hello");
    ResultActions results = this.mockMvc.perform(result);
    MvcResult returns = results.andReturn();
    System.out.println(new String(returns.getResponse().getContentAsByteArray()));
  }

  @Test
  public void testBefore() throws Exception {
    MockHttpServletRequestBuilder result = get("/test/before/setPara");
    ResultActions results = this.mockMvc.perform(result);
    MvcResult returns = results.andReturn();
    System.out.println(new String(returns.getResponse().getContentAsByteArray()));
  }

}
