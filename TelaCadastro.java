package telas;

import java.awt.*;
import javax.swing.*;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Campos da tela, Adicionar um placeholder nos campos de tela.
        txtNome = new JTextField();
        txtCPF = new JTextField();
        txtTelefone = new JTextField();
        txtEmail = new JTextField();

        txtSenha = new JPasswordField();
        txtConfirmarSenha = new JPasswordField();

        cbTipoConta = new JComboBox<>();
        cbTipoConta.addItem("Administrador"); // De Adones: Temos que verificar caso já tenha um administrador no sistema.
        cbTipoConta.addItem("Funcionário");
        cbTipoConta.addItem("Usuário");

        // Botões, De Adones: Adicionar ouvintes EXTERNOS aos botões(Verificação de adm pelo ouvinte)
        btCadastrar = new JButton("Cadastrar");
        btLimpar = new JButton("Limpar");
        btVoltar = new JButton("Voltar");

        // Componentes
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
        setVisible(true);
    }
 public static void main(String[] args) {
	 TelaCadastro telaCadastro = new TelaCadastro();
 }
    
}