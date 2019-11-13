package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {
		
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
		
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
	
	
	public static void main(String args[]) {
		PurchaseDao purchaseDao = new PurchaseDao();
		Purchase purchase = new Purchase();
		User user = new User();
		
		user.setUserId("user02");
		
		purchase.setBuyer(user);
//		purchaseDao.insertPurchase(purchase);
	}
}
