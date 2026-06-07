package componentes;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.DataUtil;

public abstract class HeaderPadrao extends JPanel {

    public HeaderPadrao() {
        setPreferredSize(new Dimension(1200, 90));
    }

    protected JPanel criarPainelUsuario(
            String nome,
            String cargo) {

        JPanel painelUsuario = new JPanel();

        painelUsuario.setLayout(
                new BoxLayout(
                        painelUsuario,
                        BoxLayout.Y_AXIS));

        JLabel lblNome =
                new JLabel("Usuário: " + nome);

        JLabel lblCargo =
                new JLabel("Cargo: " + cargo);

        JLabel lblData =
                new JLabel("Data: " +
                        DataUtil.getDataAtual());

        painelUsuario.add(lblNome);
        painelUsuario.add(lblCargo);
        painelUsuario.add(lblData);

        return painelUsuario;
    }
}