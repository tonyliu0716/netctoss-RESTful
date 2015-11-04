package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.AdminInfo;
import org.tarena.netctoss.util.MyBatisDao;
import org.tarena.netctoss.vo.AdminInfoPage;

@MyBatisDao
public interface AdminMapperDao {
	public int findByAdminCodeAndPwd(AdminInfo admin);
	public List<AdminInfo> findByCondition(AdminInfoPage page);
}
