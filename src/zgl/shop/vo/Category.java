package zgl.shop.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * һ������ʵ��
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
	//��Ŷ������༯��
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
}
