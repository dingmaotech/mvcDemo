package com.dingmao.mvcdemo.user.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dingmao.mvcdemo.student.model.Student;
import com.dingmao.mvcdemo.student.service.StudentDataHandler;
import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.mvcdemo.user.service.UserService;
import com.dingmao.platform.model.DataGrid;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.poi.excel.util.ExcelExportUtil;
import com.dingmao.platform.poi.excel.util.ExcelImportUtil;
import com.dingmao.platform.poi.excel.vo.ExcelTitleVo;
import com.dingmao.platform.poi.excel.vo.ImportParam;
import com.dingmao.platform.util.BrowserUtil;
import com.dingmao.platform.util.PropertiesUtil;
import com.dingmao.platform.vo.MessageVo;
import com.dingmao.platform.vo.datatable.DataTableVo;
import com.dingmao.platform.vo.datatable.DataTables;
import com.dingmao.platform.vo.datatables.wrapper.DataTableWrapper;
import com.dingmao.platform.vo.datatables.wrapper.model.DataTable;
import com.dingmao.platform.vo.datatables.wrapper.model.FnCallBack;

/**
 * 用户操作请求处理类
 * 
 * @author think
 * 
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value = { "userName" })
public class UserController {
	private Log logger = LogFactory.getLog(UserController.class);

	/**
	 * 用户业务类注入
	 */
	@Autowired
	private UserService userService;

	/**
	 * 消息
	 */
	private MessageVo message;
	
	private List<Student> list;
	private int itotalRecords;
	private int iTotalDisplay;

	/**
	 * 功能说明:進入用户管理列表页.
	 * 
	 * @return WEB-INF/pages/user/list.jsp
	 * @exception
	 */
	@RequestMapping(value = "/enterList", method = RequestMethod.GET)
	public String enterList(Model model) {
		List<User> users = this.userService.findAll();
		model.addAttribute("userList", users);
		return "user/list";
	}

	/**
	 * 功能说明:AJAX请求获取用户数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response) {
		DataTables dataGrid = new DataTables(request);
		Integer sEcho = dataGrid.getEcho();
		// 开始记录
		int iDisplayStart = dataGrid.getDisplayStart();

		// 单页显示记录数
		int iTotalRecords = dataGrid.getDisplayLength();

		Page<User> page = new Page<User>(iDisplayStart,iTotalRecords);

		// 定义列名
		String[] cols = { "id", "username" };
		// 获取客户端需要那一列排序
		String orderColumn = "0";
		orderColumn = request.getParameter("order[0][column]");
		if (orderColumn != null) {
			orderColumn = cols[Integer.parseInt(orderColumn)];
		}

		// 获取排序方式 默认为asc
		String orderDir = "asc";
		orderDir = request.getParameter("order[0][dir]");

		// 获取用户过滤框里的字符
		String searchValue = request.getParameter("search[value]");

		List<String> sArray = new ArrayList<String>();
		if (searchValue != null && !searchValue.equals("")) {
			sArray.add(" id like '%" + searchValue + "%'");
			sArray.add(" username like '%" + searchValue + "%'");
		}

		String individualSearch = "";
		if (sArray.size() == 1) {
			individualSearch = sArray.get(0);
		} else if (sArray.size() > 1) {
			for (int i = 0; i < sArray.size() - 1; i++) {
				individualSearch += sArray.get(i) + " or ";
			}
			individualSearch += sArray.get(sArray.size() - 1);
		}

		String hql = "from  User where 1=1 ";

		if (individualSearch != "") {
			hql += " where " + individualSearch;
		}
		if (orderColumn != null && !"".equals(orderColumn)) {
			hql += " order by " + orderColumn + " " + orderDir;
		}

		Page<User> userpage = this.userService.findPage(page, hql,
				new Object[] {});
		List<User> users = userpage.getResult();
		iTotalRecords = users.size();
		/**
		 * 过滤后总记录数
		 */
		Integer iTotalDisplayRecords = (int) userpage.getTotalRows();
		DataTableVo dr = new DataTableVo(iTotalRecords, iTotalDisplayRecords,
				sEcho, users);
		
		DataTableWrapper tableWrapper = new DataTableWrapper();
		DataTable table = new DataTable(Student.class);
		
		//table.bServerSide = true;
		table.bDestroy = true;
		table.sPaginationType = "full_numbers";
		table.fnServerData = new FnCallBack("fnDataTablesPipeline");
		
		tableWrapper.setDataHandler(StudentDataHandler.class);
		tableWrapper.setDataTable(table);
		
		
		JSONObject json = JSONObject.fromObject(dr);
		// DataTableUtil.getJson(dr, "id,username");
		return json.toString();
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
	public MessageVo delete(@RequestParam List<String> ids) {
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
			message = new MessageVo(true, "删除成功：" + success + "/" + ids.size()
					+ "条");
		} catch (Exception e) {
			message = new MessageVo(false, "刪除失败");
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

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		List<User> users = this.userService.findAll();
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "用户信息";
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
			ExcelTitleVo title = new ExcelTitleVo("用户列表", "导出人:"
					+ (String) map.get("userName"), "导出用户信息");
			workbook = ExcelExportUtil.exportExcel(title, User.class, users);
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
	 * 导出excel 用户模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcelTemplate", method = RequestMethod.GET)
	public void exportExcelTemplate(User User, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		response.setContentType("application/vnd.ms-excel");
		String fileName = null;
		OutputStream out = null;
		try {
			fileName = "用户信息模板";
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
			ExcelTitleVo title = new ExcelTitleVo("用户信息模板", "导出人:"
					+ (String) map.get("userName"), "导出信息");
			workbook = ExcelExportUtil.exportExcel(title, User.class, null);
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
	 * 导入用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ModelAndView importExcel(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("redirect:/user/enterList.do");
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
				List<User> listUsers = (List<User>) ExcelImportUtil
						.importExcelByIs(file.getInputStream(), User.class,
								params);
				for (User user : listUsers) {
					if (user.getUsername() != null) {
						userService.save(user);
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
