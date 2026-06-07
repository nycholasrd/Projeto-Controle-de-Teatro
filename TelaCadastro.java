package telas;

import java.awt.*;
import javax.swing.*;

import classes.Administrador;
import classes.Usuario;
import classes.Pessoa;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import telas.TelaLogin;

public class TelaCadastro extends JFrame {

    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtTelefone;
    private JTextField txtEmail;

    private JPasswordField txtSenha;
    private JPasswordField txtConfirmarSenha;

    private JComboBox<String> cbTipoConta;

    private JButton btCadastrar;
    private JButton btLimpar;
    private JButton btVoltar;

    public TelaCadastro() {
        setTitle("Cadastro");
        setSize(550, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        txtNome = new JTextField();
        txtCPF = new JTextField();
        txtTelefone = new JTextField();
        txtEmail = new JTextField();

        txtSenha = new JPasswordField();
        txtConfirmarSenha = new JPasswordField();

        cbTipoConta = new JComboBox<>();
        cbTipoConta.addItem("Administrador");
        cbTipoConta.addItem("Usuário");

        btCadastrar = new JButton("Cadastrar");
        btLimpar = new JButton("Limpar");
        btVoltar = new JButton("Voltar");

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);

        painel.add(new JLabel("CPF:"));
        painel.add(txtCPF);

        painel.add(new JLabel("Telefone:"));
        painel.add(txtTelefone);

        painel.add(new JLabel("Email:"));
        painel.add(txtEmail);

        painel.add(new JLabel("Senha:"));
        painel.add(txtSenha);

        painel.add(new JLabel("Confirmar Senha:"));
        painel.add(txtConfirmarSenha);

        painel.add(new JLabel("Tipo Conta:"));
        painel.add(cbTipoConta);

        painel.add(btCadastrar);
        painel.add(btLimpar);
        painel.add(btVoltar);

        add(painel);

        // ouvintes
        btCadastrar.addActionListener(e -> cadastrar());
        btLimpar.addActionListener(e -> limparCampos());
        btVoltar.addActionListener(e -> voltar());

        setVisible(true);
    }

    private void cadastrar() {
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        String confirmar = new String(txtConfirmarSenha.getPassword());
        String tipoConta = (String) cbTipoConta.getSelectedItem();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        if (!senha.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!");
            return;
        }

        // Recupera central de informações
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        Pessoa novaPessoa;
        if ("Administrador".equals(tipoConta)) {
            novaPessoa = new Administrador(nome, email, senha, cpf, telefone);
        } else {
            novaPessoa = new Usuario(nome, email, senha, cpf, telefone);
        }

        central.adicionarPessoas(novaPessoa);
        persistencia.salvarCentral(central, "dados.xml");

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");

        // Após cadastrar, volta para tela de login
        dispose();
        new TelaLogin();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtConfirmarSenha.setText("");
        cbTipoConta.setSelectedIndex(0);
    }

    private void voltar() {
        // Recupera a central
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        if (central.getTodasAsPessoas().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Não é possível voltar para o login.\nNenhum cadastro encontrado!");
            return; // não faz nada
        }

        dispose(); // fecha a tela de cadastro
        new TelaLogin(); // abre a tela de login
    }


    public static void main(String[] args) {
        new TelaCadastro();
    }
}
