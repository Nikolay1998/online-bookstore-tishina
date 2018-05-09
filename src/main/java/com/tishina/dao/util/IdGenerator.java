package com.tishina.dao.util;

import com.tishina.dao.TishinaDataSource;

import java.sql.ResultSet;

public class IdGenerator {

    private static IdGenerator instance = new IdGenerator();

    private IdGenerator(){
        System.out.println("IdGenerator creation");
        lastGeneratedId = (Integer) TishinaDataSource.executePreparedStatement(
                "select max(id) max from client",
                null,
                new ResultSetHandler() {
                    @Override
                    public Integer handle(ResultSet rs) throws Exception {
                        if (rs.next()) {
                            return rs.getInt("max");
                        } else {
                            throw new Exception("Can't find max id in client table");
                        }
                    }
                }
        );
    }

    public static IdGenerator getIdGenerator(){
        return instance;
    }

    private Integer lastGeneratedId;

    public Integer getNewId(){
        return ++lastGeneratedId;
    }
}
