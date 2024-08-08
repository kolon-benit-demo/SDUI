package com.example.sdui.support;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;

@Component("H2Dialect")
public class H2DatabaseStrategy implements DatabaseStrategy {

	@Override
	public void disableConstraints(EntityManager entityManager) {
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
	}

	@Override
	public void enableConstraints(EntityManager entityManager) {
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
	}

	@Override
	public String getTruncateTableQuery(String tableName) {
		return "TRUNCATE TABLE " + tableName;
	}

	@Override
	public String getResetAutoIncrementQuery(String tableName, String columnName) {
		return "ALTER TABLE " + tableName + " ALTER COLUMN " + columnName + " RESTART WITH 1";
	}
}
