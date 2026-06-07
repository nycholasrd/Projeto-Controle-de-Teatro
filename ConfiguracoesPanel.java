package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Administrador;

public class ConfiguracoesPanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btSalvar;

    public ConfiguracoesPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Configurações do Administrador", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel(new GridLayout(0, 2, 10, 10));
        painelForm.setBorder(BorderFactory.createTitledBorder("Dados do Administrador"));

        txtNome = new JTextField();
        txtEmail = new JTextField();
        txtSenha = new JPasswordField();

        painelForm.add(new JLabel("Nome:"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Email:"));
        painelForm.add(txtEmail);
        painelForm.add(new JLabel("Senha:"));
        painelForm.add(txtSenha);

        add(painelForm, BorderLayout.CENTER);

        btSalvar = new JButton("Salvar Alterações");
        add(btSalvar, BorderLayout.SOUTH);

        // Carregar dados atuais do administrador
        carregarDados();

        // Lógica do botão
        btSalvar.addActionListener(e -> salvarAlteracoes());
    }

    private void carregarDados() {
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        Administrador admin = central.getAdministrador();
        if (admin != null) {
            txtNome.setText(admin.getNome());
            txtEmail.setText(admin.getEmail());
            txtSenha.setText(admin.getSenha());
        }
    }

    private void salvarAlteracoes() {
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        Administrador admin = central.getAdministrador();
        if (admin != null) {
            admin.setNome(txtNome.getText());
            admin.setEmail(txtEmail.getText());
            admin.alterarSenha(new String(txtSenha.getPassword()));

            persistencia.salvarCentral(central, "dados.xml");
            JOptionPane.showMessageDialog(this, "Dados do administrador atualizados com sucesso!");
        }
    }
}
