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
		System.out.println("가가가ㅏ가가가가ㅏ가ㅏ가가가가 " + buyerId);
		
		String sql = "SELECT prod_no, user_id, receiver_name, receiver_phone, tran_status_code FROM users, transaction WHERE users.user_id = transaction.buyer_id AND user_id = ? ORDER BY tran_no";
		
		int totalCount = this.getTotalCount(sql);		//전체 레코드 수 get
		System.out.println("PurchaseDAO:: totalCount :: " + totalCount);
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, buyerId);
		
		ResultSet rs = pStmt.executeQuery();
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
			Purchase purchase = new Purchase();
			Product product = new Product();
			User user = new User();
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
	
	
	public static void main(String args[]) {
		PurchaseDao purchaseDao = new PurchaseDao();
		Purchase purchase = new Purchase();
		User user = new User();
		
		user.setUserId("user02");
		
		purchase.setBuyer(user);
//		purchaseDao.insertPurchase(purchase);
	}
}
