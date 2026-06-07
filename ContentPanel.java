package componentes;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {

    private CardLayout cardLayout;

    public ContentPanel() {

        cardLayout = new CardLayout();

        setLayout(cardLayout);

        configurarVisual();
    }

    private void configurarVisual() {

        setBackground(Color.WHITE);

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20));
    }

    public void adicionarTela(
            String nome,
            JPanel painel) {

        painel.setBackground(Color.WHITE);

        add(painel, nome);
    }

    public void mostrarTela(String nome) {

        cardLayout.show(this, nome);
    }
}