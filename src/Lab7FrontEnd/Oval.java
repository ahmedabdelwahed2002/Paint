/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7FrontEnd;

/**
 *
 * @author AhmedAbdelwahed
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.lang.Math.abs;
import javax.swing.text.Position;

public class Oval extends AbstractShape {

    private int Vradius;
    private int Hradius;
    private int deltaX;
    private int deltaY;
    private Point SP1;
    private Point SP2;
    private Point SP3;
    private Point SP4;
    private Point resizingPoint;
    private int width;
    private int length;

    public void setWidth(int width) {
        this.width = width;
        this.Hradius = width/2;
    }

    public void setLength(int length) {
        this.length = length;
        this.Vradius = length/2;
    }

    public Oval(Point position,int Vradius, int Hradius) {
        super(position);
        this.Vradius = Vradius;
        this.Hradius = Hradius;
        this.SP1 = new Point(position.x - this.Hradius, position.y - this.Vradius);
        this.SP2 = new Point(SP1.x + 2*this.Hradius, SP1.y);
        this.SP3 = new Point(SP2.x, SP2.y + 2*this.Vradius);
        this.SP4 = new Point(SP1.x, SP1.y + 2*this.Vradius);
        this.width = this.Hradius*2;
        this.length = this.length*2;
        this.setShapeName("Oval_");
    }

    
    
    public void setVRadius(int Vradius) {
        this.Vradius = Vradius;
    }

    public void setHradius(int Hradius) {
        this.Hradius = Hradius;
    }
    
    public int getVRadius() {
        return Vradius;
    }

    public int getHradius() {
        return Hradius;
    }
    
    

    @Override
    public void draw(Graphics canvas) {
        if (super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillOval(super.getPosition().x - Hradius, super.getPosition().y - Vradius, Hradius * 2, Vradius * 2);
        }
        canvas.setColor(super.getColor());
        canvas.drawOval(super.getPosition().x - Hradius, super.getPosition().y - Vradius, Hradius * 2, Vradius * 2);
    }
    
    @Override
    public boolean contains (Point point)
    {
        int h = super.getPosition().x;
        int k = this.getPosition().y;
        int xP = point.x;
        int yP = point.y;
         double p = ((double)Math.pow((xP - h), 2)
                    / (double)Math.pow(Hradius, 2))
                   + ((double)Math.pow((yP - k), 2)
                      / (double)Math.pow(Vradius, 2));
        if(p<=1)
            return true;
        return false;
        //if(Math.sqrt(Math.pow((double)(xP - centerX), 2.0)+ Math.pow((double)(yP-centerY),2.0)) <= (double)this.Vradius)
        //    return true;
        //return false;
    }
    @Override
    public void setDraggingPoint (Point newp){
        super.setDraggingPoint(newp);
        deltaX = this.getDraggingPoint().x - this.getPosition().x;
        deltaY = this.getDraggingPoint().y - this.getPosition().y;
    }

    @Override
    public void moveTo(Point newp) {
        Point temp = new Point(newp.x - this.deltaX,newp.y - this.deltaY);
        this.setPosition(temp);
        this.SP1 = new Point(temp.x - this.Hradius, temp.y - this.Vradius);
        this.SP2 = new Point(SP1.x + 2*this.Hradius, SP1.y);
        this.SP3 = new Point(SP2.x, SP2.y + 2*this.Vradius);
        this.SP4 = new Point(SP1.x, SP1.y + 2*this.Vradius);
    }
    
    @Override
    public Oval copy(){
        Point pointC = this.getPosition();
        int vRadiusC = this.Vradius;
        int hRadiusC = this.Hradius;
        Oval o = new Oval(pointC,vRadiusC,hRadiusC);
        o.setFillColor(this.getFillColor());
        o.setColor(this.getColor());
        return o;
    }

    @Override
    public void resize(Point newp) {
        int resizeX;
        int resizeY;
        resizeX = (newp.x - resizingPoint.x);
        resizeY = (newp.y - resizingPoint.y);
        if (this.resizingPoint == SP1 && newp.x < SP2.x && newp.y < SP3.y) {
            System.out.println("howa SP1 !!!");
            SP1 = newp;
            this.resizingPoint = newp;
            SP2.y = newp.y;
            SP4.x = newp.x;
            this.setPosition(new Point(SP1.x+Hradius,SP1.y+Vradius));
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));
        }
        if (resizingPoint == SP2 && newp.x > SP1.x && newp.y < SP3.y) {
            System.out.println("howa SP2 !!!");
            SP2 = newp;
            this.resizingPoint = SP2;
            SP1.y = newp.y;
            SP3.x = newp.x;
            this.setPosition(new Point(SP1.x+Hradius,SP1.y+Vradius));
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));

        }
        if (resizingPoint == SP3 && newp.x > SP1.x && newp.y > SP2.y) {
            System.out.println("howa SP3 !!!");
            SP3 = newp;
            this.resizingPoint = SP3;
            SP4.y = newp.y;
            SP2.x = newp.x;
            this.setPosition(new Point(SP1.x+Hradius,SP1.y+Vradius));
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));

        }
        if (resizingPoint == SP4 && newp.x < SP2.x && newp.y > SP2.y) {
            System.out.println("howa SP4 !!!");
            SP4 = newp;
            this.resizingPoint = SP4;
            SP3.y = SP4.y;
            SP1.x = SP4.x;
            this.setPosition(new Point(SP1.x+Hradius,SP1.y+Vradius));
            this.setWidth(abs(SP3.x - SP4.x));
            this.setLength(abs(SP1.y - SP4.y));
        }
 }

    @Override
    public void drawHandles(Graphics2D g) {
        g.fillRect(SP1.x - 4, SP1.y - 4, 8, 8);
        g.fillRect(SP2.x - 4, SP2.y - 4, 8, 8);
        g.fillRect(SP3.x - 4, SP3.y - 4, 8, 8);
        g.fillRect(SP4.x - 4, SP4.y - 4, 8, 8);
    }

    @Override
    public Point setResizingPoint(Point point) {
         int positionXSP1 = this.SP1.x - 4;
        int positionYSP1 = this.SP1.y - 4;
        int positionXSP2 = (this.SP1.x - 4) + this.width;
        int positionYSP2 = this.SP1.y - 4;
        int positionXSP3 = (this.SP1.x-4)+this.width;
        int positionYSP3 = (this.SP1.y-4)+this.length;
        int positionXSP4 = this.SP1.x-4;
        int positionYSP4 = (this.SP1.y-4)+this.length;
        if (point.x <= (positionXSP1 + 8) && point.x >= positionXSP1 && point.y <= (positionYSP1 + 8) && point.y >= positionYSP1) {
            this.resizingPoint = SP1;
            return SP1;
        }
        if (point.x <= (positionXSP2 + 8) && point.x >= positionXSP2 && point.y <= (positionYSP2 + 8) && point.y >= positionYSP2) {
            this.resizingPoint = SP2;
            return SP2;
        }
        if (point.x <= (positionXSP3 + 8) && point.x >= positionXSP3 && point.y <= (positionYSP3 + 8) && point.y >= positionYSP3) {
            this.resizingPoint = SP3;
            return SP3;
        }
        if (point.x <= (positionXSP4 + 8) && point.x >= positionXSP4 && point.y <= (positionYSP4 + 8) && point.y >= positionYSP4) {
            this.resizingPoint = SP4;
            return SP4;
        }
        return null; }
    
}
