package zgl.shop.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import zgl.shop.service.CategoryService;
import zgl.shop.service.ProductService;
import zgl.shop.vo.Category;
import zgl.shop.vo.Product;

/**
 * 首页访问的Action
 * @author zgl
 *
 */
public class IndexAction extends ActionSupport{
	//一级分类的Service
	private CategoryService categoryService;
	//注入商品的Service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 *  执行的访问首页的方法:
	 */
	public String execute(){
		//查询所有一级分类集合
		List<Category> cList=categoryService.findAll();
		//存入Session的范围
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> hList=productService.findHot();
		//保存到值栈中
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
