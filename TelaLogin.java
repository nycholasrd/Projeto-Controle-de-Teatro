package telas;

import java.awt.GridLayout;
import javax.swing.*;

public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;

    private JButton btEntrar;
    private JButton btCriarConta;
    private JButton btEsqueciSenha;

    public TelaLogin() {

        setTitle("Login");
        setSize(500,350);
        setLocationRelativeTo(null);
        setResizable(false);

       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(0,1,10,10));
        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20)); // De Adones: Server para a criação de espaços em branco na janela, por enquanto apenas para teste

        // Campos, De Adones: Adicionar placeHolder
        txtEmail = new JTextField(); 
        txtSenha = new JPasswordField();

        // Botões, Adicionar ouvintes
        btEntrar = new JButton("Entrar");
        btCriarConta = new JButton("Criar Conta");
        btEsqueciSenha =
                new JButton("Esqueci minha senha");

        // Componentes
        painel.add(new JLabel("Email"));
        painel.add(txtEmail);

        painel.add(new JLabel("Senha"));
        painel.add(txtSenha);

        painel.add(btEntrar);
        painel.add(btCriarConta);
        painel.add(btEsqueciSenha);

        add(painel);

        setVisible(true);
    }
public static void main(String[] args) {
	TelaLogin telaLogin = new TelaLogin();
}

}