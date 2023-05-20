/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Lab7FrontEnd;

/**
 *
 * @author AhmedAbdelwahed
 */
public interface Shape {
    // set position
    public void setPosition(java.awt.Point poisition);
    public java.awt.Point getPosition();
    // colorise
    public void setColor(java.awt.Color color);
    public java.awt.Color getColor();
    public void setFillColor (java.awt.Color color);
    public java.awt.Color getFillColor();
    // redraw shape on canvas
    public void draw (java.awt.Graphics canvas);
}
