/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Controlador.*;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Juan Esteban Muñoz, Mateo Pacheco, Luis Fernando Suarez, Aldo Marquez
 */
public abstract class Base {
    protected int yvelocidad;
    protected int xvelocidad;
    protected int velocidad;
    protected int tiempo;
    protected int ybalon;
    protected int xbalon;
    
    public Base(int x, int y){
        this.ybalon=y;
        this.xbalon=x;
        this.yvelocidad=0;
        this.xvelocidad=0;
        this.tiempo=0;
    }
    public abstract int vy(int v,int t);
    public abstract int y(int y, int v,int t);
    public abstract int getY();
    public abstract int getX();
    public abstract int getVY();
    public abstract int getVX();
    public abstract int getTime();
    public abstract void setTime(int a);
    public abstract void setY(int a);
    public abstract void setX(int a);
    public abstract void setVY(int a);
    public abstract void setVX(int a);
}
