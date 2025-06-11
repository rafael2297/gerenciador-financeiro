package com.rafael.view;


import java.awt.GridLayout;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.rafael.dao.TransacaoDAO;
import com.rafael.model.Transacao;
import com.rafael.model.Usuario;
import com.rafael.util.ConexaoDB;

public class TelaTransacao extends JFrame {
    private Usuario usuario;
    private JComboBox<String> comboTipo;
    private JTextField campoDescricao;
    private JTextField campoValor;
    private JTextField campoData;
    private JButton botaoSalvar;

    public TelaTransacao(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Nova Transação");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Receita", "Despesa"});
        add(comboTipo);

        add(new JLabel("Descrição:"));
        campoDescricao = new JTextField();
        add(campoDescricao);

        add(new JLabel("Valor (Ex: 100.50):"));
        campoValor = new JTextField();
        add(campoValor);

        add(new JLabel("Data (YYYY-MM-DD):"));
        campoData = new JTextField(LocalDate.now().toString());
        add(campoData);

        botaoSalvar = new JButton("Salvar");
        add(botaoSalvar);

        botaoSalvar.addActionListener(e -> salvarTransacao());

        setVisible(true);
    }

    private void salvarTransacao() {
        String tipo = (String) comboTipo.getSelectedItem();
        String descricao = campoDescricao.getText();
        String valorTexto = campoValor.getText();
        String dataTexto = campoData.getText();

        try {
            double valor = Double.parseDouble(valorTexto);
            LocalDate data = LocalDate.parse(dataTexto);

            Transacao t = new Transacao();
            t.setIdUsuario(usuario.getId());
            t.setTipo(tipo);
            t.setDescricao(descricao);
            t.setValor(valor);
            t.setData(data);

            try (Connection conn = ConexaoDB.conectar()) {
                TransacaoDAO dao = new TransacaoDAO(conn);
                dao.adicionarTransacao(t);
                JOptionPane.showMessageDialog(this, "Transação registrada com sucesso!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar no banco de dados.");
            }

        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro nos dados. Verifique valor e data.");
        }
    }
}

