package spring.controller.Callable;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

@Controller
@RequestMapping("/async/callable")
public class CallableController {

private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/response-body")
	public @ResponseBody Callable<String> processUpload(HttpServletRequest request,  
            final HttpServletResponse response)  {

		  System.out.println("线程名称："+Thread.currentThread().getName());  
	        return new Callable<String>() {  
	            public String call() throws Exception {  
	                try {  
	                    System.out.println("线程名称："+Thread.currentThread().getName());  
	                    response.setContentType("text/plain;charset=utf-8");  
	                    response.getWriter().write("CallableController");  
	                    logger.info("进入 异步的方法");
	                    response.getWriter().close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	                return null;  
	            }  
	        };  
	}

	@RequestMapping("/view")
	public Callable<String> callableWithView(final Model model) {

		return new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				model.addAttribute("foo", "bar");
				model.addAttribute("fruit", "apple");
				return "views/html";
			}
		};
	}

	@RequestMapping("/exception")
	public @ResponseBody Callable<String> callableWithException(
			final @RequestParam(required=false, defaultValue="true") boolean handled) {

		return new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				if (handled) {
					// see handleException method further below
					throw new IllegalStateException("Callable error");
				}
				else {
					throw new IllegalArgumentException("Callable error");
				}
			}
		};
	}

	@RequestMapping("/custom-timeout-handling")
	public @ResponseBody WebAsyncTask<String> callableWithCustomTimeoutHandling() {

		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Callable result";
			}
		};

		return new WebAsyncTask<String>(1000, callable);
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}

}
