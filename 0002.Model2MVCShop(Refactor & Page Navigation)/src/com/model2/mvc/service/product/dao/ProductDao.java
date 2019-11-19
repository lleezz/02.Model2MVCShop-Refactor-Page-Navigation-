package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDao {

	public ProductDao() {
		
	}
	
	public void insertProduct(Product product) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO product VALUES(seq_product_prod_no.nextval, ?, ?, REPLACE(?, '-', ''), ?, ?, SYSDATE)";
//		INSERT INTO product VALUES(seq_product_prod_no.nextval, '카누 디카페인', '밤에 마시기 좋아요', REPLACE('2019-11-01', '-', ''), 10000, '', SYSDATE);
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
	
	public Product findProduct(int prodNo) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "SELECT prod_no, prod_name, prod_detail, manufacture_day, price, image_file, reg_date FROM product WHERE prod_no = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);
		ResultSet rs = pStmt.executeQuery();
		
		Product product = new Product(); 
		while(rs.next()) {
			
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setPrice(rs.getInt("price"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setFileName(rs.getString("image_file"));
			product.setRegDate(rs.getDate("reg_date"));
		}
		return product;
		
	}

	public Map<String, Object> getProductList(Search search) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.prod_no, prod_name, prod_detail, manufacture_day, price, image_file, reg_date, tran_status_code FROM product p, transaction t WHERE p.prod_no = t.prod_no(+)";
		if(search.getSearchCondition() != null) {
			if(search.getSearchCondition().equals("0") && !search.getSearchKeyword().equals(""))
			sql += " AND prod_no LIKE'%" + search.getSearchKeyword() + "%'";
			if(search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals(""))
				sql += " AND prod_name LIKE'%" + search.getSearchKeyword() + "%'";
			if(search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals(""))
				sql += " AND price <=" + search.getSearchKeyword();
		}
		
		sql += " ORDER BY prod_no";
		System.out.println("ProductDAO:: Original SQL :: " + sql);
		
		int totalCount = this.getTotalCount(sql);		//전체 레코드 수 get
		System.out.println("ProductDAO:: totalCount :: " + totalCount);
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()) {
			Product product = new Product();
			
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setPrice(rs.getInt("price"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setFileName(rs.getString("image_file"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setProTranCode(rs.getString("tran_status_code"));
			
			list.add(product);
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		pStmt.close();
		rs.close();
		con.close();
			
		return map;
	}
	
	
	
	
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
	
	
	public static void main(String args[]) throws Exception {
		
		ProductDao productDao = new ProductDao();
		Product product = new Product();
		
//		product.setProdName("카누 디카페인");
//		product.setProdDetail("밤에 마시기 좋아요");
//		product.setManuDate("2019-11-01");
//		product.setPrice(10000);
//		
//		productDao.insertProduct(product);
		
		Search search = new Search();
		
		List<Product> list = new ArrayList<Product>();
		Map<String, Object> map = new HashMap<String, Object>();
		map = productDao.getProductList(search);
		list = (List<Product>) map.get("list");
		System.out.println(list);
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println("map list: " + list.get(i));
		}
		
		
		
	}

}
