package com.rafael.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rafael.model.Transacao;

public class TransacaoDAO {
    private Connection conexao;

    public TransacaoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionarTransacao(Transacao t) throws SQLException {
        String sql = "INSERT INTO transacao (id_usuario, tipo, descricao, valor, data) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, t.getIdUsuario());
        stmt.setString(2, t.getTipo());
        stmt.setString(3, t.getDescricao());
        stmt.setDouble(4, t.getValor());
        stmt.setDate(5, Date.valueOf(t.getData()));
        stmt.execute();
        stmt.close();
    }

    public List<Transacao> listarTransacoes(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE id_usuario = ? ORDER BY data DESC";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        List<Transacao> lista = new ArrayList<>();
        while (rs.next()) {
            Transacao t = new Transacao();
            t.setId(rs.getInt("id"));
            t.setIdUsuario(rs.getInt("id_usuario"));
            t.setTipo(rs.getString("tipo"));
            t.setDescricao(rs.getString("descricao"));
            t.setValor(rs.getDouble("valor"));
            t.setData(rs.getDate("data").toLocalDate());
            lista.add(t);
        }
        rs.close();
        stmt.close();
        return lista;
    }
}
