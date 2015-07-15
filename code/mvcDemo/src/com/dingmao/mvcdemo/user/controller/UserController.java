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
import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.mvcdemo.user.model.UserDataHandler;
import com.dingmao.mvcdemo.user.service.UserService;
import com.dingmao.platform.model.page.Page;
import com.dingmao.platform.poi.excel.util.ExcelExportUtil;
import com.dingmao.platform.poi.excel.util.ExcelImportUtil;
import com.dingmao.platform.poi.excel.vo.ExcelTitleVo;
import com.dingmao.platform.poi.excel.vo.ImportParam;
import com.dingmao.platform.util.BrowserUtil;
import com.dingmao.platform.util.PropertiesUtil;
import com.dingmao.platform.vo.MessageVo;
import com.dingmao.platform.vo.datatables.wrapper.DataTableWrapper;
import com.dingmao.platform.vo.datatables.wrapper.IDataHandler;
import com.dingmao.platform.vo.datatables.wrapper.data.DataTableRequest;

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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * 功能说明:AJAX请求获取用户数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		DataTableRequest dataGrid = this.parseAjaxRequest(request);
/*		String sEcho = dataGrid.getsEcho();
		// 开始记录
		int iDisplayStart = dataGrid.getiDisplayStart();

		// 单页显示记录数
		int iTotalRecords = dataGrid.getiDisplayLength()==0?10:dataGrid.getiDisplayLength();

		Page<User> page = new Page<User>(iTotalRecords,iDisplayStart);


		String hql = "from  User where 1=1 ";*/


		/*Page<User> userpage = this.userService.findPage(page, hql,
				new Object[] {});
		
		List<User> users = userpage.getResult();*/
		//iTotalRecords = users.size();
		/**
		 * 过滤后总记录数
		 */
		//Integer iTotalDisplayRecords = (int) userpage.getTotalRows();

		List<User> users = this.userService.findAll();
		IDataHandler<User>  userHandler = new UserDataHandler();
		DataTableWrapper wrapper = new DataTableWrapper();
		String  jsonStr = wrapper.wrap(request,userHandler,users, request.getRequestURL().toString()).toString();
		
		JSONObject json = JSONObject.fromObject(jsonStr);
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
			HttpServletResponse response,  ModelMap map) {
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
	
	public DataTableRequest parseAjaxRequest(HttpServletRequest request) {
		DataTableRequest ajaxRequest = new DataTableRequest();
		if (request.getParameter("sEcho") != null
				&& request.getParameter("sEcho") != "") {
			ajaxRequest.sEcho = request.getParameter("sEcho");
			ajaxRequest.sSearchKeyword = request.getParameter("sSearch");
			ajaxRequest.bRegexKeyword = Boolean.parseBoolean(request
					.getParameter("bRegex"));
			ajaxRequest.sColumns = request.getParameter("sColumns");
			ajaxRequest.iDisplayStart = Integer.parseInt(request
					.getParameter("iDisplayStart"));
			ajaxRequest.iDisplayLength = Integer.parseInt(request
					.getParameter("iDisplayLength"));
			ajaxRequest.iColumns = Integer.parseInt(request
					.getParameter("iColumns"));
			ajaxRequest.sSearch = new String[ajaxRequest.iColumns];
			ajaxRequest.bSearchable = new boolean[ajaxRequest.iColumns];
			ajaxRequest.bSortable = new boolean[ajaxRequest.iColumns];
			ajaxRequest.bRegex = new boolean[ajaxRequest.iColumns];
			for (int i = 0; i < ajaxRequest.iColumns; i++) {
				ajaxRequest.sSearch[i] = request
						.getParameter((new StringBuilder("sSearch_")).append(i)
								.toString());
				ajaxRequest.bSearchable[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bSearchable_"))
								.append(i).toString()));
				ajaxRequest.bSortable[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bSortable_")).append(
								i).toString()));
				ajaxRequest.bRegex[i] = Boolean.parseBoolean(request
						.getParameter((new StringBuilder("bRegex_")).append(i)
								.toString()));
			}

			if (request.getParameter("iSortingCols") != null)
				ajaxRequest.iSortingCols = Integer.parseInt(request
						.getParameter("iSortingCols"));
			ajaxRequest.sSortDir = new String[ajaxRequest.iSortingCols];
			ajaxRequest.iSortCol = new int[ajaxRequest.iSortingCols];
			for (int i = 0; i < ajaxRequest.iSortingCols; i++) {
				ajaxRequest.sSortDir[i] = request
						.getParameter((new StringBuilder("sSortDir_"))
								.append(i).toString());
				ajaxRequest.iSortCol[i] = Integer.parseInt(request
						.getParameter((new StringBuilder("iSortCol_"))
								.append(i).toString()));
			}

		}
		return ajaxRequest;
	}
	
}
