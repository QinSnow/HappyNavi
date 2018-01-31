package demo;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class HelloController extends Controller {
	public void index() {
    
		//List<Record> list=Db.find("SELECT * FROM tb_admin");
		User user=User.dao.findById(1015);
    	
        renderText(user.toString());
    }
}
