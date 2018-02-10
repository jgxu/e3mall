package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.TreeNode;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeNode> getContentCatgory(@RequestParam(name="id",defaultValue="0")long parentId){
		List<TreeNode> treeNodes = contentCategoryService.getContentCategoryList(parentId);
		return treeNodes;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3Result addContentCategory(long parentId,String name){
		E3Result result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
}
