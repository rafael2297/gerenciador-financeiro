package com.rafael.view;

import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rafael.dao.UsuarioDAO;
import com.rafael.model.Usuario;
import com.rafael.util.ConexaoDB;

public class TelaLogin extends JFrame {
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton botaoLogin;

    public TelaLogin() {
        setTitle("Login - Gerenciador Financeiro");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Email:"));
        campoEmail = new JTextField();
        add(campoEmail);

        add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        add(campoSenha);

        botaoLogin = new JButton("Entrar");
        add(botaoLogin);

        botaoLogin.addActionListener(e -> autenticar());

        setVisible(true);
    }

    private void autenticar() {
        String email = campoEmail.getText();
        String senha = new String(campoSenha.getPassword());

        try (Connection conn = ConexaoDB.conectar()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            if (usuarioDAO.autenticar(email, senha)) {
                Usuario usuario = usuarioDAO.buscarPorEmail(email);
                JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario.getNome() + "!");
                dispose(); // fecha tela de login
                new TelaPrincipal(usuario); // abre menu principal
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro na conexão com o banco.");
        }
    }


}
