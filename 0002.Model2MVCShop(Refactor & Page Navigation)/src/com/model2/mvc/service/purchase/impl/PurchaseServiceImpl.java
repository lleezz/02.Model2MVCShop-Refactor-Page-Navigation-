package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDao purchaseDao;
	
	public PurchaseServiceImpl() {
		purchaseDao = new PurchaseDao();
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		
		purchaseDao.insertPurchase(purchase);;
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		
		return purchaseDao.findPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		
		return purchaseDao.getPurchaseList(search, buyerId);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		
		return purchaseDao.getSaleList(search);
	}

	@Override
	public void updatePurcahse(Purchase purchase) throws Exception {

		purchaseDao.updatePurchase(purchase);
	}

	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);

	}

}
