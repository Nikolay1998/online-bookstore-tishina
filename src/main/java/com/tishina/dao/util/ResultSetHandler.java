package com.tishina.dao.util;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {

    public T handle(ResultSet rs) throws Exception;
}
