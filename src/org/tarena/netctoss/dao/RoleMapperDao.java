package org.tarena.netctoss.dao;

import java.util.List;
import java.util.Map;

import org.tarena.netctoss.entity.Role;
import org.tarena.netctoss.entity.Role_privilege;
import org.tarena.netctoss.util.MyBatisDao;

@MyBatisDao
public interface RoleMapperDao {
	public List<Role> findAll1();
	public List<Role> findAll(Map<String, Integer> map);
	public Integer countAll();
	public void insertOne(Role r);
	public void insertPrivilege(Role_privilege p);
	public void deleteOneRole(Integer id);
	public void deleteOneRP(Integer id);
	public void updateRole(Map<String, Integer> map);
	public void updateRp(Map<String, Integer> map);
	public Role findById(Integer id);
	public void updateById(Map<String, String> map);
	public void deleteRP(Integer id);
	public void insertRP(Role_privilege rp);
}
