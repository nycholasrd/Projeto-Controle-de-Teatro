package telas;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import controle.CentralDeInformacoes;
import classes.PropostaDeAluguel;
import classes.Ingresso;
import classes.RegistroPresenca;
import classes.Pessoa;

public class DashboardUsuario extends JFrame {
    private CentralDeInformacoes central;
    private Pessoa usuario;

    private JButton btCadastrarPeca;
    private JButton btComprarIngressos;
    private JButton btConfirmarPresenca;

    public DashboardUsuario(CentralDeInformacoes central, Pessoa usuario) {
        this.central = central;
        this.usuario = usuario;

        setTitle("Dashboard do Usuário");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header com informações do usuário logado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel lblInfo = new JLabel("Usuário: " + usuario.getNome() + " | Email: " + usuario.getEmail());
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(lblInfo);

        // Painel de botões
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        btCadastrarPeca = new JButton("Cadastrar Peça");
        btComprarIngressos = new JButton("Comprar Ingressos");
        btConfirmarPresenca = new JButton("Confirmar Presença");

        btCadastrarPeca.addActionListener(e -> abrirCadastroPeca());
        btComprarIngressos.addActionListener(e -> abrirCompraIngressos());
        btConfirmarPresenca.addActionListener(e -> abrirConfirmacaoPresenca());

        contentPanel.add(btCadastrarPeca);
        contentPanel.add(btComprarIngressos);
        contentPanel.add(btConfirmarPresenca);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Cadastro de peça → vai direto para o painel de propostas do administrador
    private void abrirCadastroPeca() {
        JTextField nomePeca = new JTextField();
        JTextField valorAluguel = new JTextField();
        JTextField precoTicket = new JTextField();

        Object[] campos = {
            "Nome da Peça:", nomePeca,
            "Valor do Aluguel:", valorAluguel,
            "Preço do Ticket:", precoTicket
        };

        int opcao = JOptionPane.showConfirmDialog(this, campos, "Cadastro de Peça", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            PropostaDeAluguel proposta = new PropostaDeAluguel(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5),
                nomePeca.getText(),
                Float.parseFloat(valorAluguel.getText()),
                Float.parseFloat(precoTicket.getText()),
                usuario.getNome() // locatário é o usuário logado
            );
            central.adicionarPropostas(proposta);
            JOptionPane.showMessageDialog(this, "Peça cadastrada com sucesso! A proposta foi enviada ao administrador.");
        }
    }

    // Compra de ingressos
    private void abrirCompraIngressos() {
        List<PropostaDeAluguel> propostas = central.getTodasAsPropostas();
        if (propostas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma peça cadastrada!");
            return;
        }

        // for tradicional para montar array de nomes
        String[] nomes = new String[propostas.size()];
        for (int i = 0; i < propostas.size(); i++) {
            nomes[i] = propostas.get(i).getNomeDaPeca();
        }

        JComboBox<String> comboPecas = new JComboBox<>(nomes);
        JTextField quantidade = new JTextField();

        Object[] campos = {
            "Selecione a Peça:", comboPecas,
            "Quantidade de Ingressos:", quantidade
        };

        int opcao = JOptionPane.showConfirmDialog(this, campos, "Compra de Ingressos", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            int index = comboPecas.getSelectedIndex();
            PropostaDeAluguel propostaSelecionada = propostas.get(index);

            Ingresso ingresso = new Ingresso(
                propostaSelecionada.getId(),
                usuario.getCPF(), // CPF do usuário logado
                Integer.parseInt(quantidade.getText())
            );
            central.adicionarIngresso(ingresso);
            JOptionPane.showMessageDialog(this, "Ingressos comprados com sucesso!");
        }
    }

    // Confirmação de presença
    private void abrirConfirmacaoPresenca() {
        List<Ingresso> ingressos = central.getIngressos();
        if (ingressos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum ingresso comprado!");
            return;
        }

        // filtra ingressos do usuário logado
        java.util.List<Ingresso> ingressosUsuario = new java.util.ArrayList<>();
        for (Ingresso ing : ingressos) {
            if (ing.getCpfCliente().equals(usuario.getCPF())) {
                ingressosUsuario.add(ing);
            }
        }

        if (ingressosUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você não possui ingressos comprados!");
            return;
        }

        // for tradicional para montar array de opções
        String[] opcoes = new String[ingressosUsuario.size()];
        for (int i = 0; i < ingressosUsuario.size(); i++) {
            Ingresso ing = ingressosUsuario.get(i);
            opcoes[i] = "Peça ID: " + ing.getIdProposta() + " | Quantidade: " + ing.getQuantidade();
        }

        JComboBox<String> comboIngressos = new JComboBox<>(opcoes);
        Object[] campos = {
            "Selecione o ingresso para confirmar presença:", comboIngressos
        };

        int opcao = JOptionPane.showConfirmDialog(this, campos, "Confirmar Presença", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            int index = comboIngressos.getSelectedIndex();
            Ingresso ingressoSelecionado = ingressosUsuario.get(index);

            RegistroPresenca presenca = new RegistroPresenca(
                ingressoSelecionado.getCpfCliente(),
                getTitle(), ingressoSelecionado.getQuantidade()
            );
            central.adicionarPresenca(presenca);
            JOptionPane.showMessageDialog(this, "Presença confirmada com sucesso!");
        }
    }
}
