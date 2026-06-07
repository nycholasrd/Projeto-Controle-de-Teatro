package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.PropostaDeAluguel;
import enums.Status;
import controle.GeradorDeContratos;

public class ContratosPanel extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaContratos;
    private JButton btEncerrar;
    private JButton btGerarPDF;

    public ContratosPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciamento de Contratos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        // Lista de contratos
        modeloLista = new DefaultListModel<>();
        listaContratos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaContratos);
        scroll.setBorder(BorderFactory.createTitledBorder("Contratos ativos"));
        add(scroll, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel();
        btEncerrar = new JButton("Encerrar Contrato");
        btGerarPDF = new JButton("Gerar PDF");
        painelBotoes.add(btEncerrar);
        painelBotoes.add(btGerarPDF);
        add(painelBotoes, BorderLayout.SOUTH);

        // Lógica dos botões
        btEncerrar.addActionListener(e -> encerrarContrato());
        btGerarPDF.addActionListener(e -> gerarContratoPDF());

        atualizarListaContratos();
    }

    private void atualizarListaContratos() {
        modeloLista.clear();
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        for (PropostaDeAluguel p : central.getTodasAsPropostas()) {
            if (p.getStatus() == Status.ATIVO) {
                modeloLista.addElement("ID: " + p.getId() +
                        " | Peça: " + p.getNomeDaPeca() +
                        " | Locatário: " + p.getLocatario());
            }
        }
    }

    private void encerrarContrato() {
        int index = listaContratos.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contrato!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        PropostaDeAluguel contrato = central.getTodasAsPropostas()
                .stream()
                .filter(p -> p.getStatus() == Status.ATIVO)
                .toList()
                .get(index);

        contrato.setStatus(Status.INATIVO);
        persistencia.salvarCentral(central, "dados.xml");
        JOptionPane.showMessageDialog(this, "Contrato encerrado!");
        atualizarListaContratos();
    }

    private void gerarContratoPDF() {
        int index = listaContratos.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um contrato!");
            return;
        }

        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        PropostaDeAluguel contrato = central.getTodasAsPropostas()
                .stream()
                .filter(p -> p.getStatus() == Status.ATIVO)
                .toList()
                .get(index);

        try {
            GeradorDeContratos.obterContrato(contrato.getId(), central);
            JOptionPane.showMessageDialog(this, "Contrato gerado em PDF!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar contrato: " + e.getMessage());
        }
    }
}
