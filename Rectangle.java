/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Varun
 */
import java.awt.*;
class Rectangle{
	Point2D center;
	Point2D topLeft;
	Point2D topRight;
	Point2D bottomRight;
	Point2D bottomLeft;
	float reqWidth;
	float reqHeight;
	Rectangle(Point2D P, float reqWidth, float reqHeight){
		this.reqWidth=reqWidth;
		this.reqHeight=reqHeight;
		this.center=P;
		this.topLeft=new Point2D(-reqWidth/2,reqHeight/2,P);
		this.topRight=new Point2D(reqWidth/2,reqHeight/2,P);
		this.bottomRight=new Point2D(reqWidth/2,-reqHeight/2,P);
		this.bottomLeft=new Point2D(-reqWidth/2,-reqHeight/2,P);
	}
	void draw(Graphics g){
		g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,topRight.x),Board.iY(center,topRight.y));
		g.drawLine(Board.iX(center,topRight.x),Board.iY(center,topRight.y),Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y));
		g.drawLine(Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y),Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y));
		g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y));	
	}
	public void drawCenteredString(Graphics g,String s) {
		FontMetrics fm = g.getFontMetrics();
		int x = center.X - fm.stringWidth(s)/2;
		int y = center.Y +(fm.getAscent()+fm.getDescent())/3;
		g.drawString(s, x, y);
	}
	public void drawLeftString(Graphics g,String s) {
		FontMetrics fm = g.getFontMetrics();
		int x = topLeft.X;
		int y = center.Y +(fm.getAscent()+fm.getDescent())/2;
		g.drawString(s, x, y);
	}
	public void drawRightString(Graphics g,String s) {
		FontMetrics fm = g.getFontMetrics();
		int x = topRight.X - fm.stringWidth(s);
		int y = center.Y +(fm.getAscent()+fm.getDescent())/2;
		g.drawString(s, x, y);
	}
	boolean isInside(Point2D P){
		if(topLeft.x<=P.x && P.x<=topRight.x && P.y<=topLeft.y && bottomLeft.y<=P.y)return true;
		else return false;
	}
}


