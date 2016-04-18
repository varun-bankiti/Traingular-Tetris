/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
/**
 *
 * @author Varun
 */
class ShapeFour{
	float width,height;
	Color color;
	Point2D topLeft,bottomRight,topRight,bottomLeft,center;
        int orienttions=2,currentOrientation=1;
	ShapeFour(Point2D center,float width,float height,Color color){
		this.width=width;
		this.height=height;
		this.color=color;
		this.center=center;
	}
	void draw(Graphics g){
                int xA1[]=new int[3];   
                int xB1[]=new int[3];
                int xA2[]=new int[3];
                int xB2[]=new int[3];
                switch(this.currentOrientation){
                    case 1:
                        this.topLeft=new Point2D(-width/2,height/2);
                        this.topRight=new Point2D(0,height/2);
                        this.bottomRight=new Point2D(width/2,-height/2);
                        this.bottomLeft=new Point2D(0,-height/2);
                        xA1=new int[]{Board.iX(center,topLeft.x)+2,Board.iX(center,bottomLeft.x),Board.iX(center,topRight.x)};
                        xB1=new int[]{Board.iY(center,topLeft.y)+1,Board.iY(center,bottomLeft.y)-1,Board.iY(center,topRight.y)};
                        xA2=new int[]{Board.iX(center,bottomRight.x)-1,Board.iX(center,bottomLeft.x)+1,Board.iX(center,topRight.x)};
                        xB2=new int[]{Board.iY(center,bottomRight.y),Board.iY(center,bottomLeft.y),Board.iY(center,topRight.y)};
                        break;

                    case 2:
                        this.topLeft=new Point2D(-height/2,-width/2);
                        this.topRight=new Point2D(-height/2,0);
                        this.bottomRight=new Point2D(height/2,width/2);
                        this.bottomLeft=new Point2D(height/2,0);
                        xA1=new int[]{Board.iX(center,topLeft.x)+1,Board.iX(center,bottomLeft.x)-1,Board.iX(center,topRight.x)+1};
                        xB1=new int[]{Board.iY(center,topLeft.y)-1,Board.iY(center,bottomLeft.y)+1,Board.iY(center,topRight.y)+1};
                        xA2=new int[]{Board.iX(center,bottomRight.x),Board.iX(center,bottomLeft.x)-1,Board.iX(center,topRight.x)+1};
                        xB2=new int[]{Board.iY(center,bottomRight.y),Board.iY(center,bottomLeft.y),Board.iY(center,topRight.y)};
                        break;
                }
		g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y));
                g.drawLine(Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y),Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y));
		g.drawLine(Board.iX(center,topLeft.x),Board.iY(center,topLeft.y),Board.iX(center,topRight.x),Board.iY(center,topRight.y));
		g.drawLine(Board.iX(center,bottomRight.x),Board.iY(center,bottomRight.y),Board.iX(center,topRight.x),Board.iY(center,topRight.y));
		g.drawLine(Board.iX(center,bottomLeft.x),Board.iY(center,bottomLeft.y),Board.iX(center,topRight.x),Board.iY(center,topRight.y));
		g.setColor(color);
		g.fillPolygon(xA1,xB1,3);
		g.fillPolygon(xA2,xB2,3);
                g.setColor(Color.black);
	}
        boolean canRotate(int direction){
            int x1,x2,x3,x4,y1,y2,y3,y4,revy1;
            boolean result=false;
            switch(this.currentOrientation){
                case 1:
                    x1=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+1);
                    x3=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+2);
                    x4=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+3);
                    y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
                    switch(direction){
                        case 1:
                            result=(y1>0 && y2<Board.gridSizeRows-1 &&(Board.grid[y1-1][x2]==0 || Board.grid[y1-1][x2]==2) &&(Board.grid[y1][x2]==0) && (Board.grid[y2][x2]==0 || Board.grid[y2][x2]==4) && (Board.grid[y1][x3]==0 || Board.grid[y1][x3]==2) && (Board.grid[y2][x3]==0) && (Board.grid[y2+1][x3]==0 || Board.grid[y2+1][x3]==4));
                            break;
                        case 2:
                            result=(y1>0 && y2<Board.gridSizeRows-1 &&(Board.grid[y1-1][x2]==0 || Board.grid[y1-1][x2]==2) &&(Board.grid[y1][x2]==0) && (Board.grid[y2][x2]==0 || Board.grid[y2][x2]==4) && (Board.grid[y1][x3]==0 || Board.grid[y1][x3]==2) && (Board.grid[y2][x3]==0) && (Board.grid[y2+1][x3]==0 || Board.grid[y2+1][x3]==4));
                            break;
                    }
                    break;
                case 2:
                    x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2+1);
                    y1=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+1;y2=Board.gridSizeRows-1-y2;
                    y3=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+2;y3=Board.gridSizeRows-1-y3;
                    y4=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+3;y4=Board.gridSizeRows-1-y4;
                    switch(direction){
                        case 1:
                            result=(x1>0 && x2<Board.gridSizeCols-1 && (Board.grid[y3][x1-1]==0 || Board.grid[y3][x1-1]==1)&& (Board.grid[y2][x1]==0 || Board.grid[y2][x1]==1) && (Board.grid[y3][x1]==0) &&(Board.grid[y2][x2]==0) && (Board.grid[y3][x2]==0 || Board.grid[y3][x2]==3)&& (Board.grid[y2][x2+1]==0 || Board.grid[y2][x2+1]==3) );
                            break;
                        case 2:
                            result=(x1>0 && x2<Board.gridSizeCols-1 && (Board.grid[y3][x1-1]==0 || Board.grid[y3][x1-1]==1)&& (Board.grid[y2][x1]==0 || Board.grid[y2][x1]==1) && (Board.grid[y3][x1]==0) &&(Board.grid[y2][x2]==0) && (Board.grid[y3][x2]==0 || Board.grid[y3][x2]==3)&& (Board.grid[y2][x2+1]==0 || Board.grid[y2][x2+1]==3) );
                            break;
                    }
            }
        return result;
        }
        boolean canMove(int direction){
            int x1,x2,x3,x4,y1,y2,y3,y4,revy1;
            boolean result=false;
            switch(this.currentOrientation){
                case 1:
                    x1=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+1);
                    x3=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+2);
                    x4=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+3);
                    y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
                    revy1=Board.gridSizeRows-1-y1;
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y2,3,direction)&&Board.rectCanMove(x2,y1,3,direction) && Board.rectCanMove(x3,y1,5,direction) && Board.rectCanMove(x4,y1,1,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y2>=0 &&(Board.rectCanMove(x1,y2,3,direction) && Board.rectCanMove(x2,y1,3,direction)));
                            break;
                        case 4:
                            result=(y2>=0 && (Board.rectCanMove(x3,y2,1,direction) && Board.rectCanMove(x4,y1,1,direction)));
                            break;
                    }
                    break;
                case 2:
                    x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2+1);
                    y1=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+1;y2=Board.gridSizeRows-1-y2;
                    y3=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+2;y3=Board.gridSizeRows-1-y3;
                    y4=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+3;y4=Board.gridSizeRows-1-y4;
                    
                    revy1=Board.gridSizeRows-1-y1;
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x2,y2,4,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y4>=0 && (Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x1,y2,5,direction) && Board.rectCanMove(x1,y3,2,direction) && Board.rectCanMove(x2, y4,2, direction)));
                            break;
                        case 4:
                            result=(y4>=0 && (Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x2,y2,4,direction) && Board.rectCanMove(x2,y3,5,direction) && Board.rectCanMove(x2, y4,2, direction)));
                            break;
                    }
                    break;
            }

            return result;
        }
        void updateGrid(){
            int x1,x2,x3,x4,y1,y2,y3,y4;
            switch(this.currentOrientation){
                case 1:
                    x1=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+1);
                    x3=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+2);
                    x4=(int)((this.center.x+Board.halfBoardWidth-1.0F)*2+3);
                    y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
                    Board.update(y2,x1,3,5,color);
                    Board.update(y2,x2,5,7,color);
                    Board.update(y2,x3,1,6,color);
                    Board.update(y1,x2,3,6,color);
                    Board.update(y1,x3,5,5,color);
                    Board.update(y1,x4,1,5,color);
                    break;
                case 2:
                    x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
                    x2=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2+1);
                    y1=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2);y1=Board.gridSizeRows-1-y1;
                    y2=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+1;y2=Board.gridSizeRows-1-y2;
                    y3=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+2;y3=Board.gridSizeRows-1-y3;
                    y4=(int)((this.center.y+Board.halfBoardHeight-1.0F)*2)+3;y4=Board.gridSizeRows-1-y4;
                    Board.update(y1,x1,4,5,color);
                    Board.update(y2,x1,5,8,color);
                    Board.update(y3,x1,2,6,color);
                    Board.update(y2,x2,4,6,color);
                    Board.update(y3,x2,5,6,color);
                    Board.update(y4,x2,2,5,color);
                    break;
            }
        }
}
