package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.RegistroPresenca;
import java.util.ArrayList;

public class PresencaPanel extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaPresencas;
    private JTextField txtNomeCliente;
    private JTextField txtCpfCliente;
    private JTextField txtQuantidade;
    private JButton btRegistrar;
    private JButton btExcluir;
    private JButton btAtualizar;

    public PresencaPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Controle de Presenças");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        // Painel de registro
        JPanel painelRegistro = new JPanel(new GridLayout(0, 2, 10, 10));
        painelRegistro.setBorder(BorderFactory.createTitledBorder("Registrar Presença"));

        txtNomeCliente = new JTextField();
        txtCpfCliente = new JTextField();
        txtQuantidade = new JTextField();
        btRegistrar = new JButton("Registrar Presença");

        painelRegistro.add(new JLabel("Nome do Cliente:"));
        painelRegistro.add(txtNomeCliente);
        painelRegistro.add(new JLabel("CPF do Cliente:"));
        painelRegistro.add(txtCpfCliente);
        painelRegistro.add(new JLabel("Quantidade de Ingressos:"));
        painelRegistro.add(txtQuantidade);
        painelRegistro.add(new JLabel(""));
        painelRegistro.add(btRegistrar);

        add(painelRegistro, BorderLayout.WEST);

        // Lista de presenças
        modeloLista = new DefaultListModel<>();
        listaPresencas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaPresencas);
        scroll.setBorder(BorderFactory.createTitledBorder("Presenças registradas"));
        add(scroll, BorderLayout.CENTER);

        // Botões extras
        JPanel painelBotoes = new JPanel();
        btExcluir = new JButton("Excluir Presença");
        btAtualizar = new JButton("Atualizar Lista");
        painelBotoes.add(btExcluir);
        painelBotoes.add(btAtualizar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Lógica dos botões
        btRegistrar.addActionListener(e -> registrarPresenca());
        btExcluir.addActionListener(e -> excluirPresenca());
        btAtualizar.addActionListener(e -> atualizarListaPresencas());

        atualizarListaPresencas();
    }

    private void registrarPresenca() {
        try {
            String nomeCliente = txtNomeCliente.getText();
            String cpfCliente = txtCpfCliente.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            RegistroPresenca presenca = new RegistroPresenca(nomeCliente, cpfCliente, quantidade);

            Persistencia persistencia = new Persistencia();
            CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

            central.getPresencas().add(presenca); // precisa existir lista de presenças na central
            persistencia.salvarCentral(central, "dados.xml");

            JOptionPane.showMessageDialog(this, "Presença registrada com sucesso!");
            atualizarListaPresencas();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar presença: " + ex.getMessage());
        }
    }

    private void atualizarListaPresencas() {
        modeloLista.clear();
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        ArrayList<RegistroPresenca> presencas = central.getPresencas(); // precisa existir método getPresencas
        for (RegistroPresenca p : presencas) {
            modeloLista.addElement("Cliente: " + p.getNomeCliente() +
                    " | CPF: " + p.getCpfCliente() +
                    " | Ingressos: " + p.getQuantidadeIngressos());
        }
    }

    private void excluirPresenca() {
        int index = listaPresencas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma presença para excluir!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        central.getPresencas().remove(index); // precisa existir lista de presenças
        persistencia.salvarCentral(central, "dados.xml");

        JOptionPane.showMessageDialog(this, "Presença excluída com sucesso!");
        atualizarListaPresencas();
    }

    private void limparCampos() {
        txtNomeCliente.setText("");
        txtCpfCliente.setText("");
        txtQuantidade.setText("");
    }
}
