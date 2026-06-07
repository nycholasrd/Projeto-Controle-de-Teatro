package componentes;

import java.awt.Dimension;
import javax.swing.JPanel;

public abstract class SidebarPadrao extends JPanel {

    public SidebarPadrao() {

        setPreferredSize(
                new Dimension(200, 700));
    }
}