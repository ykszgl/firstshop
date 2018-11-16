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
	// ģ������ʹ�õĶ���
		private CategorySecond categorySecond = new CategorySecond();
		// ����page����:
		private Integer page;
		// ע�����Service
		private CategorySecondService categorySecondService;
		// ע��һ�������Service
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
		// ����Service���в�ѯ.
				PageBean<CategorySecond> pageBean = categorySecondService
						.findByPage(page);
				// ��pageBean�����ݴ��뵽ֵջ��.
				ActionContext.getContext().getValueStack().set("pageBean", pageBean);
				return "findAll";
	}
	public String addPage(){
		//��ѯһ������
	List<Category> cList=categoryService.findAll();
	//�����ݴ���ֵջ��ʾ��ҳ��������б���
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
	//�༭��������
	public String edit(){
		//��ѯ�����������
		categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//�޸Ķ�������
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
