package zgl.shop.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * 一级分类实体
 * @author zgl
 *
 */
public class Category {
	private Integer cid;
	private String cname;
	public Category() {
		super();
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	//存放二级分类集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
}
