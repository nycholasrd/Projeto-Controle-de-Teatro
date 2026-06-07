package admin.componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardDashboard extends JPanel {

    private final Color COR_NORMAL = Color.WHITE;
    private final Color COR_HOVER = new Color(245,245,245);

    public CardDashboard(
            String titulo,
            String descricao,
            Runnable acao) {

        configurarPainel();

        JLabel lblTitulo =
                new JLabel(titulo);

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        JLabel lblDescricao =
                new JLabel(descricao);

        lblDescricao.setForeground(Color.GRAY);

        add(lblTitulo, BorderLayout.NORTH);
        add(lblDescricao, BorderLayout.CENTER);

        configurarHover();
        
        addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e) {

                        acao.run();
                    }

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        setBackground(COR_HOVER);
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        setBackground(COR_NORMAL);
                    }
                });
    }

    private void configurarPainel() {

        setLayout(new BorderLayout());

        setBackground(COR_NORMAL);

        setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR));

        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220)),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));
    }

    private void configurarHover() {

        addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        setBackground(
                                COR_HOVER);
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        setBackground(
                                COR_NORMAL);
                    }
                });
    }
}