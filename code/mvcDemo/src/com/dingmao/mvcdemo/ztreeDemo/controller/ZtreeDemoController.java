package com.dingmao.mvcdemo.ztreeDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dingmao.mvcdemo.course.model.CourseTree;
import com.dingmao.mvcdemo.course.service.CourseTreeService;
import com.dingmao.platform.controller.BaseController;
import com.dingmao.platform.util.json.JsonUtils;
import com.dingmao.platform.vo.MessageVo;

/**
 * ztreeDemo操作请求处理类
 * 
 * @author think
 * 
 */
@Controller
@RequestMapping("/ztreeDemo")
public class ZtreeDemoController  extends BaseController<CourseTree> {
	private Log logger = LogFactory.getLog(ZtreeDemoController.class);

	/**
	 * 课程业务类注入
	 */
	@Autowired
	private CourseTreeService courseTreeService;

	/**
	 * 消息
	 */
	private MessageVo message;

	/**
	 * 功能说明:进入ztree index页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/index.jsp
	 * @exception
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "ztreeDemo/index";
	}

	/**
	 * 功能说明:进入最简单的树 -- 标准 JSON 数据页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/standardData.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/standardData", method = RequestMethod.GET)
	public String standardData(Model model) {
		return "ztreeDemo/core/standardData";
	}

	/**
	 * 功能说明:进入最简单的树 -- 简单 JSON 数据页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/simpleData.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/simpleData", method = RequestMethod.GET)
	public String simpleData(Model model) {
		return "ztreeDemo/core/simpleData";
	}

	/**
	 * 功能说明:进入不显示连接线的树页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/noline.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/noline", method = RequestMethod.GET)
	public String noline(Model model) {
		return "ztreeDemo/core/noline";
	}

	/**
	 * 功能说明:进入不显示节点图标的树页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/noicon.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/noicon", method = RequestMethod.GET)
	public String noicon(Model model) {
		return "ztreeDemo/core/noicon";
	}
	
	/**
	 * 功能说明:进入异步加载节点数据的树页.
	 * 异步加载全部节点
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/async.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/async", method = RequestMethod.GET)
	public String async(Model model) {
		return "ztreeDemo/core/async";
	}
	
	/**
	 * 功能说明:进入异步加载节点数据的树页.
	 * 点击后异步加载下级节点
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/asyncChild.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/asyncChild", method = RequestMethod.GET)
	public String asyncChild(Model model) {
		return "ztreeDemo/core/asyncChild";
	}
	
	
	/**
	 * 功能说明:进入单击节点控制页.
	 * 点击后异步加载下级节点
	 * 
	 * @return WEB-INF/pages/ztreeDemo/core/click.jsp
	 * @exception
	 */
	@RequestMapping(value = "/core/click", method = RequestMethod.GET)
	public String click(Model model) {
		return "ztreeDemo/core/click";
	}
	
	/**
	 * 功能说明:进入Checkbox 勾选操作页.
	 * 
	 * @return WEB-INF/pages/ztreeDemo/excheck/checkbox.jsp
	 * @exception
	 */
	@RequestMapping(value = "/excheck/checkbox", method = RequestMethod.GET)
	public String checkbox(Model model) {
		return "ztreeDemo/excheck/checkbox";
	}
	
	
	
	/**
	 * 功能说明:进入基本 增 / 删 / 改 节点页.
	 * 点击后异步加载下级节点
	 * 
	 * @return WEB-INF/pages/ztreeDemo/exedit/edit.jsp
	 * @exception
	 */
	@RequestMapping(value = "/exedit/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		return "ztreeDemo/exedit/edit";
	}
	
	/**
	 * 功能说明:AJAX请求获取ztree数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/asyncData/getNodeById", method = RequestMethod.GET)
	@ResponseBody
	public CourseTree getNodeById(@RequestParam String id,HttpServletRequest request,
			HttpServletResponse response) {
		 CourseTree node = courseTreeService.getById(Integer.parseInt(id));
		return node;
	}
	
	/**
	 * 功能说明:AJAX请求获取ztree数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/asyncData/getChildNodes", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getChildNodes(@RequestParam String id,HttpServletRequest request,
			HttpServletResponse response) {
		if("".equals(id)){
			id="8";
		}
		 List<CourseTree> nodes = this.courseTreeService.findChildCourseTreeByPId(Integer.parseInt(id));
		 CourseTree tree = null;
		 List<CourseTree> subnodes = null;
		 for(int i = 0;i<nodes.size();i++){
			 tree = nodes.get(i);
			 subnodes = this.courseTreeService.findChildCourseTreeByPId(tree.getId());
			 if(subnodes != null &&subnodes.size() > 0 ){
				 tree.setIsParent(true);
			 }else{
				 tree.setIsParent(false);
			 }
			 nodes.set(i, tree);
		 }
			String jsonStr = JsonUtils.listToJson(nodes, new String[] { "no",
					"type", "bz", "eduId", "imgSrc", "rootId", "url" ,"children"});
		 JSONArray json = JSONArray.fromObject(jsonStr);
		return json;
	}
	
	/**
	 * 功能说明:AJAX请求获取ztree数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/asyncData/getAllNodes", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllNodes(@RequestParam String id,HttpServletRequest request,
			HttpServletResponse response) {
		if("".equals(id)){
			id="8";
		}
		List<CourseTree> nodes = courseTreeService.findAllChildCourseTreeByPId(Integer.parseInt(id));
		JSONArray json = JSONArray.fromObject(nodes);
		System.out.println(nodes.size() + "\n" + json.toString());
		return json;
	}

	/**
	 * 功能说明:AJAX请求获取ztree数据
	 * 
	 * @return json
	 * @exception
	 */
	@RequestMapping(value = "/asyncData/getNodes", method = RequestMethod.GET)
	@ResponseBody
	public String getNodes(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding(ENCODING);
		response.setContentType("application/x-javascript;charset=UTF-8");
		List<CourseTree> nodes = courseTreeService.findAll();
		String jsonStr = JsonUtils.listToJson(nodes, new String[] { "no",
				"type", "bz", "eduId", "imgSrc", "rootId", "url" });
		System.out.println(nodes.size() + "\n" + jsonStr);
		return jsonStr;
	}

	
	
}
