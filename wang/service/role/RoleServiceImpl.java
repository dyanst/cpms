package com.wang.service.role;

import com.wang.dao.DBCon;
import com.wang.dao.role.RoleDao;
import com.wang.dao.role.RoleDaoImpl;
import com.wang.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public class RoleServiceImpl implements RoleService{
	
	private RoleDao roleDao;
	
	public RoleServiceImpl(){
		roleDao = new RoleDaoImpl();
	}
	
	@Override
	public List<Role> getRoleList() {
		Connection connection = null;
		List<Role> roleList = null;
		try {
			connection = DBCon.getConnection();
			roleList = roleDao.getRoleList(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				DBCon.close(connection, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roleList;
	}
	
}