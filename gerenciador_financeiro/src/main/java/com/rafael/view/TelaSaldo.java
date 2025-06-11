package com.rafael.view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.rafael.dao.TransacaoDAO;
import com.rafael.model.Transacao;
import com.rafael.model.Usuario;
import com.rafael.util.ConexaoDB;

public class TelaSaldo extends JFrame {
    private Usuario usuario;
    private JLabel labelSaldo;

    public TelaSaldo(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Saldo Atual - " + usuario.getNome());
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        labelSaldo = new JLabel("Calculando saldo...", SwingConstants.CENTER);
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelSaldo, BorderLayout.CENTER);

        calcularSaldo();

        setVisible(true);
    }

    private void calcularSaldo() {
        try (Connection conn = ConexaoDB.conectar()) {
            TransacaoDAO dao = new TransacaoDAO(conn);
            List<Transacao> transacoes = dao.listarTransacoes(usuario.getId());

            double saldo = 0.0;
            for (Transacao t : transacoes) {
                if ("Receita".equalsIgnoreCase(t.getTipo())) {
                    saldo += t.getValor();
                } else if ("Despesa".equalsIgnoreCase(t.getTipo())) {
                    saldo -= t.getValor();
                }
            }

            labelSaldo.setText(String.format("Saldo Atual: R$ %.2f", saldo));

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao calcular saldo.");
        }
    }
}

