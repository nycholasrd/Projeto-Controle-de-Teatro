package admin.paineis;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import admin.DashboardAdmin;
import admin.componentes.CardDashboard;
import componentes.ContentPanel;

public class DashboardPanel extends JPanel {
	private ContentPanel contentPanel;
	private DashboardAdmin dashboard;

	public DashboardPanel(
	        DashboardAdmin dashboard) {

	    this.dashboard = dashboard;

	    inicializarComponentes();
	}
	private void inicializarComponentes() {

		JPanel painelSuperior = new JPanel();
		painelSuperior.setOpaque(false);

		painelSuperior.setLayout(
				new BoxLayout(
						painelSuperior,
						BoxLayout.Y_AXIS));

		JLabel titulo =
				new JLabel("Dashboard");

		titulo.setFont(
				new Font(
						"Segoe UI",
						Font.BOLD,
						32));

		JLabel subtitulo =
				new JLabel(
						"Bem-vindo ao Sistema de Gestão de Teatros");

		subtitulo.setFont(
				new Font(
						"Segoe UI",
						Font.PLAIN,
						16));

		JLabel acessoRapido =
				new JLabel("Acesso Rápido");

		acessoRapido.setFont(
				new Font(
						"Segoe UI",
						Font.BOLD,
						18));

		painelSuperior.add(titulo);
		painelSuperior.add(Box.createVerticalStrut(5));
		painelSuperior.add(subtitulo);
		painelSuperior.add(Box.createVerticalStrut(15));
		painelSuperior.add(new JSeparator());
		painelSuperior.add(Box.createVerticalStrut(15));
		painelSuperior.add(acessoRapido);

		JPanel painelCards =
				new JPanel(
						new GridLayout(
								2,
								3,
								20,
								20));

		painelCards.setOpaque(false);
		// "() ->" é uma expressão lambda do Java. 
		// Ela serve para representar um método de forma curta, sem precisar criar uma classe inteira
		// Além disso, é uma função anônima, estamos usando únicamente para a facilitação do projeto como um todo.
		// Todos os Cards precisam da expressão lambda por conta que no terceiro parametro foi pedido um runnable.
		// Você está criando um objeto que implementa Runnable 
		//(porque Runnable é uma interface funcional com apenas um método run()). Assim, o código só será executado quando o card for clicado, e não no momento da criação.
		CardDashboard cardUsuarios =
				new CardDashboard(
				        "Usuários",
				        "Gerenciar usuários",
				        () -> dashboard.abrirTela(
				                "usuarios",
				                dashboard.getSidebarAdmin()
				                         .getBtUsuarios()));
		painelCards.add(cardUsuarios);
		CardDashboard cardPropostas =
		        new CardDashboard(
		                "Propostas",
		                "Gerenciar propostas",
		               ()  -> dashboard.abrirTela(
		                        "propostas",
		                        dashboard.getSidebarAdmin()
		                                 .getBtPropostas()));
		painelCards.add(cardPropostas);
		CardDashboard cardIngressos = new CardDashboard(
				"Ingressos",
				"Gerenciar ingressos",
				() -> dashboard.abrirTela("ingressos", dashboard.getSidebarAdmin().getBtIngressos()));    
		painelCards.add(cardIngressos);
		CardDashboard cardPresenca = new CardDashboard(
				"Presença", 
				"Lista de presença",
				() -> dashboard.abrirTela(
				        "presenca",
				        dashboard.getSidebarAdmin()
				                 .getBtPresenca()));
		CardDashboard cardConfiguracao = new CardDashboard(
                "Configurações",
                "Gerenciar conta do administrador",
                () -> dashboard.abrirTela(
                        "configuracoes",
                        dashboard.getSidebarAdmin()
                                 .getBtConfiguracoes()));
//		CardDashboard cardRegras = new CardDashboard(
//				"Regras",
//				"Alocações", 
//				() -> dashboard.abrirTela(
//				        "regras",
//				        dashboard.getSidebarAdmin()
//				                 .getBtRegras()));
		painelCards.add(cardPresenca);
//		painelCards.add(cardRegras);
		painelCards.add(cardConfiguracao);
		add(painelSuperior, BorderLayout.NORTH);
		add(painelCards, BorderLayout.CENTER);
	}
}