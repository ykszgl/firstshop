package zgl.shop.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.CategorySecondService;
import zgl.shop.service.CategoryService;
import zgl.shop.utils.PageBean;
import zgl.shop.vo.Category;
import zgl.shop.vo.CategorySecond;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	// 模型驱动使用的对象
		private CategorySecond categorySecond = new CategorySecond();
		// 接收page参数:
		private Integer page;
		// 注入二级Service
		private CategorySecondService categorySecondService;
		// 注入一级分类的Service
		private CategoryService categoryService;

		public void setPage(Integer page) {
			this.page = page;
		}

		public void setCategorySecondService(
				CategorySecondService categorySecondService) {
			this.categorySecondService = categorySecondService;
		}

		public void setCategoryService(CategoryService categoryService) {
			this.categoryService = categoryService;
		}

		public CategorySecond getModel() {
			return categorySecond;
		}


	public String findAll() {
		// 调用Service进行查询.
				PageBean<CategorySecond> pageBean = categorySecondService
						.findByPage(page);
				// 将pageBean的数据存入到值栈中.
				ActionContext.getContext().getValueStack().set("pageBean", pageBean);
				return "findAll";
	}
	public String addPage(){
		//查询一级分类
	List<Category> cList=categoryService.findAll();
	//把数据存入值栈显示到页面的下拉列表中
	ActionContext.getContext().getValueStack().set("cList", cList);
	return "addPageSuccess";
	}
	public String save(){
		categorySecondService.save(categorySecond);
		return "savaSuccess";
	}
	public String delete(){
		
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//编辑二级分类
	public String edit(){
		//查询二级分类对象
		categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//修改二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
