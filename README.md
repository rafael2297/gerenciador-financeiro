# 💰 Gerenciador Financeiro Pessoal

Sistema Java desktop com interface gráfica (Swing) para controle de receitas e despesas pessoais.

---

## ✅ Funcionalidades

- Login de usuário
- Cadastro de receitas e despesas
- Listagem de transações
- Cálculo de saldo atual
- Interface gráfica com Java Swing
- Integração com banco de dados MySQL

---

## 🛠 Tecnologias Utilizadas

- Java 17+
- MySQL
- JDBC
- Swing
- Maven
- VS Code

---

## 🗃 Estrutura do Banco de Dados

Banco: `gerenciador_financeiro`

```sql
CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100),
  email VARCHAR(100),
  senha VARCHAR(100)
);

CREATE TABLE transacao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT,
  tipo ENUM('Receita', 'Despesa'),
  descricao VARCHAR(255),
  valor DECIMAL(10,2),
  data DATE,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
