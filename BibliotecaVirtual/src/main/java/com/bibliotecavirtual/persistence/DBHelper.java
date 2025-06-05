package com.bibliotecavirtual.persistence;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBHelper {

    public static <T>ObservableList<T> llenarTabla( String sql, RowMapper<T> mapper){

        ObservableList<T> lista = FXCollections.observableArrayList();

        try(Connection conn = DBConexion.establecerConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){

                T objeto = mapper.mapRow(rs);
                lista.add(objeto);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return lista;
    }

    public static int manejarEntidad (String sql, Object... parametros) throws SQLException{

        try(Connection conn = DBConexion.establecerConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            for (int i = 0; i<parametros.length; i++){

                Object obj = parametros[i];
                int indice = i+1;

                if(obj == null){

                    ps.setNull(indice, Types.INTEGER);
                }else if(obj instanceof java.sql.Time){

                    ps.setTime(indice, (java.sql.Time) obj);
                }else if(obj instanceof java.sql.Date){

                    ps.setDate(indice, (java.sql.Date) obj);
                }else{

                    ps.setObject(indice, obj);
                }
            }

            return ps.executeUpdate();
        } catch (SQLException e) {

            System.out.println("Error en DBHelper.manejarEntidad(): " + e.getMessage() );
            throw e;
        }
    }

    public static ResultSet ejecutarConsulta(String sql, Object... parametros) throws SQLException{

        try(Connection conn = DBConexion.establecerConexion();
        PreparedStatement ps = conn.prepareStatement(sql)){

            for(int i =0; i<parametros.length; i++){

                Object obj = parametros[i];
                int indice = i+1;

                if(obj ==null){

                    ps.setNull(indice, Types.INTEGER);
                }else if(obj instanceof java.sql.Time){

                    ps.setTime(indice, (java.sql.Time) obj);
                }else if(obj instanceof java.sql.Date){

                    ps.setDate(indice, (java.sql.Date)obj);
                }else{

                    ps.setObject(indice, obj);
                }
            }

            return ps.executeQuery();
        }catch (SQLException e) {

            System.out.println("Error en DBHelper.buscarFila(): " + e.getMessage());
            throw e;
        }
    }
}
