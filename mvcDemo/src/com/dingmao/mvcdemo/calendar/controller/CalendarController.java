package com.dingmao.mvcdemo.calendar.controller;

import com.dingmao.mvcdemo.calendar.model.Calendar;
import com.dingmao.mvcdemo.calendar.service.CalendarService;
import com.dingmao.platform.controller.BaseController;
import com.dingmao.platform.dto.MessageDto;
import com.dingmao.platform.service.ServiceException;
import com.dingmao.platform.util.json.JsonUtils;
import java.io.PrintWriter;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class CalendarController extends BaseController
{

	private Log log = LogFactory.getLog(CalendarController.class);
	private CalendarService calendarService;
	private MessageDto message;


	public String enterList(Model model)
	{
		return "calendar/list";
	}

	public String enterAdd(Model model)
	{
		return "calendar/add";
	}

	public String enterEdit(String id, Model model, PrintWriter out)
	{
		Calendar calendar = (Calendar)calendarService.getById(id);
		if (calendar.getRczt().equals("草稿"))
		{
			model.addAttribute("calendar", calendar);
			model.addAttribute("rcMx", JsonUtils.listToJson(calendar.getCalendarMx(), new String[] {
				"calendar"
			}));
			return "calendar/edit";
		} else
		{
			model.addAttribute("message", calendar.getRczt());
			return "common/result";
		}
	}

	public List enterEditMx(String id)
	{
		Calendar Calendar = (Calendar)calendarService.getById(id);
		return Calendar.getCalendarMx();
	}

	public String enterDetail(String id, Model model)
	{
		Calendar calendar = (Calendar)calendarService.getById(id);
		model.addAttribute("calendar", calendar);
		return "calendar/detail";
	}

	public ModelAndView doSave(Calendar calendar)
	{
		Calendar newCalendar = (Calendar)calendarService.save(calendar);
		ModelAndView mav = new ModelAndView();
		if (newCalendar.getId() != null)
			mav.setViewName("common/ok");
		else
			mav.setViewName("common/error");
		return mav;
	}

	public MessageDto save(Calendar calendar)
	{
		Calendar newCalendar = (Calendar)calendarService.save(calendar);
		if (newCalendar.getId() != null)
			message = new MessageDto(true, "保存成功");
		else
			message = new MessageDto(false, "保存失败");
		return message;
	}

	public MessageDto update(Calendar calendar)
	{
		try
		{
			calendarService.update(calendar);
			message = new MessageDto(true, "更新成功");
		}
		catch (ServiceException e)
		{
			message = new MessageDto(false, "更新失败");
		}
		return message;
	}

	public MessageDto delete(List ids)
	{
		try
		{
			List dels = new ArrayList();
			for (Iterator iterator = ids.iterator(); iterator.hasNext();)
			{
				String id = (String)iterator.next();
				Calendar Calendar = (Calendar)calendarService.getById(id);
				if (Calendar.getRczt().equals("草稿"))
					dels.add(id);
			}

			Integer success = Integer.valueOf(dels.size());
			if (success.intValue() > 0)
				calendarService.delete(dels);
			message = new MessageDto(true, (new StringBuilder("删除成功：")).append(success).append("/").append(ids.size()).append("条").toString());
		}
		catch (Exception e)
		{
			message = new MessageDto(false, "刪除失败");
		}
		return message;
	}
}