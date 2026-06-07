package telas;

import java.awt.*;
import javax.swing.*;

public class DashboardAdmin extends JFrame {
    private CentralDeInformacoes central = new CentralDeInformacoes();
    private JPanel headerPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;

    public DashboardAdmin() {

        setTitle("Dashboard Administrador");
        setSize(1200,700);
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

    private void criarHeader() {

        headerPanel = new JPanel();
        headerPanel.setPreferredSize(
                new Dimension(1200,70));

        headerPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Header"));

        headerPanel.add(
                new JLabel(
                        "Usuário | Cargo | Data | Logout"));
    }

    private void criarSidebar()  {

        sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(
                new Dimension(200,700));

        sidebarPanel.setLayout(
                new GridLayout(0,1,5,5));

        sidebarPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Menu"));

        sidebarPanel.add(
                new JButton("Dashboard"));

        sidebarPanel.add(
                new JButton("Usuários"));

        sidebarPanel.add(
                new JButton("Regras"));

        sidebarPanel.add(
                new JButton("Propostas"));


        JButton btnEstenderContrato = new JButton("Estender Contrato");
        btnEstenderContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                TelaEstenderContrato tela = new TelaEstenderContrato(central);
                tela.setVisible(true);
            }
        });
        sidebarPanel.add(btnEstenderContrato);


        sidebarPanel.add(
                new JButton("Ingressos"));

        sidebarPanel.add(
                new JButton("Presença"));

        sidebarPanel.add(
                new JButton("Relatórios"));

        sidebarPanel.add(
                new JButton("Configurações"));

        sidebarPanel.add(
                new JButton("Logout"));
    }

    private void criarContent() {

        contentPanel = new JPanel();

        contentPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Área de Conteúdo"));

        contentPanel.add(
                new JLabel(
                        "Content Panel"));
    }

    public static void main(String[] args) {
        DashboardAdmin adm = new DashboardAdmin();
    }
}