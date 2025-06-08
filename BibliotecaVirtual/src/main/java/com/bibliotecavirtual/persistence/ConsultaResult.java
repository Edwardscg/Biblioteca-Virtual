package com.bibliotecavirtual.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConsultaResult {

    private final Connection conn;
    private final PreparedStatement ps;
    private final ResultSet rs;

    public ConsultaResult(Connection conn, PreparedStatement ps, ResultSet rs) {
        this.conn = conn;
        this.ps = ps;
        this.rs = rs;
    }

    public ResultSet getResultSet(){

        return rs;
    }

    public void cerrar(){

        try{

            if(rs!=null){
                rs.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{

            if(ps!=null){
                ps.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{

            if(conn!=null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
