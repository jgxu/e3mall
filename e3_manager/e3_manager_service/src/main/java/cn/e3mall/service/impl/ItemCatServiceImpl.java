package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TreeNode;
import cn.e3mall.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		//根据parentId查询商品类别
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<TreeNode> treeNodes = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode treeNode = new TreeNode();
			//设置treeNode的id, 为当前类别的id
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			//如果没有子节点, 就是close,有子节点就是open
			Boolean flag = tbItemCat.getIsParent();
			if(flag){
				treeNode.setState("closed");
			}else{
				treeNode.setState("open");
				
			}
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

}
