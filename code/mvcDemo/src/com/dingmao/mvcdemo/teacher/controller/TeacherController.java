package com.dingmao.mvcdemo.teacher.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dingmao.mvcdemo.teacher.model.Teacher;
import com.dingmao.mvcdemo.teacher.service.TeacherService;
import com.dingmao.platform.controller.BaseController;
import com.dingmao.platform.poi.excel.util.ExcelExportUtil;
import com.dingmao.platform.poi.excel.util.ExcelImportUtil;
import com.dingmao.platform.poi.excel.vo.ExcelTitleVo;
import com.dingmao.platform.poi.excel.vo.ImportParam;
import com.dingmao.platform.util.BrowserUtil;
import com.dingmao.platform.util.PropertiesUtil;

/**
 * 功能说明:教师管理请求处理类
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Controller
@RequestMapping("/teacher")
@SessionAttributes({ "userName" })
public class TeacherController extends BaseController<Teacher> {
	private Log logger = LogFactory.getLog(TeacherController.class);

	/**
	 * 教师业务类注入
	 */
	@Autowired
	private TeacherService teacherService;

	/**
	 * 功能说明:进入教师管理列表页.
	 * 
	 * @return WEB-INF/pages/teacher/list.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterList", method = RequestMethod.GET)
	public String enterList(Model model) {
		logger.debug("TeacherController  enterList");
		List<Teacher> teachers = this.teacherService.findAll();
		model.addAttribute("teacherList", teachers);
		return "teacher/list";
	}

	/**
	 * 功能说明:进入教师管理添加页.
	 * 
	 * @return WEB-INF/pages/teacher/add.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterAdd", method = RequestMethod.GET)
	public String enterAdd(Model model) {
		return "teacher/add";
	}

	/**
	 * 功能说明:进入教师管理编辑页.
	 * 
	 * @return WEB-INF/pages/teacher/edit.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterEdit/{id}", method = RequestMethod.GET)
	public String enterEdit(@PathVariable String id, Model model) {
		Teacher teacher = this.teacherService.getById(id);
		model.addAttribute("teacher", teacher);
		return "teacher/edit";
	}

	/**
	 * 功能说明:进入教师管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @return WEB-INF/pages/teacher/detail.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterDetail/{id}", method = RequestMethod.GET)
	public String enterDetail(@PathVariable String id, Model model) {
		Teacher teacher = this.teacherService.getById(id);
		model.addAttribute("teacher", teacher);
		return "teacher/detail";
	}

	/**
	 * 功能说明:添加操作
	 * 
	 * @param teacher
	 *            教师实体
	 * @return 保存状态信息
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Teacher teacher) {
		Teacher newTeacher = this.teacherService.save(teacher);
		ModelAndView mav = new ModelAndView();
		if (newTeacher.getId() != null) {
			mav.setView(new RedirectView("enterList.do"));
		} else {
			mav.setViewName("common/error");
		}
		return mav;
	}

	/**
	 * 功能说明: 更新操作
	 * 
	 * @param Teacher
	 *            教师实体
	 * @return 状态信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Teacher teacher) {
		this.teacherService.update(teacher);
		ModelAndView mav = new ModelAndView(new RedirectView("enterList.do"));
		return mav;
	}

	/**
	 * 功能说明:进入教师管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @exception
	 */
	@RequestMapping(value = "/deleteTeacher/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTeacher(@PathVariable String id, Model model) {
		int size = this.teacherService.delete(id);
		ModelAndView mav = new ModelAndView("redirect:/teacher/enterList.do");
		return mav;
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response,  ModelMap map) {
		List<Teacher> teachers = this.teacherService.findAll();
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "教师信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtil.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(fileName, "UTF-8")
								+ ".xls");// 这个地方要修改成和模板一样的文件类型
			} else {
				String newtitle = new String(fileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}

			// 产生工作簿对象
			Workbook workbook = null;
			ExcelTitleVo title = new ExcelTitleVo("教师列表", "导出人:"
					+ (String) map.get("userName"), "导出教师信息");
			workbook = ExcelExportUtil.exportExcel(title, Teacher.class,
					teachers);
			out = response.getOutputStream();
			workbook.write(out);
		} catch (Exception e) {
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 导出excel 教师模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcelTemplate", method = RequestMethod.GET)
	public void exportExcelTemplate(Teacher teacher,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "教师信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtil.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(fileName, "UTF-8")
								+ ".xls");// 这个地方要修改成和模板一样的文件类型
			} else {
				String newtitle = new String(fileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}

			// 产生工作簿对象
			Workbook workbook = null;
			ExcelTitleVo title = new ExcelTitleVo("教师信息模板", "导出人:"
					+ (String) map.get("userName"), "导出信息");
			workbook = ExcelExportUtil.exportExcel(title, Teacher.class, null);
			out = response.getOutputStream();
			workbook.write(out);
		} catch (Exception e) {
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {

			}
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ModelAndView importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/teacher/enterList.do");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParam params = new ImportParam();
			params.setSaveUrl(PropertiesUtil.getInstance().getPropertiesValue(
					"file.upload.path")
					+ "/excelUpload");
			params.setTitleRows(2);
			params.setSecondTitleRows(2);
			params.setNeedSave(true);
			try {
				List<Teacher> listTeachers = (List<Teacher>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(), Teacher.class,
								params);
				for (Teacher teacher : listTeachers) {
					if (teacher.getName() != null) {
						teacherService.save(teacher);
					}
				}
			} catch (Exception e) {
				logger.error(e);
			} finally {
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mav;
	}
}
