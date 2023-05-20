/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7FrontEnd;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.lang.Math.floor;

/**
 *
 * @author lenovo
 */
public class Triangle extends AbstractShape{

    private Point point2;
    private Point point3;
    private int DX1;
    private int DY1;
    private int DX2;
    private int DY2;
    private int DX3;
    private int DY3;
    private Point resizingPoint;

    public Triangle(Point position,Point point2, Point point3) {
        super(position);
        this.point2 = point2;
        this.point3 = point3;
        this.setShapeName("Triangle_");
    }

    
    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }
    
    
    @Override
    public void draw(Graphics canvas) {
        int [] xPoints = {super.getPosition().x,point2.x,point3.x};
        int [] yPoints = {super.getPosition().y,point2.y,point3.y};
        
        if (super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillPolygon(xPoints, yPoints, 3);
        }
        canvas.setColor(super.getColor());
        canvas.drawPolygon(xPoints, yPoints, 3);

    }
    
     public double area_triangle(double side1, double side2, double side3) {
        double area = 0;
        double s = (side1 + side2 + side3)/2;
        area = Math.sqrt(s*(s - side1)*(s - side2)*(s - side3));
        return area;
    }
    
    // Area of triangle = (x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2))/2
    // Area is multiplied by 2 to avoid any floats
    private int doubleArea(Point p1, Point p2, Point p3) {
        return Math.abs(p1.x*(p2.y-p3.y) + p2.x*(p3.y-p1.y) + p3.x*(p1.y-p2.y));
    }
     
    @Override
    public boolean contains(Point point) {
        int totalDoubleArea = this.doubleArea(this.getPosition(), this.point2, this.point3);
        int doubleArea1 = this.doubleArea(point, this.getPosition(), this.point2);
        int doubleArea2 = this.doubleArea(point, this.getPosition(), this.point3);
        int doubleArea3 = this.doubleArea(point, this.point2, this.point3);
        return totalDoubleArea == doubleArea1 + doubleArea2 + doubleArea3;
    }

    @Override
    public void moveTo(Point newp) {
        Point NTemp1 = new Point(newp.x - DX1,newp.y - DY1);
        this.setPosition(NTemp1);
        Point NTemp2 = new Point(newp.x - DX2,newp.y - DY2);
        this.setPoint2(NTemp2);
        Point NTemp3 = new Point(newp.x - DX3,newp.y - DY3);
        this.setPoint3(NTemp3);
        
    }
    
    @Override
    public void setDraggingPoint(Point newp)
    {
        super.setDraggingPoint(newp);
        this.DX1 = getDraggingPoint().x - this.getPosition().x;
        this.DY1 = getDraggingPoint().y - this.getPosition().y;
        this.DX2 = getDraggingPoint().x - this.getPoint2().x;
        this.DY2 = getDraggingPoint().y - this.getPoint2().y;
        this.DX3 = getDraggingPoint().x - this.getPoint3().x;
        this.DY3 = getDraggingPoint().y - this.getPoint3().y;
    }
    
    @Override
    public Triangle copy()
    {
        Point point1C = this.getPosition();
        Point point2C = this.getPoint2();
        Point point3C = this.getPoint3();
        Triangle CTriangle = new Triangle(point1C, point2C, point3C);
        CTriangle.setFillColor(this.getFillColor());
        CTriangle.setColor(this.getColor());
        return CTriangle;
    }

    @Override
    public Point setResizingPoint(Point point) {
        int positionXSP1 = this.getPosition().x - 4;
        int positionYSP1 = this.getPosition().y - 4;
        int positionXSP2 = this.point2.x - 4;
        int positionYSP2 = this.point2.y - 4;
        int positionXSP3 = this.point3.x - 4;
        int positionYSP3 = this.point3.y - 4;
        
        if (point.x <= (positionXSP1 + 8) && point.x >= positionXSP1 && point.y <= (positionYSP1 + 8) && point.y >= positionYSP1) {
            this.resizingPoint = this.getPosition();
            return this.getPosition();
        }
        if (point.x <= (positionXSP2 + 8) && point.x >= positionXSP2 && point.y <= (positionYSP2 + 8) && point.y >= positionYSP2) {
            this.resizingPoint = this.point2;
            return point2;
        }
        if (point.x <= (positionXSP3 + 8) && point.x >= positionXSP3 && point.y <= (positionYSP3 + 8) && point.y >= positionYSP3) {
            this.resizingPoint = this.point3;
            return point3;
        }
        return null;
    }

    @Override
    public void resize(Point newp) {
        if(resizingPoint == this.getPosition())
        {
            //System.out.println("tmm ana f 1");    
        //Point NTemp1 = new Point(newp.x - DX1,newp.y - DY1);
        this.setPosition(newp);
        this.resizingPoint = this.getPosition();
        }
        else if(resizingPoint == this.point2)
        {
            //System.out.println("tmm ana f 2");
        //Point NTemp2 = new Point(newp.x - DX2,newp.y - DY2);
        this.setPoint2(newp);
        this.resizingPoint = this.point2;
        }
        else if(resizingPoint == this.point3)
        {
            //System.out.println("tmm ana f 3");
        //Point NTemp3 = new Point(newp.x - DX3,newp.y - DY3);
        this.setPoint3(newp);
        this.resizingPoint = this.point3;
        }
    }

    @Override
    public void drawHandles(Graphics2D g) {
        g.fillRect(this.getPosition().x - 4, this.getPosition().y - 4, 8, 8);
        g.fillRect(point2.x - 4, point2.y - 4, 8, 8);
        g.fillRect(point3.x - 4, point3.y - 4, 8, 8);
    }


    
    
    
}
