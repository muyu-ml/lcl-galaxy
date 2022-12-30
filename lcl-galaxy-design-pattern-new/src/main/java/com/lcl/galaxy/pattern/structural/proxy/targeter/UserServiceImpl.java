package com.lcl.galaxy.pattern.structural.proxy.targeter;

public class UserServiceImpl implements UserService {

	@Override
	public void saveUser() {
		System.out.println("添加用户");
	}

}
