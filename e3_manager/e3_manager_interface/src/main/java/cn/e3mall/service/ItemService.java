package cn.e3mall.service;

import cn.e3mall.pojo.DatagridResult;
import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.TbItem;

public interface ItemService {
	public TbItem findById(Long itemId);

	public DatagridResult pageQuery(int page, int rows);
	
	public E3Result addItem(TbItem item,String desc);
	
	public E3Result updateItem(TbItem item,String desc);
}
