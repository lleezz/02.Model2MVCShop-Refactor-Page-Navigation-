package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
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
		
		int currentPage=1;

		if(request.getParameter("currentPage") != null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		search.setCurrentPage(currentPage);
		
		
		// web.xml  meta-data 로 부터 상수 추출 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));		//3
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));	//5
		search.setPageSize(pageSize);
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		Map<String, Object> map = purchaseService.getPurchaseList(search, buyerId);

		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);	
		System.out.println("ListUserAction ::"+resultPage);	//( 현재페이지, 전체 건수, 5, 3 )
		
		request.setAttribute("map", map);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}
