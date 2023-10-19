/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        

        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados!");
            
        }

    // SQL para inserir um novo item na tabela (substitua "sua_tabela" pelo nome da sua tabela)
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

    try {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setInt(2, produto.getValor());
        preparedStatement.setString(3, produto.getStatus());

        // Executa a consulta de inserção
        int linhasAfetadas = preparedStatement.executeUpdate();

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Novo produto inserido com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao inserir o novo produto");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            conn.close(); // Fecha a conexão com o banco de dados
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

