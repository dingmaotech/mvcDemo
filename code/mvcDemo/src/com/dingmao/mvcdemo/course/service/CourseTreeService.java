package com.dingmao.mvcdemo.course.service;

import java.util.List;

import com.dingmao.mvcdemo.course.model.CourseTree;
import com.dingmao.platform.service.BaseService;

/**
 * 功能:课程树业务层接口.
 * <p>
 * 修改历史:对程序的修改历史进行记录
 * </p>
 */
public interface CourseTreeService extends BaseService<CourseTree, Integer> {
	/**
	 * 根据获取下-级科目子节点
	 * 
	 * @param pId
	 * @return
	 */
	public List<CourseTree> findChildCourseTreeByPId(Integer pId);

	/**
	 * 根据获取全部下级科目子节点
	 * 
	 * @param pId
	 * @return
	 */
	public List<CourseTree> findAllChildCourseTreeByPId(Integer pId);
}
