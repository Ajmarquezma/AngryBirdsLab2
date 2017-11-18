/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Juan Esteban Muñoz, Mateo Pacheco, Luis Fernando Suarez, Aldo Marquez
 */
public class AngryBird extends Base {
    private boolean v;
    public AngryBird(int x, int y) {
        super(x, y);
        this.v=false;
    }
    @Override
    public int vy(int v,int t){
        double vv=v;
        vv+=(t*0.1);
        v=intValue(vv);
        return v;
    }
    @Override
    public int y(int y, int v,int t){
        double yy=y;
        yy+=(v+((t*t)*0.1)/2);
        y=intValue(yy);
        return y;
    }
    @Override
    public int getY(){
        return ybalon;
    }
    @Override
    public int getX(){
        return xbalon;
    }
    @Override
    public int getVY(){
        return yvelocidad;
    }
    @Override
    public int getVX(){
        return xvelocidad;
    }
    @Override
    public int getTime(){
        return tiempo;
    }
    @Override
    public void setTime(int a){
        tiempo=a;
    }
    @Override
    public void setY(int a){
        ybalon=a;
    }
    @Override
    public void setX(int a){
        xbalon=a;
    }
    @Override
    public void setVY(int a){
        yvelocidad=a;
    }
    @Override
    public void setVX(int a){
        xvelocidad=a;
    }
    public boolean getFly(){
        return v;
    }
    public void setFly(boolean a){
        v=a;
    }
}
