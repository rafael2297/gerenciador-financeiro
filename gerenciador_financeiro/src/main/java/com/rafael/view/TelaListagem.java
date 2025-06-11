package com.rafael.view;


import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.rafael.dao.TransacaoDAO;
import com.rafael.model.Transacao;
import com.rafael.model.Usuario;
import com.rafael.util.ConexaoDB;

public class TelaListagem extends JFrame {
    private Usuario usuario;
    private JTable tabela;

    public TelaListagem(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Transações - " + usuario.getNome());
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Tipo", "Descrição", "Valor", "Data"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        carregarTransacoes(modelo);

        setVisible(true);
    }

    private void carregarTransacoes(DefaultTableModel modelo) {
        try (Connection conn = ConexaoDB.conectar()) {
            TransacaoDAO dao = new TransacaoDAO(conn);
            List<Transacao> lista = dao.listarTransacoes(usuario.getId());

            for (Transacao t : lista) {
                modelo.addRow(new Object[]{
                    t.getTipo(),
                    t.getDescricao(),
                    String.format("R$ %.2f", t.getValor()),
                    t.getData().toString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar transações.");
        }
    }
}

