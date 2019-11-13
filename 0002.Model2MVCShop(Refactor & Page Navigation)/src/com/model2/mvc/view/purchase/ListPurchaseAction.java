package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		String buyerId = user.getUserId();
		
		Search search = new Search();
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map = purchaseService.getPurchaseList(search, buyerId);
		
		request.setAttribute("map", map);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
