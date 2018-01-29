package cn.e3mall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.utils.FastDFSClient;
import cn.e3mall.utils.JsonUtils;

@Controller
public class PictureController {
	
	@Value("${image.server.url}")
	private String host;
	@RequestMapping(value="/pic/upload",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String Upload(MultipartFile uploadFile){
		try {
			String oname = uploadFile.getOriginalFilename();
			String extName = oname.substring(oname.lastIndexOf(".")+1);
			FastDFSClient client = new FastDFSClient("classpath:config/client.conf");
			String surl = client.uploadFile(uploadFile.getBytes(), extName, null);
			String url = host+surl;
			Map map = new HashMap<>();
			map.put("error", 0);
			map.put("url", url);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			Map map = new HashMap<>();
			map.put("error", 1);
			map.put("message", "上传文件失败");
			e.printStackTrace();
			return JsonUtils.objectToJson(map);
		} 
		
	}
}
