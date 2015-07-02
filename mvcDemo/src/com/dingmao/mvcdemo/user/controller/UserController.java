package com.dingmao.mvcdemo.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.mvcdemo.user.service.UserService;
import com.dingmao.platform.dto.MessageDto;

/**
 * 用户操作请求处理类
 * 
 * @author think
 * 
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value={"userName"})
public class UserController {

	/**
	 * 用户业务类注入
	 */
	@Autowired
	private UserService userService;

	/**
	 * 消息
	 */
	private MessageDto message;

	/**
	 * 功能说明:進入用户管理列表页.
	 * 
	 * @return WEB-INF/pages/user/list.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterList", method = RequestMethod.GET)
	public String enterList(Model model) {
		List<User>   users = this.userService.findAll();
		model.addAttribute("userList", users);
		return "user/list";
	}

	/**
	 * 功能说明:进入用户管理添加页.
	 * 
	 * @return WEB-INF/pages/user/add.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterAdd", method = RequestMethod.GET)
	public String enterAdd(Model model) {
		return "user/add";
	}

	/**
	 * 功能说明:进入用户管理编辑页.
	 * 
	 * @return WEB-INF/pages/user/edit.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterEdit/{id}", method = RequestMethod.GET)
	public String enterEdit(@PathVariable String id, Model model) {
		User user = this.userService.getById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	/**
	 * 功能说明:进入用户管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @return WEB-INF/pages/user/detail.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterDetail/{id}", method = RequestMethod.GET)
	public String enterDetail(@PathVariable String id, Model model) {
		User user = this.userService.getById(id);
		model.addAttribute("user", user);
		return "user/detail";
	}

	/**
	 * 功能说明:添加操作
	 * 
	 * @param user
	 *            用户实体
	 * @return 保存状态信息
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(User user) {
		User newUser = this.userService.save(user);
		ModelAndView mav = new ModelAndView();
		if (newUser.getId() != null) {
			mav.setView(new RedirectView("enterList.do"));
		} else {
			mav.setViewName("common/error");
		}
		return mav;
	}

	/**
	 * 功能说明: 更新操作
	 * 
	 * @param User
	 *            用户实体
	 * @return 状态信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(User user) {
		this.userService.update(user);
		ModelAndView mav = new ModelAndView(new RedirectView("enterList.do"));
		return mav;
	}

	/**
	 * 功能说明: 删除操作
	 * 
	 * @param ids
	 *            所选的删除主键
	 * @return 状态信息
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public MessageDto delete(@RequestParam List<String> ids) {
		try {
			List<String> dels = new ArrayList<String>();
			for (String id : ids) {
				User User = this.userService.getById(id);
				dels.add(id);// 保存可以被删除的ID
			}
			Integer success = dels.size();
			if (success > 0) {
				this.userService.delete(dels);
			}
			message = new MessageDto(true, "删除成功：" + success + "/" + ids.size()
					+ "条");
		} catch (Exception e) {
			message = new MessageDto(false, "刪除失败");
		}
		return message;
	}

	/**
	 * 功能说明:进入用户管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @exception
	 */
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable String id, Model model) {
		int size = this.userService.delete(id);
		ModelAndView mav = new ModelAndView("redirect:/user/enterList.do");
		return mav;  
	}
}
