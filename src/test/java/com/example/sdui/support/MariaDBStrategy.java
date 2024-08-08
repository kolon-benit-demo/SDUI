package com.example.sdui.support;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;

@Component("MariaDBDialect")
public class MariaDBStrategy implements DatabaseStrategy {

	@Override
	public void disableConstraints(EntityManager entityManager) {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
	}

	@Override
	public void enableConstraints(EntityManager entityManager) {
		entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
	}

	@Override
	public String getTruncateTableQuery(String tableName) {
		return "TRUNCATE TABLE " + tableName;
	}

	@Override
	public String getResetAutoIncrementQuery(String tableName, String columnName) {
		return "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
	}
}
