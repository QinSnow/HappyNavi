package com.happynavi.comm;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;

import demo.User;

/**
 * 该类用于对整个Web项目进行配置，需要实现六个抽象方法
 * 
 * @author qin
 *
 */
public class ProjectConfig extends JFinalConfig {

	/**
	 * 此方法用来配置JFinal常量值
	 */
	@Override
	public void configConstant(Constants me) {
		PropKit.use("a_little_config.txt");// 配置数据库链接
		me.setDevMode(PropKit.getBoolean("devMode", true));// 设置为开发模式，会对每次请求输出报告

	}

	/**
	 * 此方法用来配置访问路由
	 */
	@Override
	public void configRoute(Routes me) {
		TestRoutes ar = new TestRoutes();
		me.add(ar);

	}

	/**
	 * 此方法用来配置Template Engine，向模板引擎中添加模板文件
	 */
	@Override
	public void configEngine(Engine me) {

	}

	/**
	 * 此方法用来配置项目的插件，可以方便的创建插件并应用到项目中去
	 */
	@Override
	public void configPlugin(Plugins me) {

		// c3p0连接池
		C3p0Plugin cPlugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(),
				PropKit.get("driverClassName"));
		me.add(cPlugin);

		// 映射数据模型
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cPlugin);
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		arp.setDialect(new AnsiSqlDialect());

		arp.addMapping("tb_user", "UserID", User.class);

		me.add(arp);

	}

	/**
	 * 此方法用来配置项目的全局拦截器，拦截所有action请求，除非使用@Clear在controller中清除
	 */
	@Override
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 此方法用来配置项目的处理器
	 */
	@Override
	public void configHandler(Handlers me) {

	}

}
