package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.DatagridResult;
import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public DatagridResult getContentList(long categoryId, int page, int rows){
		DatagridResult result = contentService.getContentList(page, rows, categoryId);
		return result;
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public E3Result addContent(TbContent content){
		contentService.addContent(content);
		return E3Result.ok();
	}
}
