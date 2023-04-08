package com.saulmmbp.just_another_hotel_alura.dataaccess;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, E> {

	int save(T entity) throws SQLException;
	
	T findById(E id) throws SQLException;
	
	List<T> findAll() throws SQLException;
	
	int deleteById(E id) throws SQLException;
	
}
