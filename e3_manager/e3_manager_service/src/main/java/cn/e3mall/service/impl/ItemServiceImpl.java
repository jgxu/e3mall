package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.DatagridResult;
import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import cn.e3mall.utils.IDUtils;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TbItem findById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}
	
	
	@Override
	public DatagridResult pageQuery(int page, int rows) {
		//pageHelper拦截器会拦截查询语句
		PageHelper.startPage(page,rows);
		
		//此时的list不是arraylist, 而是page,page继承了arraylist, 
		//如果直接添加到datagridresult中,会出现page找不到的警告, 可以换成arraylist
		List<TbItem> list = itemMapper.selectByExample(new TbItemExample());
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("下一页:"+pageInfo.getNextPage());
		System.out.println("当前页:"+pageInfo.getPageNum());
		System.out.println("总记录:"+pageInfo.getTotal());
		System.out.println("总页数:"+pageInfo.getPages());
		System.out.println("分页查询的数据:"+list.size());
		DatagridResult datagridResult = new DatagridResult();
		ArrayList<TbItem> list2 = new ArrayList<TbItem>();
		for (TbItem tbItem : list) {
			list2.add(tbItem);
		}
		datagridResult.setRows(list2);
		datagridResult.setTotal(pageInfo.getTotal());
		return datagridResult;
	}


	@Override
	public E3Result addItem(TbItem item, String desc) {
		item.setId(IDUtils.genItemId());
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc );
		return E3Result.ok();
	}


	@Override
	public E3Result updateItem(TbItem item, String desc) {
		item.setStatus((byte) 1);
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKey(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKey(itemDesc);
		return E3Result.ok();
	}
	
}
