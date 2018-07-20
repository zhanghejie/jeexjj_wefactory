package com.xjj.wefactory.sys.code.service;

import java.util.List;

import com.xjj.wefactory.sys.code.entity.ColumnInfo;
import com.xjj.wefactory.sys.code.entity.TableInfo;


public interface CodeService {

	public List<String> findTableList();
	public List<ColumnInfo> findColumnsByTable(String tableName);
	public TableInfo getTableInfoByName(String tableName);
}
