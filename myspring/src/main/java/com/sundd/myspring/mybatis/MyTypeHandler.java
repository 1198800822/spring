package com.sundd.myspring.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyTypeHandler extends BaseTypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, parameter);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnIndex);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return cs.getString(columnIndex);
	}

}
