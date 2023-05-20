/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7FrontEnd;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author AhmedAbdelwahed
 */

public class LineSegment extends AbstractShape {

    private Point endPosition;
    private int DX1;
    private int DY1;
    private int DX2;
    private int DY2;
    private Point resizingPoint;

    public LineSegment(Point position, Point endPosition) {
        super(position);
        this.endPosition = endPosition;
        this.setShapeName("Line_");
    }

    public Point getendPosition() {
        return endPosition;
    }

    public void setendPosition(Point endPosition) {
        this.endPosition = endPosition;
    }

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        canvas.drawLine(super.getPosition().x, super.getPosition().y, endPosition.x, endPosition.y);
    }

    @Override
    public boolean contains(Point point) {
        Point p1 = this.getPosition();
        Point p2 = this.getendPosition();
        double length = Math.sqrt(Math.pow((double) (p1.x - p2.x), 2.0) + Math.pow((double) (p1.y - p2.y), 2.0));
        double d1 = Math.sqrt(Math.pow((double) (p1.x - point.x), 2.0) + Math.pow((double) (p1.y - point.y), 2.0));
        double d2 = Math.sqrt(Math.pow((double) (p2.x - point.x), 2.0) + Math.pow((double) (p2.y - point.y), 2.0));
        if (length == d1 + d2) {
            return true;
        }
        return false;
    }

    @Override
    public void moveTo(Point newp) {
        Point newPose1 = new Point(newp.x - DX1, newp.y - DY1);
        Point newEndPoint = new Point(newp.x - DX2, newp.y - DY2);
        this.setPosition(newPose1);
        this.setendPosition(newEndPoint);
    }

    @Override
    public void setDraggingPoint(Point p) {
        super.setDraggingPoint(p);
        DX1 = this.getDraggingPoint().x - this.getPosition().x;
        DY1 = this.getDraggingPoint().y - this.getPosition().y;
        DX2 = this.getDraggingPoint().x - this.endPosition.x;
        DY2 = this.getDraggingPoint().y - this.endPosition.y;
    }

    public LineSegment copy() {
        Point p1 = this.getPosition();
        Point p2 = this.getendPosition();
        LineSegment l = new LineSegment(p1, p2);
        l.setColor(this.getColor());
        return l;
    }

    @Override
    public void resize(Point newp) {
        if (this.resizingPoint == this.getPosition()) {
            //Point newPose1 = new Point(newp.x - DX1, newp.y - DY1);
            this.setPosition(newp);
            this.resizingPoint = this.getPosition();

        } else {
            //Point newEndPoint = new Point(newp.x - DX2, newp.y - DY2);
            this.setendPosition(newp);
            this.setPosition(this.endPosition);
            
        }
    }

    @Override
    public void drawHandles(Graphics2D g) {
        g.fillRect(this.getPosition().x - 4, this.getPosition().y - 4, 8, 8);
        g.fillRect(endPosition.x - 4, endPosition.y - 4, 8, 8);
    }

    @Override
    public Point setResizingPoint(Point point) {
        int positionXSP1 = this.getPosition().x - 4;
        int positionYSP1 = this.getPosition().y - 4;
        int positionXSP2 = this.endPosition.x - 4;
        int positionYSP2 = this.endPosition.y - 4;

        if (point.x <= (positionXSP1 + 8) && point.x >= positionXSP1 && point.y <= (positionYSP1 + 8) && point.y >= positionYSP1) {
            this.resizingPoint = this.getPosition();
            return this.getPosition();
        }
        if (point.x <= (positionXSP2 + 8) && point.x >= positionXSP2 && point.y <= (positionYSP2 + 8) && point.y >= positionYSP2) {
            this.resizingPoint = this.endPosition;
            return endPosition;
        }

        return null;
    }

}
