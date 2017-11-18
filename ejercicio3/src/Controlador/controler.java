/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Logica.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import javax.swing.*;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Juan Esteban Muñoz, Mateo Pacheco, Luis Fernando Suarez, Aldo Marquez
 */
public class controler extends JPanel implements ActionListener {

    private Timer timer;
    private AngryBird[] pr;
    private Pig cerdo;
    private int floor;
    private int npajaros;
    private int puntaje;
    private Resortera h;
    private int fMonster = 0;
    private int bat = 0;
    private int contador;

    public controler() {
        this.timer = new Timer(100, this);
        this.timer.start();
        npajaros = 4;
        this.puntaje = 0;
        this.pr = new AngryBird[5];
        for (int i = 0; i < 5; i++) {
            pr[i] = new AngryBird(20 * i, 380);
        }
        this.h = new Resortera();
        this.floor = 400;
        setFocusable(true);
        addKeyListener(new EventosTeclado());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image[] p = new Image[27];
        for (int i = 0; i < 26; i++) {
            Image monster = loadImage("og_" + String.valueOf(i) + ".png");
            p[i] = monster;
        }
        Image backTrees = loadImage("parallax-forest-back-trees.png");
        Image lights = loadImage("parallax-forest-lights.png");
        Image middleTrees = loadImage("parallax-forest-middle-trees.png");
        Image frontTrees = loadImage("parallax-forest-front-trees.png");
        g.drawImage(backTrees, 0, 0, 1000, 500, this);
        g.drawImage(lights, 0, 0, 1000, 500, this);
        g.drawImage(middleTrees, 0, 0, 1000, 500, this);
        g.drawImage(frontTrees, 0, 0, 1000, 500, this);
        Image sling = loadImage("5.png");
        g.drawImage(sling, 100, (400 - 125), 135, 405, 0, 38, 35, 125, this);
        Image birds = loadImage("4.png");

        Image[] ba = new Image[13];
        for (int i = 0; i < 12; i++) {
            Image bat = loadImage("__Bat02_Idle_0" + String.valueOf(i) + ".png");
            ba[i] = bat;
        }

        g.drawImage(ba[bat], pr[this.npajaros].getX() - 8, pr[this.npajaros].getY() - 7, 50, 50, this);
        g.drawImage(p[this.fMonster], 650, 348, 305 / 5, 265 / 5, this);
        g.drawLine(0, 400, 1025, 400);
        int grad = h.getAngulo();
        double rad = Math.toRadians(grad);
        int a = intValue(h.getVI() * cos(rad));
        int b = intValue(h.getVI() * sin(rad));
        g.drawLine(120, 290, 120 - a, 290 - b);
        g.drawLine(121, 291, 121 - a, 291 - b);
        g.drawLine(119, 289, 119 - a, 289 - b);
        g.drawLine(122, 292, 122 - a, 292 - b);
        g.drawLine(118, 288, 118 - a, 288 - b);
        g.drawRect(pr[this.npajaros].getX(), pr[this.npajaros].getY(), 30, 30);
        g.drawString("PUNTAJE:" + this.puntaje, 10, 40);

    }

    public int getN() {
        return npajaros;
    }

    public void setN(int n) {
        this.npajaros = n;
    }

