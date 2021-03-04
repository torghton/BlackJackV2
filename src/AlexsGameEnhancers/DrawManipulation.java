package AlexsGameEnhancers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;

public final class DrawManipulation {
	private DrawManipulation() {}

	public static void drawAndRotate(Drawable toDraw, double degrees, Graphics g, Point translateTo) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		g2d.translate(translateTo.x,translateTo.y);
		g2d.rotate(Math.toRadians(degrees));
		
		toDraw.drawSelf(g);
		
		g2d.setTransform(old);
	}
	
	public static void cutOutShapeFromShape(Shape shapeToCutOutFrom, Shape shapeToCutOut) {
		cutOutShapeFromShape(new Area(shapeToCutOutFrom),new Area(shapeToCutOut));
	}
	
	public static void cutOutShapeFromShape(Area shapeToCutOutFrom, Area shapeToCutOut) {
		shapeToCutOutFrom.subtract(shapeToCutOut);
	}
}