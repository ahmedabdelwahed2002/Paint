/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab7FrontEnd;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author NourKhamis
 */
public abstract class AbstractShape implements Shape,Moveable{
    
    private Color borderColor;
    private Color fillColor = null;
    private Point position;
    private Point draggingPoint;
    private String ShapeName;

    public String getShapeName() {
        return ShapeName;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setShapeName(String ShapeName) {
        this.ShapeName = ShapeName;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public AbstractShape(Point position) {
        this.position = position;
    }
    
    @Override
    public Color getColor() {
        return borderColor;
    }

    @Override
    public void setColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }

    @Override
    public void setDraggingPoint(Point draggingPoint) {
        this.draggingPoint = draggingPoint;
    }
    
    

}
