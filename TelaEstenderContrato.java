package telas;

import CentralDeInformacoes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaEstenderContrato extends JFrame {
    private CentralDeInformacoes central;
    private JTextField txtIdProposta;
    private JTextField txtNovaData;
    private JButton btnEstender;

    public TelaEstenderContrato(CentralDeInformacoes central) {
        this.central = central;
        setTitle("Estender Contrato");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("ID da Proposta:"));
        txtIdProposta = new JTextField();
        add(txtIdProposta);

        add(new JLabel("Nova Data (dd/MM/yyyy):"));
        txtNovaData = new JTextField();
        add(txtNovaData);

        add(new JLabel(""));
        btnEstender = new JButton("Confirmar");
        add(btnEstender);

        btnEstender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(txtIdProposta.getText().trim());
                    LocalDate novaData = LocalDate.parse(txtNovaData.getText().trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String resultado = central.estenderContrato(id, novaData);

                    if (resultado.startsWith("SUCESSO")) {
                        JOptionPane.showMessageDialog(null, resultado, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, resultado, "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}