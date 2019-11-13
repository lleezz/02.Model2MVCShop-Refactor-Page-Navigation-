package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;



public class AddProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**/System.out.println("*** AddProductAction.java  start....");
		
		Product product = new Product();
		
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		
//		String[] manuDate2 = request.getParameter("manuDate").split("-");
//		product.setManuDate(manuDate2[0]+manuDate2[1]+manuDate2[2]);
		
		//System.out.println(productVO);
		
		ProductService service = new ProductServiceImpl();
		product = service.addProduct(product);
		
		//System.out.println(productVO);
		request.setAttribute("vo", product);
		
		/**/System.out.println("*** ProductVO service.addProduct(productVO)");
		/**/System.out.println("*** AddProductAction.java  end....>>");
		return "forward:/product/addProduct.jsp";		//////redirect....****
	}

}
