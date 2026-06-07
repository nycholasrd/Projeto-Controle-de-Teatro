package telas;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import controle.Mensageiro;
import classes.Pessoa;

public class TelaRecuperarSenha extends JFrame {
    private JTextField txtEmail;
    private JButton btEnviarCodigo;
    private JButton btVoltar;

    public TelaRecuperarSenha() {
        setTitle("Recuperar Senha");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(0,1,10,10));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        txtEmail = new JTextField();
        btEnviarCodigo = new JButton("Enviar Código");
        btVoltar = new JButton("Voltar");

        painel.add(new JLabel("Digite seu e-mail:"));
        painel.add(txtEmail);
        painel.add(btEnviarCodigo);
        painel.add(btVoltar);

        add(painel);

        btEnviarCodigo.addActionListener(e -> enviarCodigo());
        btVoltar.addActionListener(e -> voltar());

        setVisible(true);
    }

    private void enviarCodigo() {
        String email = txtEmail.getText();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite seu e-mail!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        Pessoa usuario = central.buscarPessoaPorEmail(email);
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Email não encontrado!");
            return;
        }

        // gera código aleatório
        String codigo = UUID.randomUUID().toString().substring(0,6).toUpperCase();
        usuario.setCodigoRecuperacao(codigo);
        persistencia.salvarCentral(central, "dados.xml");

        try {
            // envia o código por e-mail
            Mensageiro.enviarCodigoRecuperacao(email, codigo);
            JOptionPane.showMessageDialog(this, "Um código foi enviado para seu e-mail.");
            dispose();
            new TelaNovaSenha(email);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao enviar e-mail: " + ex.getMessage());
        }
    }

    private void voltar() {
        dispose();
        new TelaLogin();
    }
}
