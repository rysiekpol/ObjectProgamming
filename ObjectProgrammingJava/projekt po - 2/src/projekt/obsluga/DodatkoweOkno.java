package projekt.obsluga;

import projekt.Organizm;
import projekt.swiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class DodatkoweOkno {
    private static final String[] organizmyNazwy = {"Wilk", "Owca", "Lis", "Antylopa", "Zolw", "Trawa", "Mlecz", "Jagody", "Guarana", "Barszcz"};
    JFrame ukryte = new JFrame();
    JPopupMenu menu = new JPopupMenu();

    DodatkoweOkno(int x, int y, Swiat swiat, Obsluga_okienka o){
        ukryte.getContentPane().setBackground(new Color(173, 137, 0));
        ukryte.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ukryte.setLocationRelativeTo(null);
        ukryte.setLayout(new FlowLayout());
        ukryte.setAlwaysOnTop(true);
        JComboBox<String> comboBox = new JComboBox<>(organizmyNazwy);
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
               swiatOrganizmow[x][y] = new UstawiaczOrganizmow().klonowanie((String) Objects.requireNonNull(comboBox.getSelectedItem()), swiat, x, y);
               swiat.dodajOrganizm(swiatOrganizmow[x][y], false);
               o.getPanelZeSwiatem().removeAll();
               o.getPanelZKomentarzem().removeAll();
               o.UtworzOkno();
               ukryte.setVisible(false);
               ukryte.dispose();
           }
        });
        ukryte.add(comboBox);
        ukryte.add(button);
        ukryte.pack();
        ukryte.setVisible(true);
    }
}
