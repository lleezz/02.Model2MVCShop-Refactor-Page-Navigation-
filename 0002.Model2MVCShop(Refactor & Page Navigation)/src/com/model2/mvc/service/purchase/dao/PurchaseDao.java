package com.model2.mvc.service.purchase.dao;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {
		
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		pStmt.setString(2, purchase.getBuyer().getUserId());
		pStmt.setString(3, purchase.getPaymentOption());
		pStmt.setString(4, purchase.getReceiverName());
		pStmt.setString(5, purchase.getReceiverPhone());
		pStmt.setString(6, purchase.getDivyAddr());
		pStmt.setString(7, purchase.getDivyRequest());
		pStmt.setString(8, purchase.getTranCode());
		pStmt.setString(9, purchase.getDivyDate());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		
		
	}
	
	
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT tran_no, prod_no, user_id, receiver_name, receiver_phone, tran_status_code FROM users, transaction WHERE users.user_id = transaction.buyer_id AND users.user_id ='" + 
					buyerId + "' ORDER BY tran_no";
		
		int totalCount = this.getTotalCount(sql);		//전체 레코드 수 get
		System.out.println("PurchaseDAO:: totalCount :: " + totalCount);
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
			Purchase purchase = new Purchase();
			Product product = new Product();
			User user = new User();
			
			purchase.setTranNo(rs.getInt("tran_no"));
			user.setUserId(buyerId);
			
			product.setProdNo(rs.getInt("prod_no"));
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setTranCode(rs.getString("tran_status_code"));
			
			list.add(purchase);
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		pStmt.close();
		rs.close();
		con.close();
			
		return map;
	}
	
	
	
	
	public Map<String, Object> getSaleList(Search search) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.prod_no, prod_name, price, reg_date, tran_status_code, tran_no FROM product p, transaction t WHERE p.prod_no = t.prod_no ORDER BY tran_no";
		
		int totalCount = this.getTotalCount(sql);		//전체 레코드 수 get
		System.out.println("PurchaseDAO:: totalCount :: " + totalCount);
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
			Purchase purchase = new Purchase();
			Product product = new Product();
//			User user = new User();
			
			product.setProdNo(rs.getInt("prod_no"));
			product.setProdName(rs.getString("prod_name"));
			product.setPrice(rs.getInt("price"));
			product.setRegDate(rs.getDate("reg_date"));
//			product.setProTranCode(rs.getString("tran_status_code"));
			purchase.setPurchaseProd(product);
			purchase.setTranCode(rs.getString("tran_status_code"));
			purchase.setTranNo(rs.getInt("tran_no"));
			
			
			list.add(purchase);
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));

		pStmt.close();
		rs.close();
		con.close();
			
		return map;
	}
	
	
	
	
	public Purchase findPurchase(int tranNo) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT tran_no, prod_no, buyer_id, payment_option, receiver_name, receiver_phone, demailaddr, dlvy_request, TO_CHAR(dlvy_date, 'YYYY-MM-DD') dlvy_date, order_data, tran_status_code" + 
					 " FROM transaction WHERE tran_no = " + tranNo;
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();
		
		while(rs.next()) {
			
			
			user.setUserId(rs.getString("buyer_id"));
			product.setProdNo(rs.getInt("prod_no"));
			purchase.setTranNo(tranNo);
			purchase.setBuyer(user);
			purchase.setPurchaseProd(product);
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setPaymentOption(rs.getString("payment_option"));
			purchase.setDivyAddr(rs.getString("demailaddr"));
			purchase.setDivyRequest(rs.getString("dlvy_request"));
			purchase.setDivyDate(rs.getString("dlvy_date"));
			purchase.setOrderDate(rs.getDate("order_data"));
			purchase.setTranCode(rs.getString("tran_status_code"));
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return purchase;
		
	}
	
	
	public void updatePurchase(Purchase purchase) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction "
						+ "SET payment_option=?, receiver_name=?, receiver_phone=?, "
						+ "demailaddr=?, dlvy_request=?, dlvy_date=TO_DATE(?,'YYYY-MM-DD') "
						+ "WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setString(1, purchase.getPaymentOption());
		pStmt.setString(2, purchase.getReceiverName());
		pStmt.setString(3, purchase.getReceiverPhone());
		pStmt.setString(4, purchase.getDivyAddr());
		pStmt.setString(5, purchase.getDivyRequest());
		pStmt.setString(6, purchase.getDivyDate());
		pStmt.setInt(7, purchase.getTranNo());
		
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		
		
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		pStmt.setString(1, purchase.getTranCode());
		pStmt.setInt(2, purchase.getTranNo());
		
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
		PurchaseDao purchaseDao = new PurchaseDao();
		Purchase purchase = new Purchase();
//		User user = new User();
//		
//		user.setUserId("user02");
		
//		purchase.setBuyer(user);
//		purchaseDao.insertPurchase(purchase);
		
//		int tranNo = 10000;
		purchase = purchaseDao.findPurchase(10000);
		System.out.println(purchase);
	}
}
