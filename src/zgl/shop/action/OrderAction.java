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
 * 订单管理Action
 * @author zgl
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	// 模型驱动使用的对象
	private Order order = new Order();

	public Order getModel() {
		return order;
	}


	// 接收付款成功后的参数:
	private String r3_Amt;
	private String r6_Order;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	// 接收page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 注入OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	// 生成订单的执行的方法:
	public String saveOrder() {

		// 调用Service完成数据库插入的操作:
		// 设置订单的总金额:订单的总金额应该是购物车中总金额:
		// 购物车在session中,从session总获得购物车信息.
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("亲!您还没有购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		order.setState(1); // 1:未付款.
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!您还没有登录!");
			return "msg";
		}
		order.setUser(existUser);
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// 清空购物车:
		cart.clearCart();

		return "saveOrder";
	}

	// 查询我的订单:
	public String findByUid() {
		// 获得用户的id.
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		// 获得用户的id
		Integer uid = existUser.getUid();
		// 根据用户的id查询订单:
		PageBean<Order> pageBean = orderService.findByUid(uid, page);
		// 将PageBean数据带到页面上.
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}

	// 根据订单id查询订单:
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}

	// 为订单付款:
	public String payOrder() throws IOException {
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		currOrder.setState(2);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		// 修改订单
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
