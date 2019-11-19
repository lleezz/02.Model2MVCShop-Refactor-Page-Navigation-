package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class ListSaleAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		/**/System.out.println("*** ListSaleAction.java  start....");
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));	//3
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		System.out.println("\t\t\t 페이지 사이즈 확인: " + pageSize);
		
		/**/System.out.println(search);
		
		PurchaseService service = new PurchaseServiceImpl();
		Map<String, Object> map = service.getSaleList(search);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("map", map);
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		
		/**/System.out.println("*** ListSaleAction.java  end....>>");
		
		return "forward:/purchase/listSale.jsp";
	}
}