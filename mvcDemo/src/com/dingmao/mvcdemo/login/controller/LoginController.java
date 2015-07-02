package com.dingmao.mvcdemo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.mvcdemo.user.service.UserService;
import com.dingmao.platform.dto.MessageDto;



/**
 * 登陆初始化控制器
 * 
 */
@Controller
@RequestMapping("/loginAction")
public class LoginController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转到用户登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/enterLogin")
	public String enterList(Model model) {
		return "login";
	}
	
	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginAction/login.do"));
		return modelAndView;
	}

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		User user = new User();
		user.setUsername("admin");
		String newPwd = "admin";
		userService.pwdInit(user, newPwd);
		modelAndView = new ModelAndView(new RedirectView(
				"loginAction/enterLogin.do"));
		return modelAndView;
	}

	/**
	 *  检查用户名称
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkuser")
	@ResponseBody
	public MessageDto checkuser(HttpServletRequest request, User loginUser,
			ModelMap modelMap) {
		MessageDto message = new MessageDto(true, "校验成功");
		User user = userService.checkUserPwd(loginUser);
		if (user == null)
			message = new MessageDto(false, "用户或密码有误，请重新输入");
		return message;
	}

	/**
	 * 用户登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(ModelMap modelMap, HttpServletRequest request,
			User loginUser) {
		ModelAndView mav = new ModelAndView("login");
		User user = userService.getUserByUsername(loginUser.getUsername());
		if (user != null && loginUser.getPassword().equals(user.getPassword())) {
			modelMap.put("userName", user.getUsername());
			String url = "redirect:/user/enterList.do";
			mav = new ModelAndView(url);
		}
		return mav;
	}
}
