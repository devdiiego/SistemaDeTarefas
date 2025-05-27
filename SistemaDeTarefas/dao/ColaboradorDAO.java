package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import model.Colaborador;

public class ColaboradorDAO {

    // Método para adicionar um colaborador
    public void adicionarColaborador(Colaborador colaborador) {
        String sql = "INSERT INTO colaboradores (nome, email) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getEmail());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Colaborador cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador: " + e.getMessage());
        }
    }

    public List<Colaborador> listarColaboradores() {
        List<Colaborador> lista = new ArrayList<>();
        String sql = "SELECT * FROM colaboradores";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Colaborador colaborador = new Colaborador();
                colaborador.setId(rs.getInt("id"));
                colaborador.setNome(rs.getString("nome"));
                colaborador.setEmail(rs.getString("email"));
                lista.add(colaborador);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar colaboradores: " + e.getMessage());
        }

        return lista;
    }

    public void atualizarColaborador(Colaborador colaborador) {
        String sql = "UPDATE colaboradores SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getEmail());
            stmt.setInt(3, colaborador.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Colaborador atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar colaborador: " + e.getMessage());
        }
    }

    public void excluirColaborador(int id) {
        String sql = "DELETE FROM colaboradores WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Colaborador excluído com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir colaborador: " + e.getMessage());
        }
    }
}
