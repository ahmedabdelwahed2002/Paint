/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Lab7FrontEnd;

import java.util.ArrayList;

/**
 *
 * @author AhmedAbdelwahed
 */
public interface DrawingEngine {
    // manage shape objects
    public void addShape(Shape shape);
    public void removeShape (Shape shape);
    // return all shapes drawn on canvas
    public Shape[] getShapes();
    //public ArrayList <Shape> getShapes();
    // redraws all shapes on canvas
    public void refresh(java.awt.Graphics canvas);
}