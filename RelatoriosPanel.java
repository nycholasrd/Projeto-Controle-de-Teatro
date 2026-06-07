package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.PropostaDeAluguel;
import enums.Status;

public class RelatoriosPanel extends JPanel {

    private JLabel lblEmAvaliacao;
    private JLabel lblAtivos;
    private JLabel lblInativos;
    private JLabel lblNaoContratados;
    private JLabel lblValorTotal;
    private JButton btAtualizar;

    public RelatoriosPanel() {
        setLayout(new GridLayout(0, 1, 10, 10));

        JLabel titulo = new JLabel("Relatórios do Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titulo);

        lblEmAvaliacao = new JLabel();
        lblAtivos = new JLabel();
        lblInativos = new JLabel();
        lblNaoContratados = new JLabel();
        lblValorTotal = new JLabel();

        add(lblEmAvaliacao);
        add(lblAtivos);
        add(lblInativos);
        add(lblNaoContratados);
        add(lblValorTotal);

        // Botão de atualizar
        btAtualizar = new JButton("Atualizar Relatórios");
        btAtualizar.addActionListener(e -> atualizarRelatorios());
        add(btAtualizar);

        atualizarRelatorios();
    }

    private void atualizarRelatorios() {
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        int emAvaliacao = 0;
        int ativos = 0;
        int inativos = 0;
        int naoContratados = 0;
        float valorTotal = 0;

        for (PropostaDeAluguel p : central.getTodasAsPropostas()) {
            switch (p.getStatus()) {
                case EM_AVALIACAO -> emAvaliacao++;
                case ATIVO -> {
                    ativos++;
                    valorTotal += p.getValorTotalDoAluguel();
                }
                case INATIVO -> inativos++;
                case NÃO_CONTRATADO -> naoContratados++;
            }
        }

        lblEmAvaliacao.setText("Propostas em avaliação: " + emAvaliacao);
        lblAtivos.setText("Contratos ativos: " + ativos);
        lblInativos.setText("Contratos encerrados: " + inativos);
        lblNaoContratados.setText("Propostas não contratadas: " + naoContratados);
        lblValorTotal.setText("Valor total dos contratos ativos: R$ " + valorTotal);
    }
}
