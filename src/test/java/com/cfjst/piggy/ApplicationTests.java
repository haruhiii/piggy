package com.cfjst.piggy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.cfjst.piggy.bean.BigTask;
import com.cfjst.piggy.bean.SmallTask;
import com.cfjst.piggy.bean.Student;
import com.cfjst.piggy.service.BigTaskService;
import com.cfjst.piggy.service.SmallTaskService;
import com.cfjst.piggy.service.StudentService;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 入口测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {

		
		Date data = new Date();
	}

/**
 * Description:
 * <p>计算项目代码行数</p>
 * Create by hry
 */

private String [] ignore = {
		"ees-migration",
		//"ees-proxy",
		"ees-task",
};

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void foo(){

		String path = "C:\\Users\\Power\\Desktop\\piggy\\src\\main\\resources";

		File srcfolder = new File(path);

		if(!srcfolder.exists()){
			return;
		}

		HashMap<String, File> objectObjectHashMap = new HashMap<>();
		Map<String, File> map = showAllFiles(srcfolder, objectObjectHashMap);
		assert map != null;
		logger.info("共有文件{}个",map.size());

		int num = 0;

		for(File file : map.values()){
			//int line = getLine(file, "//", "/*", "*/", "*");
			int line = getLine(file);
			num += line;
		}

		logger.info("总行数:{}",num);
	}

	/**
	 * 获取文件夹下所有文件
	 * @param file      必须为一个文件夹
	 * @param data      初始化为分配空间但空间内无数据的map
	 */
	private Map<String,File> showAllFiles(File file, Map<String, File> data){

		File[] files = file.listFiles();
		if(files == null || files.length == 0){
			return null;
		}

		for (File f : files) {
			if (f.isDirectory()) {
				showAllFiles(f,data);
			} else {
				data.put(f.getPath(),f);
			}
		}

		return data;
	}

	/**
	 * 计算文件有效行数
	 * 匹配指定格式文件（java），设置匹配规则Patton
	 * 计算行数
	 * patton: 文件行内包含该字符的会被忽略
	 * file  : 具体文件
	 */
	private int getLine(File file,String...patton){

		if(!file.getName().endsWith(".css")){
			return 0;
		}

		int temp = 0;
		try {
			String line;
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			while ((line = fileReader.readLine()) != null){
				if(line.trim().length() == 0){
					continue;
				}

				boolean flag = true;

				/**
				 * 计算行数
				 * 每行内存在patton的字符则过滤，不予计算
				 */
				if(patton != null && patton.length > 0){
					List<String> strings = Arrays.asList(patton);
					for(String p : strings){
						if(line.contains(p)){
							flag = false;
							break;
						}
					}
				}

				if(flag){
					temp ++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}

}
