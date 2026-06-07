package admin.paineis;

import javax.swing.*;
import java.awt.*;
import controle.CentralDeInformacoes;
import controle.Persistencia;
import classes.Pessoa;
import telas.TelaCadastro;

public class UsuariosPanel extends JPanel {

    private DefaultListModel<String> modeloLista;
    private JList<String> listaUsuarios;

    public UsuariosPanel() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gerenciamento de Usuários");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JButton btNovoUsuario = new JButton("Novo Usuário");
        JButton btAtualizarLista = new JButton("Atualizar Lista");

        JPanel topo = new JPanel(new BorderLayout());
        topo.add(titulo, BorderLayout.WEST);

        JPanel botoes = new JPanel();
        botoes.add(btNovoUsuario);
        botoes.add(btAtualizarLista);

        topo.add(botoes, BorderLayout.EAST);

        add(topo, BorderLayout.NORTH);

        // Lista de usuários
        modeloLista = new DefaultListModel<>();
        listaUsuarios = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaUsuarios);
        scroll.setBorder(BorderFactory.createTitledBorder("Usuários cadastrados"));

        add(scroll, BorderLayout.CENTER);

        // 🔑 Botão "Novo Usuário" abre a TelaCadastro
        btNovoUsuario.addActionListener(e -> {
            new TelaCadastro(); // reaproveita a tela já criada
        });

        // 🔑 Botão "Atualizar Lista"
        btAtualizarLista.addActionListener(e -> atualizarListaUsuarios());

        // Carrega lista inicial
        atualizarListaUsuarios();
    }

    private void atualizarListaUsuarios() {
        modeloLista.clear();
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral("dados.xml");

        for (Pessoa p : central.getTodasAsPessoas()) {
            modeloLista.addElement(p.getNome() + " - " + p.getCargo() + " - CPF: " + p.getCPF());
        }
    }
}
