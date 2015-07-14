package com.dingmao.mvcdemo.calendar.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingmao.mvcdemo.calendar.model.Calendar;
import com.dingmao.mvcdemo.calendar.model.CalendarMx;
import com.dingmao.mvcdemo.calendar.service.CalendarService;
import com.dingmao.mvcdemo.dict.service.DictService;
import com.dingmao.platform.controller.BaseController;
import com.dingmao.platform.service.ServiceException;
import com.dingmao.platform.util.json.JsonUtils;
import com.dingmao.platform.vo.MessageVo;

/**
 * 功能说明:日程管理
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Controller
@RequestMapping(value = "/calendar")
public class CalendarController extends BaseController<Calendar> {
	/**
	 * 日程业务类注入
	 */
	@Autowired
	private CalendarService calendarService;

	private MessageVo message;

	/**
	 * 功能说明:進入日程管理列表页.
	 * 
	 * @return WEB-INF/pages/calendar/list.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterList", method = RequestMethod.GET)
	public String enterList(Model model) {
		model.addAttribute("rclxs", DictService.getDicMap("Rclx"));
		return "calendar/list";
	}

	/**
	 * 功能说明:进入日程管理添加页.
	 * 
	 * @return WEB-INF/pages/calendar/add.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterAdd", method = RequestMethod.GET)
	public String enterAdd(Model model) {
		model.addAttribute("rclxs", DictService.getDicMap("Rclx"));
		return "calendar/add";
	}

	/**
	 * 功能说明:进入日程管理编辑页.
	 * 
	 * @return WEB-INF/pages/calendar/edit.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterEdit/{id}", method = RequestMethod.GET)
	public String enterEdit(@PathVariable String id, Model model,
			PrintWriter out) {
		Calendar calendar = this.calendarService.getById(id);
		if (calendar.getRczt().equals("草稿")) {
			model.addAttribute("calendar", calendar);
			model.addAttribute("rcMx", JsonUtils.listToJson(
					calendar.getCalendarMx(), new String[] { "calendar" }));
			return "calendar/edit";
		} else {
			model.addAttribute("message", calendar.getRczt());
			return "common/result";
		}
	}

	/**
	 * 功能说明：修改页面数据加载
	 * 
	 * @param id
	 *            主键
	 * @return 实体
	 */
	@RequestMapping(value = "/enterEnditMx/{id}", method = RequestMethod.POST)
	@ResponseBody
	public List<CalendarMx> enterEditMx(@PathVariable String id) {
		Calendar Calendar = this.calendarService.getById(id);
		return Calendar.getCalendarMx();
	}

	/**
	 * 功能说明:进入日程管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @return WEB-INF/pages/calendar/detail.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterDetail/{id}", method = RequestMethod.GET)
	public String enterDetail(@PathVariable String id, Model model) {
		Calendar calendar = this.calendarService.getById(id);
		model.addAttribute("calendar", calendar);
		return "calendar/detail";
	}

	/**
	 * 功能说明:分页查询列表.
	 * 
	 * @param request
	 *            请求对象.
	 * @return grid 的对象
	 * @exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request) {
		/*
		 * Page<Calendar> result = this.calendarService.findPage(super
		 * .buildGridPager(request)); Map<String, Object> grid =
		 * super.buildPageJson(result);
		 */
		Map<String, Object> grid = null;
		return grid;

	}

	/**
	 * 功能说明:拟制时，保存
	 * 
	 * @param calendar
	 *            日程实体
	 * @return 保存状态信息
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public MessageVo save(@RequestBody Calendar calendar) {
		Calendar newCalendar = this.calendarService.save(calendar);
		if (newCalendar.getId() != null) {
			message = new MessageVo(true, "保存成功");
		} else {
			message = new MessageVo(false, "保存失败");
		}
		return message;
	}

	/**
	 * 功能说明: 更新操作
	 * 
	 * @param Calendar
	 *            日程实体
	 * @return 状态信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public MessageVo update(@RequestBody Calendar calendar) {
		calendar.setRclx(DictService.getDicKey("Rclx", calendar.getRclx()));
		try {
			this.calendarService.update(calendar);
			message = new MessageVo(true, "更新成功");

		} catch (ServiceException e) {
			message = new MessageVo(false, "更新失败");
		}
		return message;
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
	public MessageVo delete(@RequestParam List<String> ids) {
		try {
			List<String> dels = new ArrayList<String>();
			for (String id : ids) {
				Calendar Calendar = this.calendarService.getById(id);
				if (Calendar.getRczt().equals("草稿")) {
					dels.add(id);// 保存可以被删除的ID
				}
			}
			Integer success = dels.size();
			if (success > 0) {
				this.calendarService.delete(dels);
			}
			message = new MessageVo(true, "删除成功：" + success + "/" + ids.size()
					+ "条");
		} catch (Exception e) {
			message = new MessageVo(false, "刪除失败");
		}
		return message;
	}

}
