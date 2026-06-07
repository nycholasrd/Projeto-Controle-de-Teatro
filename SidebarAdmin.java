package admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import componentes.SidebarPadrao;

public class SidebarAdmin extends SidebarPadrao {

	private JButton btDashboard;
	private JButton btUsuarios;
	private JButton btPropostas;
	private JButton btContratos;
	private JButton btIngressos;
	private JButton btPresenca;
	private JButton btRelatorios;
	private JButton btConfiguracoes;
	private JButton btLogout;

	private JButton botaoSelecionado;

	private static final Color COR_PADRAO =
			new Color(245, 245, 245);

	private static final Color COR_SELECIONADO =
			new Color(220, 220, 220);

	private static final Color COR_BARRA =
			new Color(52, 152, 219);

	public SidebarAdmin() {

		configurarSidebar();

		criarComponentes();

		adicionarComponentes();

		selecionarBotao(btDashboard);
	}

	private void configurarSidebar() {

		setLayout(
				new BoxLayout(
						this,
						BoxLayout.Y_AXIS));

		setPreferredSize(
				new Dimension(
						230,
						0));

		setBackground(COR_PADRAO);
		setBorder(
				BorderFactory.createEmptyBorder(
						20,
						10,
						20,
						10));
	}

	private void criarComponentes() {

		btDashboard =
				criarBotao("Dashboard");

		btUsuarios =
				criarBotao("Usuários");

		btPropostas =
				criarBotao("Propostas");

		btContratos =
				criarBotao("Contratos");

		btIngressos =
				criarBotao("Ingressos");

		btPresenca =
				criarBotao("Presença");

		btRelatorios =
				criarBotao("Relatórios");

		btConfiguracoes =
				criarBotao("Configurações");

		btLogout =
				criarBotao("Logout");
	}

	private JButton criarBotao(
			String texto) {

		JButton botao =
				new JButton(texto);

		botao.setFocusPainted(false);

		botao.setHorizontalAlignment(
				SwingConstants.LEFT);

		botao.setFont(
				new Font(
						"Segoe UI",
						Font.PLAIN,
						15));

		botao.setMaximumSize(
				new Dimension(
						Integer.MAX_VALUE,
						45));

		botao.setAlignmentX(
				Component.LEFT_ALIGNMENT);

		botao.setBackground(
				COR_PADRAO);

		botao.setOpaque(true);

		botao.setContentAreaFilled(true);

		botao.setBorder(
				BorderFactory.createEmptyBorder(
						0,
						15,
						0,
						0));

		return botao;
	}
	private void adicionarComponentes() {

		JLabel lblMenu =
				new JLabel("MENU");

		lblMenu.setFont(
				new Font(
						"Segoe UI",
						Font.BOLD,
						12));

		lblMenu.setForeground(
				Color.GRAY);

		lblMenu.setAlignmentX(
				Component.LEFT_ALIGNMENT);

		add(lblMenu);

		add(Box.createVerticalStrut(15));

		add(btDashboard);
		add(btUsuarios);
		add(btPropostas);
		add(btContratos);
		add(btIngressos);
		add(btPresenca);
		add(btRelatorios);
		add(btConfiguracoes);

		add(Box.createVerticalGlue());

		add(btLogout);
	}

	public void selecionarBotao(
			JButton botao) {

		if (botaoSelecionado != null) {

			botaoSelecionado.setBackground(
					COR_PADRAO);

			botaoSelecionado.setBorder(
					BorderFactory.createEmptyBorder(
							0,
							15,
							0,
							0));

			botaoSelecionado.setFont(
					new Font(
							"Segoe UI",
							Font.PLAIN,
							15));
		}

		botaoSelecionado = botao;

		botaoSelecionado.setBackground(
				COR_SELECIONADO);

		botaoSelecionado.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createMatteBorder(
								0,
								4,
								0,
								0,
								COR_BARRA),
						BorderFactory.createEmptyBorder(
								0,
								11,
								0,
								0)));

		botaoSelecionado.setFont(
				new Font(
						"Segoe UI",
						Font.BOLD,
						15));

		repaint();
	}

	public JButton getBtDashboard() {
		return btDashboard;
	}

	public JButton getBtUsuarios() {
		return btUsuarios;
	}

	public JButton getBtPropostas() {
		return btPropostas;
	}

	public JButton getBtContratos() {
		return btContratos;
	}

	public JButton getBtIngressos() {
		return btIngressos;
	}

	public JButton getBtPresenca() {
		return btPresenca;
	}

	public JButton getBtRelatorios() {
		return btRelatorios;
	}

	public JButton getBtConfiguracoes() {
		return btConfiguracoes;
	}

	public JButton getBtLogout() {
		return btLogout;
	}
}