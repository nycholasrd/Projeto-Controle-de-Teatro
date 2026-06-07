package admin.paineis;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.PropostaDeAluguel;
import enums.Status;

public class PropostasPanel extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaPropostas;

    // Campos de cadastro
    private JTextField txtNomePeca;
    private JTextField txtLocatario;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;
    private JTextField txtValor;
    private JButton btCadastrar;
    private JButton btAtualizarLista;
    private JButton btContratar;
    private JButton btExcluir;

    public PropostasPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciamento de Propostas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        // Máscara para datas no formato dd/MM/yyyy
        MaskFormatter mascaraData = null;
        try {
            mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(0, 2, 10, 10));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Proposta"));

        txtNomePeca = new JTextField();
        txtLocatario = new JTextField();
        txtDataInicio = new JFormattedTextField(mascaraData);
        txtDataFim = new JFormattedTextField(mascaraData);
        txtValor = new JTextField();

        btCadastrar = new JButton("Cadastrar Proposta");

        painelCadastro.add(new JLabel("Nome da Peça:"));
        painelCadastro.add(txtNomePeca);
        painelCadastro.add(new JLabel("Locatário (CPF):"));
        painelCadastro.add(txtLocatario);
        painelCadastro.add(new JLabel("Data Início:"));
        painelCadastro.add(txtDataInicio);
        painelCadastro.add(new JLabel("Data Fim:"));
        painelCadastro.add(txtDataFim);
        painelCadastro.add(new JLabel("Valor Total:"));
        painelCadastro.add(txtValor);
        painelCadastro.add(new JLabel(""));
        painelCadastro.add(btCadastrar);

        add(painelCadastro, BorderLayout.WEST);

        // Lista de propostas
        modeloLista = new DefaultListModel<>();
        listaPropostas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaPropostas);
        scroll.setBorder(BorderFactory.createTitledBorder("Propostas cadastradas"));

        add(scroll, BorderLayout.CENTER);

        // Botões extras
        JPanel painelBotoes = new JPanel();
        btAtualizarLista = new JButton("Atualizar Lista");
        btContratar = new JButton("Marcar como Contratado");
        btExcluir = new JButton("Excluir Proposta");
        painelBotoes.add(btAtualizarLista);
        painelBotoes.add(btContratar);
        painelBotoes.add(btExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        // 🔑 Lógica dos botões
        btCadastrar.addActionListener(e -> cadastrarProposta());
        btAtualizarLista.addActionListener(e -> atualizarListaPropostas());
        btContratar.addActionListener(e -> contratarProposta());
        btExcluir.addActionListener(e -> excluirProposta());

        atualizarListaPropostas();
    }

    private LocalDate validarData(String texto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(texto, formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida: " + texto);
            return null;
        }
    }

    private void cadastrarProposta() {
        try {
            String nome = txtNomePeca.getText();
            String locatario = txtLocatario.getText();

            LocalDate inicio = validarData(txtDataInicio.getText());
            LocalDate fim = validarData(txtDataFim.getText());

            if (inicio == null || fim == null) return;

            if (inicio.isAfter(fim)) {
                JOptionPane.showMessageDialog(this, "Data de início não pode ser depois da data de fim!");
                return;
            }

            float valor;
            try {
                valor = Float.parseFloat(txtValor.getText());
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor numérico válido!");
                return;
            }

            PropostaDeAluguel proposta = new PropostaDeAluguel(LocalDate.now(), inicio, fim, nome, valor, locatario);

            Persistencia persistencia = new Persistencia();
            CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

            if (central.adicionarPropostas(proposta)) {
                persistencia.salvarCentral(central, "dados.xml");
                JOptionPane.showMessageDialog(this, "Proposta cadastrada com sucesso!");
                atualizarListaPropostas();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Já existe uma proposta com este ID!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar proposta: " + ex.getMessage());
        }
    }

    private void atualizarListaPropostas() {
        modeloLista.clear();
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        for (PropostaDeAluguel p : central.getTodasAsPropostas()) {
            modeloLista.addElement("Peça: " + p.getNomeDaPeca() + " | Locatário: " + p.getLocatario() + " | Status: " + p.getStatus());
        }
    }

    private void contratarProposta() {
        int index = listaPropostas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma proposta!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        PropostaDeAluguel proposta = central.getTodasAsPropostas().get(index);
        proposta.setStatus(Status.ATIVO);

        persistencia.salvarCentral(central, "dados.xml");
        JOptionPane.showMessageDialog(this, "Proposta marcada como contratada!");
        atualizarListaPropostas();
    }

    private void excluirProposta() {
        int index = listaPropostas.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma proposta para excluir!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        PropostaDeAluguel proposta = central.getTodasAsPropostas().get(index);
        central.getTodasAsPropostas().remove(proposta);

        persistencia.salvarCentral(central, "dados.xml");
        JOptionPane.showMessageDialog(this, "Proposta excluída com sucesso!");
        atualizarListaPropostas();
    }

    private void limparCampos() {
        txtNomePeca.setText("");
        txtLocatario.setText("");
        txtDataInicio.setValue(null);
        txtDataFim.setValue(null);
        txtValor.setText("");
    }
}
