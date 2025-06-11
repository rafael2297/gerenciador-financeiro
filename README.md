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
```
---

⚙️ Como Executar o Projeto
Clone o repositório:

bash
Copy
Edit
git clone https://github.com/seu-usuario/gerenciador-financeiro.git
Crie o banco gerenciador_financeiro e execute os comandos SQL acima.

Atualize a classe ConexaoDB.java com os dados de acesso ao seu MySQL:

java
Copy
Edit
private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_financeiro";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha";
Compile o projeto com Maven:

bash
Copy
Edit
mvn clean install
Execute a classe Main.java para abrir a aplicação:

bash
Copy
Edit
mvn exec:java -Dexec.mainClass="com.rafael.Main"
🧑‍💻 Autor
Rafael
Estudante de Análise e Desenvolvimento de Sistemas
Projeto de portfólio para prática de Java com banco de dados

📄 Licença
Este projeto é de uso pessoal/educacional.
