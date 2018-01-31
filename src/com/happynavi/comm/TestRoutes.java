package com.happynavi.comm;

import com.jfinal.config.Routes;

import demo.HelloController;

public class TestRoutes extends Routes {

	@Override
	public void config() {
		// TODO Auto-generated method stub
		add("/hello", HelloController.class);
	}

}
