package org.tarena.netctoss.dao;

import java.util.List;

import org.tarena.netctoss.entity.Cost;
import org.tarena.netctoss.util.MyBatisDao;

@MyBatisDao
public interface CostMapperDao {
	public List<Cost> findAll();
	public void deleteByID(int id);
	public void save(Cost c);
	public Cost findById(int id);
	public void updateCost(Cost c);
	public void updateStatus(int id);
	public List<Cost> findPage(int id);
	public int countCost();
	public List<Cost> monthly(int page);
	public List<Cost> monthlySortAsc(int page);
	public List<Cost> baseSortAsc(int page);
	public List<Cost> baseSortDesc(int page);
	public List<Cost> durationSortAsc(int page);
	public List<Cost> durationSortDesc(int page);
	public Integer findByName(String name);
}

