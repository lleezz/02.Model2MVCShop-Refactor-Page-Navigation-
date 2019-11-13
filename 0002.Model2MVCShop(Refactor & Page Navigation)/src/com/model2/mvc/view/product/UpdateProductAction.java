package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**/System.out.println("*** UpdateProductAction.java  start....");
		
		int prodNo = Integer.parseInt( request.getParameter("prodNo"));
		
		/**/System.out.println("prodNo = "+prodNo+" / prodName = "+request.getParameter("prodName")+" ¼öÁ¤");
		
		Product product = new Product();
		product.setProdNo(prodNo);
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		if( request.getParameter("fileNameNew")==null || request.getParameter("fileNameNew").length()<1) {
			product.setFileName(request.getParameter("fileName"));
			System.out.println(request.getParameter("fileName"));
		}else {
			product.setFileName(request.getParameter("fileNameNew"));
			System.out.println(request.getParameter("fileNameNew"));
		}
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);

		/**/System.out.println("*** void service.updateProduct(productVO)");
		/**/System.out.println("*** UpdateProductAction.java  end....>>");
		
		//return "redirect:/listProduct.do";
		//return "redirect:/getProduct.do?prodNo="+prodNo+"&menu=manage";	
		return "redirect:/getProduct.do?prodNo="+prodNo+"&menu=update";
	}

}
