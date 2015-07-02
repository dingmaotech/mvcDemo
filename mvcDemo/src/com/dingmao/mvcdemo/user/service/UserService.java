package com.dingmao.mvcdemo.user.service;

import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.platform.service.BaseService;

public interface UserService   extends BaseService<User, String> {

	public  User getUserByUsername(String name);

	public  void pwdInit(User user, String password);

	public  User checkUserPwd(User user);
}
