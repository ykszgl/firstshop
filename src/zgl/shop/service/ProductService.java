package zgl.shop.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import zgl.shop.dao.ProductDao;
import zgl.shop.utils.PageBean;
import zgl.shop.vo.Product;
/**
 * 商品业务层
 * @author zgl
 *
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit=12;
		pageBean.setLimit(limit);
		int totaCount=0;
		totaCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totaCount);
		int totalpage=0;
		if(totaCount % limit==0){
			totalpage = totaCount/limit;
		}else {
			totalpage=totaCount/limit+1;
		}
		//每页显示的数据集合
		pageBean.setTotalPage(totalpage);
		int begin=(page-1)*limit;
		List<Product> list= productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit=12;
		pageBean.setLimit(limit);
		int totaCount=0;
		totaCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totaCount);
		int totalpage=0;
		if(totaCount % limit==0){
			totalpage = totaCount/limit;
		}else {
			totalpage=totaCount/limit+1;
		}
		//每页显示的数据集合
		pageBean.setTotalPage(totalpage);
		int begin=(page-1)*limit;
		List<Product> list= productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		int totaCount=0;
		totaCount=productDao.findCount();
		pageBean.setTotalCount(totaCount);
		int totalpage=0;
		if(totaCount % limit==0){
			totalpage = totaCount/limit;
		}else {
			totalpage=totaCount/limit+1;
		}
		//每页显示的数据集合
		pageBean.setTotalPage(totalpage);
		int begin=(page-1)*limit;
		List<Product> list= productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
	
	
}
