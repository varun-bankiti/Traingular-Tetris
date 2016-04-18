/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
/**
 *
 * @author Varun
 */
class ShapeOne{
	float width,height;
	Color color;
	Point2D bottom,right,top,center;
        int orienttions=4;
        int currentOrientation=1;
	ShapeOne(Point2D center,float width,float height,Color color){
		this.width=width;
		this.height=height;
		this.color=color;
		this.center=center;
	}
	void draw(Graphics g){
                int xA1[]=new int[3];
                int xB1[]=new int[3];
                switch(this.currentOrientation){
                    case 1:
                        this.bottom=new Point2D(-width/2,-height/2);
                        this.right=new Point2D(width/2,-height/2);
                        this.top=new Point2D(-width/2,height/2);
                        xA1=new int[]{Board.iX(center,right.x)-1,Board.iX(center,bottom.x)+1,Board.iX(center,top.x)};
                        xB1=new int[]{Board.iY(center,right.y),Board.iY(center,bottom.y),Board.iY(center,top.y)};
                        break;
                    case 2:
                        this.bottom=new Point2D(width/2,-height/2);
                        this.right=new Point2D(width/2,height/2);
                        this.top=new Point2D(-width/2,-height/2);
                        xA1=new int[]{Board.iX(center,right.x),Board.iX(center,bottom.x)-1,Board.iX(center,top.x)+1};
                        xB1=new int[]{Board.iY(center,right.y),Board.iY(center,bottom.y),Board.iY(center,top.y)};
                        break;
                    case 3:
                        this.bottom=new Point2D(width/2,height/2);
                        this.right=new Point2D(-width/2,height/2);
                        this.top=new Point2D(width/2,-height/2);
                        xA1=new int[]{Board.iX(center,right.x)+2,Board.iX(center,bottom.x)-1,Board.iX(center,top.x)};
                        xB1=new int[]{Board.iY(center,right.y)+1,Board.iY(center,bottom.y)+1,Board.iY(center,top.y)-1};
                        break;
                    case 4:
                        this.bottom=new Point2D(-width/2,height/2);
                        this.right=new Point2D(-width/2,-height/2);
                        this.top=new Point2D(width/2,height/2);
                        xA1=new int[]{Board.iX(center,right.x),Board.iX(center,bottom.x)+1,Board.iX(center,top.x)-1};
                        xB1=new int[]{Board.iY(center,right.y)-1,Board.iY(center,bottom.y)+1,Board.iY(center,top.y)};
                        break;
                }
		g.drawLine(Board.iX(center,bottom.x),Board.iY(center,bottom.y),Board.iX(center,right.x),Board.iY(center,right.y));
		g.drawLine(Board.iX(center,bottom.x),Board.iY(center,bottom.y),Board.iX(center,top.x),Board.iY(center,top.y));
		g.drawLine(Board.iX(center,right.x),Board.iY(center,right.y),Board.iX(center,top.x),Board.iY(center,top.y));
		g.setColor(color);
		g.fillPolygon(xA1,xB1,3);
                g.setColor(Color.black);
	}
        boolean canMove(int direction){
            int x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
            int x2=(int)(((this.center.x+Board.halfBoardWidth-0.5F)*2)+1);
            int y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
            int y2=(int)(((this.center.y+Board.halfBoardHeight-0.5F)*2)+1);y2=Board.gridSizeRows-1-y2;
            boolean result=false;
            //int y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
            switch(this.currentOrientation){
                case 1:
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y1,5,direction) && Board.rectCanMove(x2,y1,1,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y2>=0 && (Board.rectCanMove(x1,y1,5,direction) && Board.rectCanMove(x1,y2,1,direction)));
                            break;
                        case 4:
                            result=(y2>=0 && (Board.rectCanMove(x2,y1,1,direction) && Board.rectCanMove(x1,y2,1,direction)));
                            break;
                    }
                    break;
                case 2:
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y1,2,direction) && Board.rectCanMove(x2,y1,5,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y2>=0 && (Board.rectCanMove(x1,y1,2,direction) && Board.rectCanMove(x2,y2,2,direction)));
                            break;
                        case 4:
                            result=(y2>=0 && (Board.rectCanMove(x2,y1,5,direction) && Board.rectCanMove(x2,y2,2,direction)));
                            break;
                    }
                    break;
                case 3:
                    switch(direction){
                        case 1:
                            result=(y2>=0 && (Board.rectCanMove(x1,y2,3,direction) && Board.rectCanMove(x2,y1,3,direction)));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(y2>=0 && (Board.rectCanMove(x1,y2,3,direction) && Board.rectCanMove(x2,y1,3,direction)));
                            break;
                        case 4:
                            result=(y2>=0 && (Board.rectCanMove(x2,y1,3,direction) && Board.rectCanMove(x2,y2,5,direction)));
                            break;
                    }
                    break;
                case 4:
                    switch(direction){
                        case 1:
                            result=(Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x2,y2,4,direction));
                            break;
                        case 2:
                            result=false;
                            break;
                        case 3:
                            result=(Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x1,y2,5,direction));
                            break;
                        case 4:
                            result=(Board.rectCanMove(x1,y1,4,direction) && Board.rectCanMove(x2,y2,4,direction));
                            break;
                    }
                    break;
            }
            return result;
        }
        boolean canRotate(int direction){
            boolean result=false;
            int x1=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2);
            int x2=(int)((this.center.x+Board.halfBoardWidth-0.5F)*2+1);
            int y1=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2);y1=Board.gridSizeRows-1-y1;
            int y2=(int)((this.center.y+Board.halfBoardHeight-0.5F)*2+1);y2=Board.gridSizeRows-1-y2;
            switch(this.currentOrientation){
                case 1:
                    switch(direction){
                        case 1:
                            result=((Board.grid[y1][x1]==0 || Board.grid[y1][x1]==4)&&(Board.grid[y2][x2]==0 || Board.grid[y1][x1]==4) &&(Board.grid[y1][x2]==0));
                            break;
                        case 2:
                            result=((Board.grid[y1][x1]==0 || Board.grid[y1][x1]==2)&&(Board.grid[y2][x2]==0 || Board.grid[y1][x1]==2) &&(Board.grid[y2][x1]==0));
                            break;
                    }
                    break;
                case 2:
                    switch(direction){
                        case 1:
                            result=((Board.grid[y1][x2]==0 || Board.grid[y1][x2]==1)&&(Board.grid[y2][x2]==0) &&(Board.grid[y2][x1]==0 || Board.grid[y2][x1]==1));
                            break;
                        case 2:
                            result=((Board.grid[y1][x2]==0 || Board.grid[y1][x2]==3)&&(Board.grid[y1][x1]==0) &&(Board.grid[y2][x1]==0 || Board.grid[y2][x1]==3));
                            break;
                    }
                    break;
                case 3:
                    switch(direction){
                        case 1:
                            result=((Board.grid[y1][x1]==0 || Board.grid[y1][x1]==2)&&(Board.grid[y2][x2]==0 || Board.grid[y1][x1]==2) &&(Board.grid[y2][x1]==0));
                            break;
                        case 2:
                            result=((Board.grid[y1][x1]==0 || Board.grid[y1][x1]==4)&&(Board.grid[y2][x2]==0 || Board.grid[y1][x1]==4) &&(Board.grid[y1][x2]==0));
                            break;
                    }
                    break;

                case 4:
                    switch(direction){
                        case 1:
                            result=((Board.grid[y1][x2]==0 || Board.grid[y1][x2]==3)&&(Board.grid[y1][x1]==0) &&(Board.grid[y2][x1]==0 || Board.grid[y2][x1]==3));
                            break;
                        case 2:
                            result=((Board.grid[y1][x2]==0 || Board.grid[y1][x2]==1)&&(Board.grid[y2][x2]==0) &&(Board.grid[y2][x1]==0 || Board.grid[y2][x1]==1));
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
            switch(this.currentOrientation){
                case 1:
                    Board.update(y1,x1,5,5,color);
                    Board.update(y1,x2,1,5,color);
                    Board.update(y2,x1,1,6,color);
                    break;
                case 2:
                    Board.update(y1,x1,2,6,color);
                    Board.update(y1,x2,5,6,color);
                    Board.update(y2,x2,2,5,color);
                    break;
                case 3:
                    Board.update(y1,x2,3,6,color);
                    Board.update(y2,x1,3,5,color);
                    Board.update(y2,x2,5,7,color);
                    break;
                case 4:
                    Board.update(y1,x1,4,5,color);
                    Board.update(y2,x1,5,8,color);
                    Board.update(y2,x2,4,6,color);
                    break;
            }
        }
}
