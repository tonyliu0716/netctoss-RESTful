package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.ServicePage;
import org.tarena.netctoss.util.MyBatisDao;
import org.tarena.netctoss.vo.ServiceLine;

@MyBatisDao
public interface ServiceMapperDao {
	public List<ServiceLine> findByCondition(ServicePage page);
}
