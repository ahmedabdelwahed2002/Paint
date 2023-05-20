/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7FrontEnd;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.lang.Math.abs;

/**
 *
 * @author AhmedAbdelwahed
 */
public class Rectangle extends AbstractShape {

    private int length;
    private int width;
    private int deltaX;
    private int deltaY;
    private Point SP1;
    private Point SP2;
    private Point SP3;
    private Point SP4;
    private Point resizingPoint;

    public Point getSP1() {
        return SP1;
    }

    public Point getSP2() {
        return SP2;
    }

    public Point getSP3() {
        return SP3;
    }

    public Point getSP4() {
        return SP4;
    }

    public void setSP1(Point SP1) {
        this.SP1 = SP1;
    }

    public void setSP2(Point SP2) {
        this.SP2 = SP2;
    }

    public void setSP3(Point SP3) {
        this.SP3 = SP3;
    }

    public void setSP4(Point SP4) {
        this.SP4 = SP4;
    }

    //private Point position;
    public Rectangle(Point position, int length, int width) {
        super(position);
        this.length = length;
        this.width = width;
        this.SP1 = position;
        this.SP2 = new Point(SP1.x + width, SP1.y);
        this.SP3 = new Point(SP2.x, SP2.y + length);
        this.SP4 = new Point(SP1.x, SP1.y + length);
        this.setShapeName("Rectangle_");
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void draw(Graphics canvas) {
        if (super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillRect(super.getPosition().x, super.getPosition().y, width, length);
        }
        canvas.setColor(super.getColor());
        canvas.drawRect(super.getPosition().x, super.getPosition().y, width, length);
    }

    @Override
    public boolean contains(Point point) {
        int positionX = this.getPosition().x;
        int positionY = this.getPosition().y;
        if (point.x <= (positionX + this.width) && point.x >= positionX && point.y <= (positionY + this.length) && point.y >= positionY) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param point
     */
    @Override
    public void setDraggingPoint(Point newp) {
        super.setDraggingPoint(newp);
        deltaX = newp.x - getPosition().x;
        deltaY = newp.y - getPosition().y;
    }

    @Override
    public void moveTo(Point newp) {
        Point temp = new Point(newp.x - this.deltaX, newp.y - this.deltaY);
        this.setPosition(temp);
        this.SP1 = new Point(temp.x, temp.y);
        this.SP2 = new Point(SP1.x + width, SP1.y);
        this.SP3 = new Point(SP2.x, SP2.y + length);
        this.SP4 = new Point(SP1.x, SP1.y + length);
    }

    @Override
    public Rectangle copy() {
        Point positionC = this.getPosition();
        int lengthC = this.getLength();
        int widthC = this.getWidth();
        Rectangle r = new Rectangle(positionC, lengthC, widthC);
        r.setFillColor(this.getFillColor());
        r.setColor(this.getColor());
        return r;
    }

    public void drawHandles(Graphics2D g) {

        g.fillRect(SP1.x - 4, SP1.y - 4, 8, 8);
        g.fillRect(SP2.x - 4, SP2.y - 4, 8, 8);
        g.fillRect(SP3.x - 4, SP3.y - 4, 8, 8);
        g.fillRect(SP4.x - 4, SP4.y - 4, 8, 8);

    }

    @Override
    public Point setResizingPoint(Point point) {
        int positionXSP1 = this.getPosition().x - 4;
        int positionYSP1 = this.getPosition().y - 4;
        int positionXSP2 = (this.getPosition().x - 4) + this.width;
        int positionYSP2 = this.getPosition().y - 4;
        int positionXSP3 = (this.getPosition().x-4)+this.width;
        int positionYSP3 = (this.getPosition().y-4)+this.length;
        int positionXSP4 = this.getPosition().x-4;
        int positionYSP4 = (this.getPosition().y-4)+this.length;
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
        return null;
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
            this.resizingPoint = SP1;
            SP2.y = newp.y;
            SP4.x = newp.x;
            this.setPosition(SP1);
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));
        }
        if (resizingPoint == SP2 && newp.x > SP1.x && newp.y < SP3.y) {
            System.out.println("howa SP2 !!!");
            SP2 = newp;
            this.resizingPoint = SP2;
            SP1.y = newp.y;
            SP3.x = newp.x;
            this.setPosition(SP1);
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));

        }
        if (resizingPoint == SP3 && newp.x > SP1.x && newp.y > SP2.y) {
            System.out.println("howa SP3 !!!");
            SP3 = newp;
            this.resizingPoint = SP3;
            SP4.y = newp.y;
            SP2.x = newp.x;
            this.setPosition(SP1);
            this.setWidth(abs(SP2.x - SP1.x));
            this.setLength(abs(SP4.y - SP1.y));

        }
        if (resizingPoint == SP4 && newp.x < SP2.x && newp.y > SP2.y) {
            System.out.println("howa SP4 !!!");
            SP4 = newp;
            this.resizingPoint = SP4;
            SP3.y = SP4.y;
            SP1.x = SP4.x;
            this.setPosition(SP1);
            this.setWidth(abs(SP3.x - SP4.x));
            this.setLength(abs(SP1.y - SP4.y));
        }

    }

}
