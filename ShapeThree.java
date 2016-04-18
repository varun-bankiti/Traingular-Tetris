/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
/**
 *
 * @author Varun
*/
public class ShapeThree {
    	Point2D center;
	Point2D topLeft;
	Point2D topRight;
	Point2D bottomRight;
	Point2D bottomLeft;
        Color color;
	float reqWidth;
	float reqHeight;
        int orienttions=1,currentOrientation=1;
	ShapeThree(Point2D center,float reqWidth, float reqHeight, Color color){
		this.reqWidth=reqWidth;
		this.reqHeight=reqHeight;
		this.center=center;
                this.color=color;
	}
	void draw(Graphics g){
		this.topLeft=new Point2D(-reqWidth/2,reqHeight/2,center);
		this.topRight=new Point2D(reqWidth/2,reqHeight/2,center);
		this.bottomRight=new Point2D(reqWidth/2,-reqHeight/2,center);
		this.bottomLeft=new Point2D(-reqWidth/2,-reqHeight/2,center);
                g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,topRight.x),Board.iY(center,topRight.y));
		g.drawLine(Board.iX(center,topRight.x),Board.iY(center,topRight.y),Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y));
		g.drawLine(Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y),Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y));
		g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y));	
                int xA1[]={Board.iX(center,topLeft.x)+1,Board.iX(center,topRight.x),Board.iX(center,bottomRight.x),Board.iX(center,bottomLeft.x)};  
                int xB1[]={Board.iY(center,topLeft.y)+1,Board.iY(center,topRight.y),Board.iY(center,bottomRight.y),Board.iY(center,bottomLeft.y)};		
                g.setColor(color);
		g.fillPolygon(xA1,xB1,4);
                g.setColor(Color.black);
	}
        boolean canMove(int direction){
            int x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
            int x2=(int)(((this.center.x+Board.halfBoardWidth-0.5F)*2)+1);
            int y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
            int y2=(int)(((this.center.y+Board.halfBoardHeight-0.5F)*2)+1);y2=Board.gridSizeRows-1-y2;
            boolean result=false;
            switch(this.currentOrientation){
                case 1:
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y1,5,direction) && Board.rectCanMove(x2,y1,5,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y2>=0 && (Board.rectCanMove(x1,y1,5,direction) && Board.rectCanMove(x1,y2,5,direction)));
                            break;
                        case 4:
                            result=(y2>=0 && (Board.rectCanMove(x2,y1,5,direction) && Board.rectCanMove(x2,y2,5,direction)));
                            break;
                    }
                    break;
            }
            return result;
        }
        void updateGrid(){
            int x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
            int x2=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2+1);
            int y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
            int y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
            Board.update(y1,x1,5,5,color);
            Board.update(y1,x2,5,6,color);
            Board.update(y2,x1,5,8,color);
            Board.update(y2,x2,5,7,color);
        }
}
