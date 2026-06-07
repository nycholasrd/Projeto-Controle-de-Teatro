package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Ingresso;
import java.util.ArrayList;

public class IngressosPanel extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaIngressos;
    private JTextField txtIdProposta;
    private JTextField txtCpfCliente;
    private JTextField txtQuantidade;
    private JButton btCadastrar;
    private JButton btExcluir;
    private JButton btAtualizar;

    public IngressosPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciamento de Ingressos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(0, 2, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Ingresso"));

        txtIdProposta = new JTextField();
        txtCpfCliente = new JTextField();
        txtQuantidade = new JTextField();

        btCadastrar = new JButton("Cadastrar Ingresso");

        painelCadastro.add(new JLabel("ID da Proposta:"));
        painelCadastro.add(txtIdProposta);
        painelCadastro.add(new JLabel("CPF do Cliente:"));
        painelCadastro.add(txtCpfCliente);
        painelCadastro.add(new JLabel("Quantidade:"));
        painelCadastro.add(txtQuantidade);
        painelCadastro.add(new JLabel(""));
        painelCadastro.add(btCadastrar);

        add(painelCadastro, BorderLayout.WEST);

        // Lista de ingressos
        modeloLista = new DefaultListModel<>();
        listaIngressos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaIngressos);
        scroll.setBorder(BorderFactory.createTitledBorder("Ingressos cadastrados"));
        add(scroll, BorderLayout.CENTER);

        // Botões extras
        JPanel painelBotoes = new JPanel();
        btExcluir = new JButton("Excluir Ingresso");
        btAtualizar = new JButton("Atualizar Lista");
        painelBotoes.add(btExcluir);
        painelBotoes.add(btAtualizar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Lógica dos botões
        btCadastrar.addActionListener(e -> cadastrarIngresso());
        btExcluir.addActionListener(e -> excluirIngresso());
        btAtualizar.addActionListener(e -> atualizarListaIngressos());

        atualizarListaIngressos();
    }

    private void cadastrarIngresso() {
        try {
            long idProposta = Long.parseLong(txtIdProposta.getText());
            String cpfCliente = txtCpfCliente.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());

            Ingresso ingresso = new Ingresso(idProposta, cpfCliente, quantidade);

            Persistencia persistencia = new Persistencia();
            CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

            central.getIngressos().add(ingresso); // precisa existir lista de ingressos na central
            persistencia.salvarCentral(central, "dados.xml");

            JOptionPane.showMessageDialog(this, "Ingresso cadastrado com sucesso!");
            atualizarListaIngressos();
            limparCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar ingresso: " + ex.getMessage());
        }
    }

    private void atualizarListaIngressos() {
        modeloLista.clear();
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        ArrayList<Ingresso> ingressos = central.getIngressos(); 
        for (Ingresso i : ingressos) {
            modeloLista.addElement("ID: " + i.getId() +
                    " | Proposta: " + i.getIdProposta() +
                    " | Cliente: " + i.getCpfCliente() +
                    " | Quantidade: " + i.getQuantidade() +
                    " | Data: " + i.getDataVenda());
        }
    }

    private void excluirIngresso() {
        int index = listaIngressos.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um ingresso para excluir!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        central.getIngressos().remove(index); // Modificação na central.
        persistencia.salvarCentral(central, "dados.xml");

        JOptionPane.showMessageDialog(this, "Ingresso excluído com sucesso!");
        atualizarListaIngressos();
    }

    private void limparCampos() {
        txtIdProposta.setText("");
        txtCpfCliente.setText("");
        txtQuantidade.setText("");
    }
}
