package com.saulmmbp.just_another_hotel_alura.util;

import java.beans.PropertyVetoException;
import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.saulmmbp.just_another_hotel_alura.config.PropertiesReader;

public class MySqlConnection {
	
	private static ComboPooledDataSource pooledDataSource;
	
	private static ComboPooledDataSource getDataSource() {
		if(pooledDataSource == null) {
			/* Instanciamos pool */
			pooledDataSource = new ComboPooledDataSource();
			
			/* Configuramos conexión */
			String driverClass = PropertiesReader.getProperty("hotel_alura.db.driver");
			String url = PropertiesReader.getProperty("hotel_alura.db.url");
			String username = PropertiesReader.getProperty("hotel_alura.db.username");
			String password = PropertiesReader.getProperty("hotel_alura.db.password");
			
			try {
				pooledDataSource.setDriverClass(driverClass);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			
			pooledDataSource.setJdbcUrl(url);
			pooledDataSource.setUser(username);
			pooledDataSource.setPassword(password);
			
			/* Configuramos pool */
			int initialPoolSize = Integer.parseInt(PropertiesReader.getProperty("hotel_alura.db.pool.initial_pool_size"));
			int minPoolSize = Integer.parseInt(PropertiesReader.getProperty("hotel_alura.db.pool.min_pool_size"));
			int maxPoolSize = Integer.parseInt(PropertiesReader.getProperty("hotel_alura.db.pool.max_pool_size"));
			int maxIdleTime = Integer.parseInt(PropertiesReader.getProperty("hotel_alura.db.pool.max_idle_time"));
			int acquireIncrement = Integer.parseInt(PropertiesReader.getProperty("hotel_alura.db.pool.acquire_increment"));
			pooledDataSource.setInitialPoolSize(initialPoolSize);
			pooledDataSource.setMinPoolSize(minPoolSize);
			pooledDataSource.setMaxPoolSize(maxPoolSize);
			pooledDataSource.setMaxIdleTime(maxIdleTime);
			pooledDataSource.setAcquireIncrement(acquireIncrement);

		}
		return pooledDataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

}
