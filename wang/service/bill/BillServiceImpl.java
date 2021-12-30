package com.wang.service.bill;

import com.wang.dao.DBCon;
import com.wang.dao.bill.BillDao;
import com.wang.dao.bill.BillDaoImpl;
import com.wang.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


//import pojo.Provider;

public class BillServiceImpl implements BillService {
	
	private BillDao billDao;
	public BillServiceImpl(){
		billDao = new BillDaoImpl();
	}
	public boolean add(Bill bill) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = null;
		try {
			connection = DBCon.getConnection();
			connection.setAutoCommit(false);//开启JDBC事务管理
			if(billDao.add(connection,bill) > 0)
				flag = true;
			connection.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				System.out.println("rollback==================");
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			//在service层进行connection连接的关闭
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public List<Bill> getBillList(Bill bill) {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Bill> billList = null;
		System.out.println("query productName ---- > " + bill.getProductName());
		System.out.println("query providerId ---- > " + bill.getProviderId());
		System.out.println("query isPayment ---- > " + bill.getIsPayment());
		
		try {
			connection = DBCon.getConnection();
			billList = billDao.getBillList(connection, bill);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return billList;
	}

	@Override
	public boolean deleteBillById(String delId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DBCon.getConnection();
			if(billDao.deleteBillById(connection, delId) > 0)
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Bill getBillById(String id) {
		// TODO Auto-generated method stub
		Bill bill = null;
		Connection connection = null;
		try{
			connection = DBCon.getConnection();
			bill = billDao.getBillById(connection, id);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			bill = null;
		}finally{
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bill;
	}

	public boolean modify(Bill bill) {
		// TODO Auto-generated method stub
		Connection connection = null;
		boolean flag = false;
		try {
			connection = DBCon.getConnection();
			if(billDao.modify(connection,bill) > 0)
				flag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
