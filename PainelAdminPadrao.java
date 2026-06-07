package admin.paineis;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PainelAdminPadrao extends JPanel {

    protected JLabel lblTitulo;

    public PainelAdminPadrao(String titulo) {

        setLayout(new BorderLayout());

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15));

        lblTitulo = new JLabel(titulo);

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        add(
                lblTitulo,
                BorderLayout.NORTH);
    }
}