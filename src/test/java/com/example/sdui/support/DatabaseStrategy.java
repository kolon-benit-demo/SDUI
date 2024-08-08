package com.example.sdui.support;

import jakarta.persistence.EntityManager;

public interface DatabaseStrategy {
	void disableConstraints(EntityManager entityManager);
	void enableConstraints(EntityManager entityManager);
	String getTruncateTableQuery(String tableName);
	String getResetAutoIncrementQuery(String tableName, String columnName);
}
