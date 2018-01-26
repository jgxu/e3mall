package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.DatagridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{id}")
	@ResponseBody
	public TbItem findById(@PathVariable()long id){
		TbItem item = itemService.findById(id);
		return item;
	}
	//首页
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage( @PathVariable String page){
		return page;
	}
	
	//商品列表
	@RequestMapping("/item/list")
	@ResponseBody
	public DatagridResult itemList(int page, int rows){
		DatagridResult datagridResult = itemService.pageQuery(page,rows);
		return datagridResult;
	}
	
}
