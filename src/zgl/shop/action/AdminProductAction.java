package zgl.shop.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.CategorySecondService;
import zgl.shop.service.ProductService;
import zgl.shop.utils.PageBean;
import zgl.shop.vo.CategorySecond;
import zgl.shop.vo.Product;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	// 文件上传需要的三个属性:
		private File upload;
		private String uploadFileName;
		private String uploadContentType;

		public void setUpload(File upload) {
			this.upload = upload;
		}

		public void setUploadFileName(String uploadFileName) {
			this.uploadFileName = uploadFileName;
		}

		public void setUploadContentType(String uploadContentType) {
			this.uploadContentType = uploadContentType;
		}
	//带分页的查询商品的执行方法
	public String findAll(){
		PageBean<Product>pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//跳转到添加页面
	public String addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
		
	}
	//保存商品的方法
	public String save() throws IOException{
		product.setPdate(new Date());
		if(upload!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	public String delete(){
		product=productService.findByPid(product.getPid());
		String path=product.getImage();
		if(path!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/"+path);
			File file=new File(realPath);
			file.delete();
		}
		productService.delete(product);
		return "deleteSuccess";
	}
	//跳转到编辑页面
	public String edit(){
		product=productService.findByPid(product.getPid());
		List<CategorySecond>csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	public String update() throws IOException{
		product.setPdate(new Date());
		if(upload!=null){
			String path=product.getImage();
			File file=new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile=new File(realPath+"//"+uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
		
	}
}
