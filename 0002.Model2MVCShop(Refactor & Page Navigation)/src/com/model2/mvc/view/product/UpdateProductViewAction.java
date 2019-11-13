package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductViewAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**/System.out.println("*** UpdateProductViewAction.java  start....");
		
		int prodNo = Integer.parseInt( request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(prodNo);

		request.setAttribute("product", product);
		
		/**/System.out.println("*** ProductVO < service.getProduct(prodNo) --> req.setAtt(productVO)");
		/**/System.out.println("*** UpdateProductViewAction.java  end....>>");
		return "forward:/product/updateProduct.jsp";	
	}
}
