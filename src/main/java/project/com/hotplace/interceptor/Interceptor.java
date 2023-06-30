package project.com.hotplace.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Interceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle()...");

//		String sPath=request.getServletPath();
//		log.info("preHandle-ServletPath...{}",sPath);
//		
//		String user_id = (String) session.getAttribute("user_id");
//		log.info("preHandle()-session...{}",user_id);
//		
		
//		if(sPath.equals("/selectAll.do")||sPath.equals("/selectOne.do")) {
//			
//			if(user_id==null) {
//			response.sendRedirect("login.do");
//			return false;
//			}
//		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle()...");

	}

}
