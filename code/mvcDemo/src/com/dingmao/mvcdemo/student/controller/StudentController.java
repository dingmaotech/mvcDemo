package com.dingmao.mvcdemo.student.controller;

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

import com.dingmao.mvcdemo.student.model.Student;
import com.dingmao.mvcdemo.student.service.StudentService;
import com.dingmao.platform.controller.BaseController;
import com.dingmao.platform.poi.excel.util.ExcelExportUtil;
import com.dingmao.platform.poi.excel.util.ExcelImportUtil;
import com.dingmao.platform.poi.excel.vo.ExcelTitleVo;
import com.dingmao.platform.poi.excel.vo.ImportParam;
import com.dingmao.platform.util.BrowserUtil;
import com.dingmao.platform.util.PropertiesUtil;

/**
 * 功能说明:学生管理请求处理类
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
@Controller
@RequestMapping("/student")
@SessionAttributes(value = { "userName" })
public class StudentController extends BaseController<Student> {
	private Log logger = LogFactory.getLog(StudentController.class);

	/**
	 * 学生业务类注入
	 */
	@Autowired
	private StudentService studentService;

	/**
	 * 功能说明:进入学生管理列表页.
	 * 
	 * @return WEB-INF/pages/student/list.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterList", method = RequestMethod.GET)
	public String enterList(Model model) {
		logger.debug("StudentController  enterList");
		List<Student> students = this.studentService.findAll();
		model.addAttribute("studentList", students);
		return "student/list";
	}

	/**
	 * 功能说明:进入学生管理添加页.
	 * 
	 * @return WEB-INF/pages/student/add.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterAdd", method = RequestMethod.GET)
	public String enterAdd(Model model) {
		return "student/add";
	}

	/**
	 * 功能说明:进入学生管理编辑页.
	 * 
	 * @return WEB-INF/pages/student/edit.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterEdit/{id}", method = RequestMethod.GET)
	public String enterEdit(@PathVariable String id, Model model) {
		Student student = this.studentService.getById(id);
		model.addAttribute("student", student);
		return "student/edit";
	}

	/**
	 * 功能说明:进入学生管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @return WEB-INF/pages/student/detail.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterDetail/{id}", method = RequestMethod.GET)
	public String enterDetail(@PathVariable String id, Model model) {
		Student student = this.studentService.getById(id);
		model.addAttribute("student", student);
		return "student/detail";
	}

	/**
	 * 功能说明:添加操作
	 * 
	 * @param student
	 *            学生实体
	 * @return 保存状态信息
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Student student) {
		Student newStudent = this.studentService.save(student);
		ModelAndView mav = new ModelAndView();
		if (newStudent.getStuId() != null) {
			mav.setView(new RedirectView("enterList.do"));
		} else {
			mav.setViewName("common/error");
		}
		return mav;
	}

	/**
	 * 功能说明: 更新操作
	 * 
	 * @param Student
	 *            学生实体
	 * @return 状态信息
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Student student) {
		this.studentService.update(student);
		ModelAndView mav = new ModelAndView(new RedirectView("enterList.do"));
		return mav;
	}

	/**
	 * 功能说明:进入学生管理详情页.
	 * 
	 * @param id
	 *            实体主鍵ID.
	 * @param model
	 *            model 视图模型.
	 * @exception
	 */
	@RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String id, Model model) {
		ModelAndView mav = new ModelAndView("redirect:/student/enterList.do");
		int size = this.studentService.delete(id);
		if (size == 0) {
			mav = new ModelAndView("common/error");
		}
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
		List<Student> students = this.studentService.findAll();
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "学生信息";
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
			ExcelTitleVo title = new ExcelTitleVo("学生列表", "导出人:"
					+ (String) map.get("userName"), "导出学生信息");
			workbook = ExcelExportUtil.exportExcel(title, Student.class,
					students);
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

	/*	*//**
	 * 导出excel 统计模板
	 * 
	 * @param request
	 * @param response
	 */
	/*
	 * @RequestMapping(value="/exportStatisticsTemplate", method =
	 * RequestMethod.GET) public void exportStatisticsTemplate(Student student,
	 * HttpServletRequest request, HttpServletResponse response, DataGrid
	 * dataGrid) { response.setContentType("application/vnd.ms-excel"); String
	 * fileName = null; OutputStream out = null; try { fileName = "学生统计信息"; //
	 * 根据浏览器进行转码，使其支持中文文件名 if (BrowserUtil.isIE(request)) { response.setHeader(
	 * "content-disposition", "attachment;filename=" +
	 * java.net.URLEncoder.encode(fileName, "UTF-8") + ".xls");//
	 * 这个地方要修改成和模板一样的文件类型 } else { String newtitle = new
	 * String(fileName.getBytes("UTF-8"), "ISO8859-1");
	 * response.setHeader("content-disposition", "attachment;filename=" +
	 * newtitle + ".xls"); }
	 * 
	 * 
	 * } catch (Exception e) { } finally { try { out.flush(); out.close(); }
	 * catch (IOException e) {
	 * 
	 * } } }
	 */

	/**
	 * 导出excel 学生模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcelTemplate", method = RequestMethod.GET)
	public void exportExcelTemplate(Student student,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "学生信息模板";
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
			ExcelTitleVo title = new ExcelTitleVo("学生信息模板", "导出人:"
					+ (String) map.get("userName"), "导出信息");
			workbook = ExcelExportUtil.exportExcel(title, Student.class, null);
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

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(value="/importExcel", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public MessageDto importExcel(HttpServletRequest request,
	 * HttpServletResponse response) { MessageDto message = new MessageDto(true,
	 * ""); MultipartHttpServletRequest multipartRequest =
	 * (MultipartHttpServletRequest) request; Map<String, MultipartFile> fileMap
	 * = multipartRequest.getFileMap(); for (Map.Entry<String, MultipartFile>
	 * entity : fileMap.entrySet()) { MultipartFile file = entity.getValue();//
	 * 获取上传文件对象 ImportParam params = new ImportParam();
	 * params.setSaveUrl(PropertiesUtil
	 * .getInstance().getPropertiesValue("file.upload.path")+"/excelUpload");
	 * params.setTitleRows(2); params.setSecondTitleRows(2);
	 * params.setNeedSave(true); try { List<Student> listStudents =
	 * (List<Student>) ExcelImportUtil .importExcelByIs(file.getInputStream(),
	 * Student.class, params); for (Student student : listStudents) { if
	 * (student.getStuName() != null) { studentService.save(student); } }
	 * message.setMessage("文件导入成功！"); } catch (Exception e) {
	 * message.setSuccess(false); message.setMessage("文件导入失败！");
	 * logger.error(e); } finally { try { file.getInputStream().close(); } catch
	 * (IOException e) { e.printStackTrace(); } } } return message; }
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ModelAndView importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/student/enterList.do");
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
				List<Student> listStudents = (List<Student>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(), Student.class,
								params);
				for (Student student : listStudents) {
					if (student.getStuName() != null) {
						studentService.save(student);
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
