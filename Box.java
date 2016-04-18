/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Varun
 */
import java.awt.*;
public class Box{
    boolean leftLine,bottomLine,rightLine,topLine,firstDiagonal,secondDiagonal;
    Color color1,color2;
    int type;
    Box(boolean leftLine,boolean bottomLine,boolean rightLine,boolean topLine,boolean firstDiagonal,boolean secondDiagonal,Color color1,Color color2,int type){
        this.leftLine=leftLine;
        this.bottomLine=bottomLine;
        this.rightLine=rightLine;
        this.topLine=topLine;
        this.firstDiagonal=firstDiagonal;
        this.secondDiagonal=secondDiagonal;
        this.color1=color1;
        this.color2=color2;
        this.type=type;
    }
    Box(Box obj){
        this.bottomLine=obj.bottomLine;
        this.topLine=obj.topLine;
        this.leftLine=obj.leftLine;
        this.rightLine=obj.rightLine;
        this.firstDiagonal=obj.firstDiagonal;
        this.secondDiagonal=obj.secondDiagonal;
        this.color1=obj.color1;
        this.color2=obj.color2;
        this.type=obj.type;
    }
}
