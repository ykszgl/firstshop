package zgl.shop.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.OrderService;
import zgl.shop.utils.PageBean;

import zgl.shop.vo.Cart;
import zgl.shop.vo.CartItem;
import zgl.shop.vo.Order;
import zgl.shop.vo.OrderItem;
import zgl.shop.vo.User;

/**
 * ��������Action
 * @author zgl
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	// ģ������ʹ�õĶ���
	private Order order = new Order();

	public Order getModel() {
		return order;
	}


	// ���ո���ɹ���Ĳ���:
	private String r3_Amt;
	private String r6_Order;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	// ����page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// ע��OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// ���ɶ�����ִ�еķ���:
	public String saveOrder() {

		// ����Service������ݿ����Ĳ���:
		// ���ö������ܽ��:�������ܽ��Ӧ���ǹ��ﳵ���ܽ��:
		// ���ﳵ��session��,��session�ܻ�ù��ﳵ��Ϣ.
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("��!����û�й���!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// ���ö�����״̬
		order.setState(1); // 1:δ����.
		// ���ö���ʱ��
		order.setOrdertime(new Date());
		// ���ö��������Ŀͻ�:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("��!����û�е�¼!");
			return "msg";
		}
		order.setUser(existUser);
		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
		cart.clearCart();

		return "saveOrder";
	}

	// ��ѯ�ҵĶ���:
	public String findByUid() {
		// ����û���id.
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// ����û���id
		Integer uid = existUser.getUid();
		// �����û���id��ѯ����:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// ��PageBean���ݴ���ҳ����.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	// ���ݶ���id��ѯ����:
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}

	// Ϊ��������:
	public String payOrder() throws IOException {
		// 1.�޸�����:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		currOrder.setState(2);
		this.addActionMessage("֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		// �޸Ķ���
		orderService.update(currOrder);
		return "paySuccess";
	}

	public String updateState(){
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
}
