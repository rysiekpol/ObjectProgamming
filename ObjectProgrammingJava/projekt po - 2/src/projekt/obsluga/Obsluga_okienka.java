package projekt.obsluga;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.ObjLongConsumer;

import projekt.Organizm;
import projekt.swiat.Swiat;
public class Obsluga_okienka implements MouseListener, KeyListener {
    private static final String[] organizmyNazwy = {"Wilk", "Owca", "Lis", "Antylopa", "Zolw", "Trawa", "Mlecz", "Jagody", "Guarana", "Barszcz"};
    private static final int UP = 0;
    private static final int LEFT = 1;
    private static final int DOWN = 2;
    private static final int RIGHT = 3;
    private static final int SPACE = 4;
    private Swiat swiat;
    private JFrame startowe;
    private JButton[][] przyciski;
    private final JPanel panelZeSwiatem = new JPanel();
    private final JPanel panelZKomentarzem = new JPanel();

    public Obsluga_okienka(Swiat swiat){
        this.swiat = swiat;
    }

    public Obsluga_okienka(){
        this.swiat = new Swiat();
    }

    public JPanel getPanelZeSwiatem(){
        return panelZeSwiatem;
    }

    public JPanel getPanelZKomentarzem(){
        return panelZeSwiatem;
    }

    public JFrame getStartowe(){
        return startowe;
    }

    public void OknoStartowe(){
        startowe = new JFrame();
        startowe.addKeyListener(this);
        startowe.setTitle("Maciej Szefler s188614");
        startowe.getContentPane().setBackground(Color.lightGray);
        startowe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startowe.setSize(1000,600);
        startowe.setLayout(null);
        startowe.setResizable(false);
        startowe.setLocationRelativeTo(null);
        JMenuBar pasekMenu = new JMenuBar();
        JMenu opcje = new JMenu("Opcje");

        JMenuItem nowySwiat = new JMenuItem("Nowy");
        JMenuItem wczytajSwiat = new JMenuItem("Wczytaj");
        JMenuItem zapiszSwiat = new JMenuItem("Zapisz");
        JMenuItem wyjdz = new JMenuItem("Wyjdz");

        nowySwiat.addActionListener(e->nowySwiat());
        wczytajSwiat.addActionListener(e->wczytajSwiat());
        zapiszSwiat.addActionListener(e->zapiszSwiat());
        wyjdz.addActionListener(e->System.exit(0));

        opcje.add(nowySwiat);
        opcje.add(wczytajSwiat);
        opcje.add(zapiszSwiat);
        opcje.add(wyjdz);

        pasekMenu.add(opcje);
        startowe.setJMenuBar(pasekMenu);
        startowe.setVisible(true);
    }

