package com.ti2cc;

import java.sql.*;

public class EsporteDAO {
    private Connection conexao;
    
    public EsporteDAO() {
        conexao = null;
    }
    
    public boolean conectar() {
        String driverName = "org.postgresql.Driver";                    
        String serverName = "localhost";
        String mydatabase = "teste";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao != null);
            System.out.println("Conexão efetuada com o PostgreSQL!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- " + e.getMessage());
        }

        return status;
    }
    
    public boolean close() {
        boolean status = false;
        
        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
    
    public boolean inserirEsporte(Esporte esporte) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO esporte (numero_jogadores, nome, data_criacao) "
                           + "VALUES ("+esporte.getNumeroJogadores()+ ", '" + esporte.getNome() + "', '"  
                           + esporte.getDataCriacao() + "');");
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean atualizarEsporte(Esporte esporte) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE esporte SET numero_jogadores = " + esporte.getNumeroJogadores() 
                       + ", nome = '" + esporte.getNome() + "', data_criacao = '" + esporte.getDataCriacao() 
                       + "' WHERE codigo = " + esporte.getNome();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean excluirEsporte(int codigo) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM esporte WHERE codigo = " + codigo);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public Esporte[] getEsportes() {
        Esporte[] esportes = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM esporte");       
             if(rs.next()){
                 rs.last();
                 esportes = new Esporte[rs.getRow()];
                 rs.beforeFirst();

                 for(int i = 0; rs.next(); i++) {
                    esportes[i] = new Esporte(rs.getInt("codigo"), rs.getInt("numero_jogadores"), 
                                              rs.getString("nome"), rs.getString("data_criacao"));
                 }
              }
              st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return esportes;
    }
}
