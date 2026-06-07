package componentes;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class DashboardPadrao extends JFrame {

    protected HeaderPadrao header;
    protected SidebarPadrao sidebar;
    protected ContentPanel contentPanel;

    public DashboardPadrao(
            HeaderPadrao header,
            SidebarPadrao sidebar) {

        this.header = header;
        this.sidebar = sidebar;

        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        contentPanel = new ContentPanel();

        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    public SidebarPadrao getSidebar() {
        return sidebar;
    }

    public HeaderPadrao getHeader() {
        return header;
    }
}