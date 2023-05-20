/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Lab7FrontEnd;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author NourKhamis
 */
public interface Moveable {
    
    public void setDraggingPoint(Point point);
    public Point getDraggingPoint();
    public boolean contains(Point point);
    public void moveTo(Point point);
    abstract public Shape copy ();
    public void drawHandles (Graphics2D g);
    public Point setResizingPoint(Point point);
    public void resize(Point newp);
}
