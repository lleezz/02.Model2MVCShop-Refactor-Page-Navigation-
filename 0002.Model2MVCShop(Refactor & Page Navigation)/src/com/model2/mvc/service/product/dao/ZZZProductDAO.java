package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ZZZProductDAO {
	
	public ZZZProductDAO(){
	}
	
	//findProduct()
	public Product findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "SELECT "
						+ "p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, "
						+ "p.price, p.image_file, p.reg_date, NVL(t.tran_status_code, 'sale') tsc " 
						//+ "p.price, p.image_file, p.reg_date " 
						+ "FROM product p, transaction t " 
						+ "WHERE p.prod_no = t.prod_no(+) " 
						+ "AND p.prod_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);

		ResultSet rs = pStmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setPrice(rs.getInt("price"));
			product.setFileName(rs.getString("image_file"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setProTranCode(rs.getString("tsc"));
		}
		
		pStmt.close();
		rs.close();
		con.close();

		return product;
	}

	//getProductList() ** count=total / list=list(ProductVO) map으로 반환
	public Map<String,Object> getProductList(Search search) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//상품명, 가격, 등록일, 현재상태 display
		String sql = "SELECT "
						+ "p.prod_no, p.prod_name, p.price, p.reg_date, NVL(t.tran_status_code, 'sale') tsc " 
						+ "FROM product p, transaction t " 
						+ "WHERE p.prod_no = t.prod_no(+) ";	
		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals("") ) {
				sql += "AND p.prod_no=" + search.getSearchKeyword();		//Prod_no
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += "AND p.prod_name LIKE '%" + search.getSearchKeyword() + "%'";
			} else if ( search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("") ) {
				sql += "AND p.price <= " + search.getSearchKeyword() + "";
			}
		}
		sql += " ORDER BY prod_no";
		
		/**/System.out.println("ProductDAO:: Original SQL :: " + sql);

		int totalCount = this.getTotalCount(sql);		//전체 레코드 수 get
		/**/System.out.println("ProductDAO:: totalCount :: " + totalCount);
		
		sql = makeCurrentPageSql(sql, search);			//currentPage 게시물만 긁어오는 쿼리 재구성
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		List<Product> list = new ArrayList<Product>();
		while( rs.next() ) {
			Product product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setPrice(rs.getInt("price"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setProTranCode(rs.getString("tsc"));
			
			list.add(product);
		}
	
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		pStmt.close();
		rs.close();
		con.close();
			
		return map;
	}
	
	//insertProduct() ****
	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "INSERT "
						+ "INTO product "
						+ "VALUES (seq_product_prod_no.NEXTVAL,?,?,?,?,?,SYSDATE)";
		//p.prod_no, p.prod_name, p.prod_detail, p.manufacture_day, p.price, p.image_file, p.reg_date
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setString(1, product.getProdName());
		pStmt.setString(2, product.getProdDetail());
		pStmt.setString(3, product.getManuDate());
		pStmt.setInt(4, product.getPrice());
		pStmt.setString(5, product.getFileName());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}
	
	//updateProduct()
	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product "
						+ "SET prod_name=?, prod_detail=?, manufacture_day=?, price=?, image_file=? "
						+ "WHERE prod_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, product.getProdName());
		pStmt.setString(2, product.getProdDetail());
		pStmt.setString(3, product.getManuDate());
		pStmt.setInt(4, product.getPrice());
		pStmt.setString(5, product.getFileName());
		pStmt.setInt(6, product.getProdNo());

		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
	}
	
	
	/*			02 Refactoring			*/
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
	
	
}