    public void UtworzOkno(){
        Border border = new LineBorder(new Color(0, 0, 0), 8, false);
        panelZeSwiatem.setBounds(20,20, 400, 500);
        panelZeSwiatem.setBorder(border);
        Organizm[][] swiatOrganizmow = swiat.getSwiatOrganizmow();
        przyciski = new JButton[swiat.getSizeX()][swiat.getSizeY()];
        panelZeSwiatem.setLayout (new GridLayout(swiat.getSizeX(), swiat.getSizeY(),1,1));
        for(int i = 0; i<swiat.getSizeX(); i++) {
            for (int j = 0; j < swiat.getSizeY(); j++) {
                JButton button = new JButton();
                if (swiatOrganizmow[i][j] != null) {
                    button.setBackground(swiatOrganizmow[i][j].getKolor());
                } else {
                    button.setBackground(Color.WHITE);
                }
                przyciski[i][j] = button;
                button.addMouseListener(this);
                button.setOpaque(true);
                button.setBorderPainted(false);
                panelZeSwiatem.add(button);
            }
        }
        Vector<String> kom = swiat.getKomentarz();
        String info = "Tura: " + swiat.getKtoraTura() + "\n\n";
        if(kom.size()>0) {
            for (String s : kom) {
                if(!s.equals("")) {
                    info += s + "\n\n";
                }
            }
        }
        JTextArea komentarz = new JTextArea(info);
        JScrollPane scroll = new JScrollPane(komentarz, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        komentarz.setEditable(false);

        panelZKomentarzem.setBounds(440, 20, 520, 500);
        panelZKomentarzem.setBorder(border);
        panelZKomentarzem.setLayout(new BorderLayout());
        panelZKomentarzem.add(scroll);
        startowe.add(panelZeSwiatem);
        startowe.add(panelZKomentarzem);
        startowe.setVisible(true);
    }

    public void wezDaneDoNowegoSwiata(){
        String x = "0";
        String y = "0";
        do {
            x = JOptionPane.showInputDialog("Podaj rozmiar planszy X: ", 20);
            y = JOptionPane.showInputDialog("Podaj rozmiar planszy Y: ", 20);
            String ile = JOptionPane.showInputDialog("Podaj ilosc organizmow: ", 10);
            Swiat nowy = new Swiat(Integer.parseInt(x), Integer.parseInt(y));
            nowy.nowySwiat(Integer.parseInt(ile));
            swiat = null;
            swiat = nowy;
        }while (Integer.parseInt(x) > 50 || Integer.parseInt(y) > 50);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode ();
        if (keyCode==KeyEvent.VK_UP) {
            swiat.setOstatniKlawisz(UP);
        } else if(keyCode==KeyEvent.VK_DOWN) {
            swiat.setOstatniKlawisz(DOWN);
        } else if(keyCode==KeyEvent.VK_LEFT) {
            swiat.setOstatniKlawisz(LEFT);
        } else if(keyCode==KeyEvent.VK_RIGHT) {
            swiat.setOstatniKlawisz(RIGHT);
        } else if(keyCode==KeyEvent.VK_SPACE) {
            swiat.setOstatniKlawisz(SPACE);
        }else{
            swiat.setOstatniKlawisz(-1);
        }
        swiat.wykonajTure();
        panelZKomentarzem.removeAll();
        panelZeSwiatem.removeAll();
        UtworzOkno();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int row = 0; row < swiat.getSizeX(); row++) {
            for (int col = 0; col < swiat.getSizeY(); col++) {
                if (przyciski[row][col] == e.getSource()){
                    Color color = przyciski[row][col].getBackground();
                    if(color == Color.white) {
                        new DodatkoweOkno(row, col, swiat, this);
                    }
                    panelZeSwiatem.removeAll();
                    panelZKomentarzem.removeAll();
                    UtworzOkno();
                    break;
                };
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void zapiszSwiat(){
        String zapis = JOptionPane.showInputDialog("Podaj nazwe do zapisania pliku: ","save");
        try {
            swiat.zapiszSwiat(zapis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void wczytajSwiat(){
        String wczytaj = JOptionPane.showInputDialog("Podaj nazwe zapisanego pliku: ","save");
        String nazwa = wczytaj+".txt";
        Scanner wczytanyPlik = null;
        try {
            wczytanyPlik = new Scanner(new File(nazwa));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        assert wczytanyPlik != null;
        int x = wczytanyPlik.nextInt();
        int y = wczytanyPlik.nextInt();
        int ktoraTura = wczytanyPlik.nextInt();
        int ktoraTuraUmiejetnosci = wczytanyPlik.nextInt();
        int czyUmiejetnosc = wczytanyPlik.nextInt();
        boolean intToBool = true;
        if(czyUmiejetnosc == 0){
            intToBool = false;
        }
        Swiat nowy = new Swiat(x,y,ktoraTura,ktoraTuraUmiejetnosci,intToBool);
        swiat = null;
        swiat = nowy;
        try{
            swiat.wczytajSwiat(wczytanyPlik);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        startowe.setVisible(false);
        panelZeSwiatem.removeAll();
        panelZKomentarzem.removeAll();
        startowe.remove(panelZeSwiatem);
        startowe.remove(panelZKomentarzem);
        UtworzOkno();
    }

    private void nowySwiat(){
        wezDaneDoNowegoSwiata();
        startowe.setVisible(false);
        panelZeSwiatem.removeAll();
        panelZKomentarzem.removeAll();
        startowe.remove(panelZeSwiatem);
        startowe.remove(panelZKomentarzem);
        UtworzOkno();
    }

}
