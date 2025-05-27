package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Tarefa;

public class TarefaDAO {

    public void adicionarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, status, idCategoria, idColaborador) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getStatus());
            stmt.setInt(4, tarefa.getIdCategoria());
            stmt.setInt(5, tarefa.getIdColaborador());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTarefas() {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getString("status"));
                tarefa.setIdCategoria(rs.getInt("idCategoria"));
                tarefa.setIdColaborador(rs.getInt("idColaborador"));

                lista.add(tarefa);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar tarefas: " + e.getMessage());
        }

        return lista;
    }

    public void atualizarTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, status = ?, idCategoria = ?, idColaborador = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setString(3, tarefa.getStatus());
            stmt.setInt(4, tarefa.getIdCategoria());
            stmt.setInt(5, tarefa.getIdColaborador());
            stmt.setInt(6, tarefa.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tarefa atualizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void excluirTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tarefa excluída com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}
