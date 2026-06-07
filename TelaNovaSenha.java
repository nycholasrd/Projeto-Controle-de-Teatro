package telas;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Pessoa;

public class TelaNovaSenha extends JFrame {
    private JTextField txtCodigo;
    private JPasswordField txtNovaSenha;
    private JPasswordField txtConfirmarSenha;
    private JButton btConfirmar;
    private JButton btCancelar;
    private String emailUsuario;

    public TelaNovaSenha(String emailUsuario) {
        this.emailUsuario = emailUsuario;

        setTitle("Nova Senha");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(0,1,10,10));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        txtCodigo = new JTextField();
        txtNovaSenha = new JPasswordField();
        txtConfirmarSenha = new JPasswordField();
        btConfirmar = new JButton("Confirmar");
        btCancelar = new JButton("Cancelar");

        painel.add(new JLabel("Código:"));
        painel.add(txtCodigo);
        painel.add(new JLabel("Nova senha:"));
        painel.add(txtNovaSenha);
        painel.add(new JLabel("Confirmar senha:"));
        painel.add(txtConfirmarSenha);
        painel.add(btConfirmar);
        painel.add(btCancelar);

        add(painel);

        btConfirmar.addActionListener(e -> confirmar());
        btCancelar.addActionListener(e -> cancelar());

        setVisible(true);
    }

    private void confirmar() {
        String codigo = txtCodigo.getText();
        String novaSenha = new String(txtNovaSenha.getPassword());
        String confirmarSenha = new String(txtConfirmarSenha.getPassword());

        if (!novaSenha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");
        Pessoa usuario = central.buscarPessoaPorEmail(emailUsuario);

        if (usuario != null && codigo.equals(usuario.getCodigoRecuperacao())) {
            usuario.setSenha(novaSenha);
            usuario.setCodigoRecuperacao(null); // limpa o código
            persistencia.salvarCentral(central, "dados.xml");
            JOptionPane.showMessageDialog(this, "Senha redefinida com sucesso!");
            dispose();
            new TelaLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Código inválido!");
        }
    }

    private void cancelar() {
        dispose();
        new TelaLogin();
    }
}
