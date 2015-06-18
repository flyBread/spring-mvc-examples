package spring.controller.simpleController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class PushServerRest {

  private static final Logger logger = LoggerFactory.getLogger(PushServerRest.class);

  public static final MediaType TEXT_JAVASCRIPT = new MediaType("text", "plain", Charset
      .forName("UTF-8"));

  @RequestMapping(value = "/hello")
  @ResponseBody
  public ResponseEntity<String> getMsgsHistory(HttpServletRequest httpServletRequest,
      HttpServletResponse response) throws UnsupportedEncodingException, IOException {
    ResponseEntity<String> result = new ResponseEntity<String>("helloworld", getResponseHeaders(),
        HttpStatus.OK);
    logger.info("测试程序");
    String resultString = "某系量的字符结果";
    response.getOutputStream().write(resultString.getBytes("UTF-8"));
    return result;
  }

  @ModelAttribute
  void beforeInvokingHandlerMethod(HttpServletRequest request) {
    request.setAttribute("para", "preSet");
    request.setAttribute("extend", new Date());
  }

  @RequestMapping(value = "before/setPara", method = RequestMethod.GET)
  public @ResponseBody String custom(@RequestAttribute("para") String para,
      @RequestAttribute("extend") Object extend) {
    return "Got 'foo' request attribute value '" + para + "'" + " " + extend.toString();
  }

  private MultiValueMap<String, String> getResponseHeaders() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setContentType(TEXT_JAVASCRIPT);
    return responseHeaders;
  }
}