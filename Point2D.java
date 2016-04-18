/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Varun
 */
class Point2D{
	float x,y;
	int X,Y;
        Point2D center=null;
        Point2D(Point2D P){
            this.x=P.x;
            this.y=P.y;
            this.X=P.X;
            this.Y=P.Y;
            this.center=P.center;
        }
	Point2D(int x,int y, Point2D center){
		this.X=x;this.Y=y;
		this.x=Board.fx(center,x);
		this.y=Board.fy(center,y);
                this.center=center;
	}
	Point2D(float x,float y, Point2D center){
		this.x=x;this.y=y;
		this.X=Board.iX(center,x);
		this.Y=Board.iY(center,y);
                this.center=center;
	}
	Point2D(int x,int y){
		this.X=x;this.Y=y;
	}
	Point2D(float x,float y){
		this.x=x;this.y=y;
	}
	void copyTo(Point2D P){
		this.x=P.x;this.y=P.y;
		this.X=P.X;this.Y=P.Y;
	}
        Point2D decreaseY(float val){
            return new Point2D(this.x,this.y-val,this.center);
        }
        Point2D decreaseX(float val){
            return new Point2D(this.x-val,this.y,this.center);
        }
        Point2D increaseX(float val){
            return new Point2D(this.x+val,this.y,this.center);
        }
        void display(){
            System.out.println(this.x+"-"+this.y+"---"+this.X+"-"+this.Y);   
        }
}

