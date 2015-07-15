package com.dingmao.mvcdemo.test.course;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.dingmao.mvcdemo.course.model.CourseTree;
import com.dingmao.mvcdemo.course.service.CourseTreeService;
import com.dingmao.platform.util.json.JsonUtils;

@ContextConfiguration("classpath:/spring/*.xml")
public class CourseTreeTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private CourseTreeService treeService;

	@Test
	public void testinitTree() {
		List<CourseTree> nodes = treeService.findAll();
		System.out.println(nodes.size());
	}

	@Test
	public void testFindChildCourseTreeByPId() {
		List<CourseTree> nodes = treeService.findChildCourseTreeByPId(8);
		String jsonStr = JsonUtils.listToJson(nodes, new String[] { "no",
				"type", "bz", "eduId", "imgSrc", "rootId", "url" });
		System.out.println(nodes.size() + "\n" + jsonStr);
	}

	@Test
	public void testFindAllChildCourseTreeByPId() {
		List<CourseTree> nodes = treeService.findAllChildCourseTreeByPId(8);
		String jsonStr = JsonUtils.listToJson(nodes, new String[] { "no",
				"type", "bz", "eduId", "imgSrc", "rootId", "url" });
		System.out.println(nodes.size() + "\n" + jsonStr);
	}

}
