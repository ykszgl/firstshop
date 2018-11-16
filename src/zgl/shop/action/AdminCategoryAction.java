package zgl.shop.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.CategoryService;
import zgl.shop.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category=new Category();
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String findAll(){
		//��ѯ����һ������
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
		
	}
	
	public String save(){
	 categoryService.save(category);
	 return "saveSuccess";
	}
	public String delete(){
		//����cid
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";	
	}
	public String edit(){
		//��ѯһ������
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	public String update(){
		//��ѯһ������
		categoryService.update(category);
		return "updateSuccess";
	}
}
