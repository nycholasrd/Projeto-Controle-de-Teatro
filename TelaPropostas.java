package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class TelaPropostas extends JFrame {
    private static final long serialVersionUID = 1L;
	
    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    
    public TelaPropostas() {
        setTitle("TelaProposta");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        criarHeader();
        criarSidebar();
        criarContent();

        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }
	
    public void criarHeader() {
        headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1200, 70));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
        
        JLabel titulo = new JLabel("RELATÓRIO DE VALORES E PROPOSTAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.BLACK);
        
        headerPanel.add(titulo, BorderLayout.CENTER);
    }
	
    public void criarSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(200, 700));
        sidebarPanel.setBackground(Color.DARK_GRAY);
        sidebarPanel.setLayout(new GridLayout(0,1,10,10));

        JButton btCadastrar = new JButton("Cadastrar Proposta");
        JButton btListar = new JButton("Listar Propostas");
        JButton btRelatorio = new JButton("Relatórios");
        JButton btVoltar = new JButton("Voltar");

        sidebarPanel.add(btCadastrar);
        sidebarPanel.add(btListar);
        sidebarPanel.add(btRelatorio);
        sidebarPanel.add(btVoltar);

        // ouvintes
        btCadastrar.addActionListener(e -> abrirCadastroProposta());
        btListar.addActionListener(e -> carregarTabelaPropostas());
        btRelatorio.addActionListener(e -> abrirRelatorio());
        btVoltar.addActionListener(e -> voltarMenu());
    }

    public void criarContent() {
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel placeholder = new JLabel("Selecione uma opção na barra lateral", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPanel.add(placeholder, BorderLayout.CENTER);
    }

    // Exemplo de método para carregar tabela
    private void carregarTabelaPropostas() {
        contentPanel.removeAll();

        String[] colunas = {"ID", "Artista", "Peça", "Status"};
        // Aqui você deve buscar as propostas da CentralDeInformacoes
        Object[][] dados = {
            {1, "João Silva", "Peça A", "Em contratação"},
            {2, "Maria Souza", "Peça B", "Contratado"}
        };

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        contentPanel.add(scroll, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Métodos placeholders para navegação
    private void abrirCadastroProposta() {
        JOptionPane.showMessageDialog(this, "Abrir tela de cadastro de proposta...");
    }

    private void abrirRelatorio() {
        JOptionPane.showMessageDialog(this, "Abrir tela de relatórios...");
    }

    private void voltarMenu() {
        JOptionPane.showMessageDialog(this, "Voltar ao menu principal...");
    }
public static void main(String[] args) {
	TelaPropostas t = new TelaPropostas();
}
}