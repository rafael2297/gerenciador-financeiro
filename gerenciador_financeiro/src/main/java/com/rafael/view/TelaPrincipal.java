package com.rafael.view;



import com.rafael.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private Usuario usuario;

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Gerenciador Financeiro - Bem-vindo " + usuario.getNome());
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel saudacao = new JLabel("Olá, " + usuario.getNome() + "!", SwingConstants.CENTER);
        add(saudacao);

        JButton botaoNovaTransacao = new JButton("Nova Transação");
        JButton botaoListarTransacoes = new JButton("Listar Transações");
        JButton botaoVerSaldo = new JButton("Ver Saldo Atual");
        JButton botaoSair = new JButton("Sair");

        add(botaoNovaTransacao);
        add(botaoListarTransacoes);
        add(botaoVerSaldo);
        add(botaoSair);

        botaoNovaTransacao.addActionListener(e -> new TelaTransacao(usuario));
        botaoListarTransacoes.addActionListener(e -> new TelaListagem(usuario));
        botaoVerSaldo.addActionListener(e -> new TelaSaldo(usuario));
        botaoSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}

