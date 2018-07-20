package com.xjj.wefactory.sys.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xjj.wefactory.sys.code.dao.CodeDao;
import com.xjj.wefactory.sys.code.entity.ColumnInfo;
import com.xjj.wefactory.sys.code.entity.TableInfo;
import com.xjj.wefactory.sys.code.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {


	@Autowired
	private CodeDao codeDao;
	
	public List<String> findTableList()
	{
		return codeDao.findTableList();
	}
	public List<ColumnInfo> findColumnsByTable(String tableName)
	{
		String dbName = codeDao.getDataBaseName();
		
		return codeDao.findColumnsByTable(tableName,dbName);
	}
	
	public TableInfo getTableInfoByName(String tableName)
	{
		String dbName = codeDao.getDataBaseName();
		return codeDao.getTableInfoByName(tableName,dbName);
	}
}
