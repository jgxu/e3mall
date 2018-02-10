package cn.e3mall.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.pojo.DatagridResult;
import cn.e3mall.pojo.E3Result;
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
	
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItem(TbItem item, String desc){
		E3Result result = itemService.addItem(item, desc);
		return result;
	}
	@RequestMapping("/item/update")
	@ResponseBody
	public E3Result updateItem(TbItem item, String desc){
		E3Result result = itemService.updateItem(item, desc);
		return result;
	}
}
