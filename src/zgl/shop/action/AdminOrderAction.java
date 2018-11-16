package zgl.shop.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.OrderService;
import zgl.shop.utils.PageBean;
import zgl.shop.vo.Order;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private Order order=new Order();
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public String findAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String updateState(){
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
