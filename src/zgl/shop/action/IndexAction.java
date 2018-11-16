package zgl.shop.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zgl.shop.service.CategoryService;
import zgl.shop.service.ProductService;
import zgl.shop.vo.Category;
import zgl.shop.vo.Product;

/**
 * ��ҳ���ʵ�Action
 * @author zgl
 *
 */
public class IndexAction extends ActionSupport{
	//һ�������Service
	private CategoryService categoryService;
	//ע����Ʒ��Service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 *  ִ�еķ�����ҳ�ķ���:
	 */
	public String execute(){
		//��ѯ����һ�����༯��
		List<Category> cList=categoryService.findAll();
		//����Session�ķ�Χ
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> hList=productService.findHot();
		//���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
