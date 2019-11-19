package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		Product product = new Product();
		Purchase purchase = new Purchase();
//		
//		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
//		HttpSession session = request.getSession();
//		User user =  (User)session.getAttribute("user");
//		
//		purchase.setBuyer(user);
//		purchase.setPurchaseProd(product);
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		purchase.setTranNo(tranNo);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("dlvyAddr"));
		purchase.setDivyRequest(request.getParameter("dlvyRequest"));
		purchase.setDivyDate(request.getParameter("dlvyDate"));
//		purchase.setTranCode("1");
		
		PurchaseService purchaseSerivce = new PurchaseServiceImpl();
		purchaseSerivce.updatePurcahse(purchase);
		
		request.setAttribute("purchase", purchase);
		
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}

}
