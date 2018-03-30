
package com.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cache.dao.EmpMapper;
import com.cache.entity.Emp;

/**  
* 创建时间：2018年3月29日 下午11:57:57  

* 项目名称：cache-redis  

* @author zhourunbin  

* @version 1.0   

* Description： 
*/
@Controller
public class EmpController {
	
	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private CacheManager cachemManager;
	
	@RequestMapping(value="/empList")
	@ResponseBody
	public List<Emp> empList(){
		List<Emp> emList = empMapper.selectByExample(null);
	 
		return emList;
	}
	
}
 