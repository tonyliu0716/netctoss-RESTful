package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.Account;
import org.tarena.netctoss.entity.AccountPage;
import org.tarena.netctoss.util.MyBatisDao;

@MyBatisDao
public interface AccountMapperDao {
	public List<Account> findByCondition(AccountPage page);
	public Integer countAll();
	public List<Account> findByPage(Integer page);
	public Integer countByCondition(AccountPage page);
}
