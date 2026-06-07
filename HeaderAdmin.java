package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.Administrador;
import componentes.HeaderPadrao;

public class HeaderAdmin extends HeaderPadrao {

    public HeaderAdmin(Administrador admin) {

        setLayout(new BorderLayout());

        JPanel painelEsquerdo = new JPanel();

        JLabel titulo = new JLabel(
                "Sistema de Gestão de Teatros");

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));
        setBorder(
        	    BorderFactory.createCompoundBorder(
        	        BorderFactory.createMatteBorder(
        	            0,
        	            0,
        	            1,
        	            0,
        	            new Color(220,220,220)
        	        ),
        	        BorderFactory.createEmptyBorder(
        	            15,
        	            20,
        	            15,
        	            20
        	        )
        	    )
        	);

        setBackground(Color.WHITE);

        painelEsquerdo.setOpaque(false);

        titulo.setFont(
            new Font("Segoe UI", Font.BOLD, 26)
        );

        JLabel subtitulo =
            new JLabel("Administração do Teatro");

        subtitulo.setFont(
            new Font("Segoe UI", Font.PLAIN, 14)
        );

        painelEsquerdo.setLayout(
            new BoxLayout(
                painelEsquerdo,
                BoxLayout.Y_AXIS
            )
        );

        painelEsquerdo.add(titulo);
        painelEsquerdo.add(subtitulo);
        
    }
}