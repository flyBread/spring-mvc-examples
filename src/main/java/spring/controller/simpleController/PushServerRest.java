package spring.controller.simpleController;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class PushServerRest {

  private static final Logger logger = LoggerFactory.getLogger(PushServerRest.class);

  public static final MediaType TEXT_JAVASCRIPT = new MediaType("text", "plain", Charset
      .forName("UTF-8"));

  @RequestMapping(value = "/hello")
  @ResponseBody
  public ResponseEntity<String> getMsgsHistory(HttpServletRequest httpServletRequest) {
    ResponseEntity<String> result = new ResponseEntity<String>("helloworld", getResponseHeaders(),
        HttpStatus.OK);
    logger.info("测试程序");
    return result;
  }

  private MultiValueMap<String, String> getResponseHeaders() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setContentType(TEXT_JAVASCRIPT);
    return responseHeaders;
  }
}