    public AngryBird[] getP() {
        return pr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle pajaro = new Rectangle(pr[this.npajaros].getX(), pr[this.npajaros].getY(), 30, 30);
        Rectangle cerdo = new Rectangle(650, 350, 50, 50);
        int by = pr[npajaros].getY();
        int bx = pr[npajaros].getX();
        int bvy = pr[npajaros].getVY();
        int bvx = pr[npajaros].getVX();
        int bt = pr[npajaros].getTime();

        if (this.fMonster < 25) {
            this.fMonster++;
        } else if (this.fMonster >= 25) {
            this.fMonster = 0;
        }

        if (this.bat < 11) {
            this.bat++;
        } else if (this.bat >= 11) {
            this.bat = 0;
        }

        if ((pr[npajaros].getY() <= (floor - 30)) && (pr[npajaros].getVY() < 0) && (pr[npajaros].getFly() == true)) {
            bvy = pr[npajaros].vy(bvy, bt);
            by = pr[npajaros].y(by, bvy, bt);
            bt++;
            bx += bvx;
            pr[npajaros].setX(bx);
            pr[npajaros].setTime(bt);
            pr[npajaros].setVY(bvy);
            pr[npajaros].setY(by);
        } else if (((pr[npajaros].getY() < (floor - 30)) && (pr[npajaros].getVY() >= 0)) && (pr[npajaros].getFly() == true)) {
            bx += bvx;
            pr[npajaros].setX(bx);
            bvy = pr[npajaros].vy(bvy, bt);
            by = pr[npajaros].y(by, bvy, bt);
            bt++;
            pr[npajaros].setTime(bt);
            pr[npajaros].setVY(bvy);
            pr[npajaros].setY(by);
        }
        if (pr[npajaros].getY() > floor - 30 && (pr[npajaros].getFly() == true)) {
            pr[npajaros].setY(floor - 30);
            pr[npajaros].setTime(0);
            npajaros = npajaros - 1;
            System.out.println("A");
        }
        if (this.npajaros < 0) {
            JOptionPane.showMessageDialog(this, "Game Over");
            int choice = JOptionPane.showConfirmDialog(null, "Reiniciar Juego?");
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < 5; i++) {
                    pr[i].setX(20 * i);
                    pr[i].setY(380);
                    pr[i].setVX(0);
                    pr[i].setVY(0);
                    pr[i].setTime(0);
                    pr[i].setFly(false);
                }
                npajaros = 4;
            } else {
                this.timer.stop();
                npajaros = 0;
            }
        }
        if (cerdo.intersects(pajaro)) {
            this.puntaje += 1;
            npajaros = npajaros - 1;
        }
        repaint();
    }

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                int a = h.getVI();
                if (h.getVI() >= 90) {
                    a = 90;
                    h.setVI(a);
                } else {
                    a++;
                    h.setVI(a);
                }
                int grad = h.getAngulo();
                double rad = Math.toRadians(grad);
                int c = intValue(h.getVI() * cos(rad));
                int b = intValue(h.getVI() * sin(rad));
                pr[npajaros].setX(120 - c);
                pr[npajaros].setY(260 - b);
            }
            if (key == KeyEvent.VK_RIGHT) {
                int a = h.getVI();
                if (h.getVI() <= 0) {
                    a = 0;
                    h.setVI(a);
                } else {
                    a--;
                    h.setVI(a);
                }
                int grad = h.getAngulo();
                double rad = Math.toRadians(grad);
                int c = intValue(h.getVI() * cos(rad));
                int b = intValue(h.getVI() * sin(rad));
                pr[npajaros].setX(120 - c);
                pr[npajaros].setY(260 - b);
                pr[npajaros].setVY(0);
                pr[npajaros].setTime(0);
            }
            if (key == KeyEvent.VK_UP) {
                int a = h.getAngulo();
                if (h.getAngulo() >= 90) {
                    a = 90;
                    h.setAngulo(a);
                } else {
                    a++;
                    h.setAngulo(a);
                }
                int grad = h.getAngulo();
                double rad = Math.toRadians(grad);
                int c = intValue(h.getVI() * cos(rad));
                int b = intValue(h.getVI() / 2 * sin(rad));
                pr[npajaros].setX(120 - c);
                pr[npajaros].setY(260 - b);
                pr[npajaros].setVY(0);
                pr[npajaros].setTime(0);
            }
            if (key == KeyEvent.VK_DOWN) {
                int a = h.getAngulo();
                if (h.getAngulo() <= -90) {
                    a = -90;
                    h.setAngulo(a);
                } else {
                    a--;
                    h.setAngulo(a);
                }
                int grad = h.getAngulo();
                double rad = Math.toRadians(grad);
                int c = intValue(h.getVI() * cos(rad));
                int b = intValue(h.getVI() * sin(rad));
                pr[npajaros].setX(120 - c);
                pr[npajaros].setY(260 - b);
                pr[npajaros].setVY(0);
                pr[npajaros].setTime(0);
                puntaje = 0;
            }
            if (key == KeyEvent.VK_SPACE) {
                int grad = h.getAngulo();
                double rad = Math.toRadians(grad);
                int a = intValue((h.getVI() / 2) * cos(rad));
                int b = intValue((h.getVI() / 2) * sin(rad));
                h.setAngulo(0);
                h.setVI(0);
                pr[npajaros].setX(120);
                pr[npajaros].setY(290);
                pr[npajaros].setVX(a);
                pr[npajaros].setVY(b);
                pr[npajaros].setFly(true);

            }
        }
    }

    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
