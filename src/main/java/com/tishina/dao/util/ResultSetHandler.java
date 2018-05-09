package com.tishina.dao.util;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {

    T handle(ResultSet rs) throws Exception;
}
