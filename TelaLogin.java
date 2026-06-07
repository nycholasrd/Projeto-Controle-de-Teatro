package telas;

import java.awt.GridLayout;
import javax.swing.*;

import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Pessoa;

public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private JButton btEntrar;
    private JButton btCriarConta;
    private JButton btEsqueciSenha;

    // atributo para guardar quem logou
    private Pessoa pessoaLogada;

    public TelaLogin() {
        setTitle("Login");
        setSize(500,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0,1,10,10));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        txtEmail = new JTextField(); 
        txtSenha = new JPasswordField();

        btEntrar = new JButton("Entrar");
        btCriarConta = new JButton("Criar Conta");
        btEsqueciSenha = new JButton("Esqueci minha senha");

        painel.add(new JLabel("Email"));
        painel.add(txtEmail);
        painel.add(new JLabel("Senha"));
        painel.add(txtSenha);
        painel.add(btEntrar);
        painel.add(btCriarConta);
        painel.add(btEsqueciSenha);

        add(painel);

        // ouvintes
        btEntrar.addActionListener(e -> validarLogin());
        btCriarConta.addActionListener(e -> abrirCadastro());
        btEsqueciSenha.addActionListener(e -> resetarSenha());

        setVisible(true);
    }

    private void validarLogin() {
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        Pessoa usuario = central.autenticarPessoas(email, senha);

        if (usuario != null) {
            pessoaLogada = usuario; // guarda quem logou
            JOptionPane.showMessageDialog(this, 
                "Login realizado com sucesso!\nCargo: " + usuario.getCargo());
            dispose(); // fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas!");
        }
    }

    private void abrirCadastro() {
        dispose();
        new TelaCadastro(); // abre tela de cadastro
    }

    private void resetarSenha() {
        JOptionPane.showMessageDialog(this, "Função de redefinição de senha...");
    }

    // getter para o Main usar
    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }
}
