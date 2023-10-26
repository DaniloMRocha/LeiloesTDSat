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
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


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
        
        ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();

        try {
            conn = new conectaDAO().connectDB();
            String sql = "SELECT * FROM produtos";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                int valor = result.getInt("valor");
                String status = result.getString("status");
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(id);         
                produto.setNome(nome);     
                produto.setValor(valor);   
                produto.setStatus(status);
                listaProdutos.add(produto);
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao listar os produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return listaProdutos;
    }
    
    public void venderProduto(int id) {
    try {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        conn.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao vender o produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    

}
    
        


