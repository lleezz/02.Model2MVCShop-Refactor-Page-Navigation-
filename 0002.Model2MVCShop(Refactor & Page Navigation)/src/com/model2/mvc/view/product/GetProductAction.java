package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


//��ǰ �� ��ȸ ��û
public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/**/System.out.println("*** GetProductAction.java  start....");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		Product vo = service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		
		
		//���ǿ��� recentProd ��Ʈ����Ʈ.... ��
//		HttpSession session = request.getSession();
//		Vector<ProductVO> recentProd = (Vector<ProductVO>)session.getAttribute("recentProd");
//		
//		if(recentProd == null) {
//			recentProd = new Vector<ProductVO>();
//			recentProd.add(vo);
//		}else {
//			recentProd.add(vo);
//		}
		
		//��Ű
		Cookie[] cookies = request.getCookies();
		String history = null;
		if(cookies!=null && cookies.length>0) {
			for(int i=0; i<cookies.length; i++) {
				
				if(cookies[i].getName().equals("history")) {
					System.out.println("--> GetProductAction�� history ��Ű ���� -- :: "+cookies[i].getName()+"-> "+cookies[i].getValue());
					
					history = cookies[i].getValue()+","+prodNo;
					cookies[i].setValue(history);
					System.out.println("--> GetProductActiond�� history ��Ű ����  -- :: "+cookies[i].getName()+"-> "+cookies[i].getValue());
					cookies[i].setMaxAge(-1);
					response.addCookie(cookies[i]);
					
				}
			}
			if(history == null) {
				history = ""+prodNo;
				Cookie cookie = new Cookie("history", history);
				cookie.setMaxAge(-1);
				response.addCookie(cookie);	
				System.out.println("--> GetProductAction���� history ��Ű ù ���� -- :: "+cookie.getName()+"-> "+cookie.getValue());
			}
		}
		
		/**/System.out.println("*** Product < service.getProduct(prodNo) --> req.setAtt(vo)");
		/**/System.out.println("*** GetProductAction.java  end....>>");
		
		String result = "forward:/product/getProduct.jsp";
		
		if( request.getParameter("menu")!=null && request.getParameter("menu").equals("manage")) {
			result = "forward:/updateProductView.do?prodNo="+prodNo;
			//result = "forward:/updateProduct.jsp";
		}
		
		return result;

	}

}
