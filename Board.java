/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Varun
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
class Board extends Canvas{  
        int centerX, centerY; float xP,yP,xP1,yP1,xP2,yP2;
	public boolean isPaused,isStarted,currentShapeCanGoDown,currentShapeCanGoLeft,currentShapeCanGoRight;
	public static float pixelSize, rWidth, rHeight;
	public Point2D globalCenter;
	public Point2D mainCenter;
	public Point2D nextShapeCenter;
	public Point2D quitButtonCenter;
	public Point2D levelTextCenter;
	public Point2D linesTextCenter;
	public Point2D scoreTextCenter;
	public Rectangle main,quitButtonRect,nextShapeRect,firstRect,levelTextRect,linesTextRect,scoreTextRect;
	public String levelText,linesText,scoreText;
        public int levelValue,linesValue,scoreValue;
	public Font font;
	public int fontSize;
	public ShapeFive nextShape;
        public ShapeOne currentShapeOne,nextShapeOne;
        public ShapeTwo currentShapeTwo,nextShapeTwo;
        public ShapeThree currentShapeThree,nextShapeThree;
        public ShapeFour currentShapeFour,nextShapeFour;
        public ShapeFive currentShapeFive,nextShapeFive;
        public ArrayList<ShapeOne> onBoardShapeOneList=new ArrayList<>();
        public ArrayList<ShapeTwo> onBoardShapeTwoList=new ArrayList<>();
        public ArrayList<ShapeThree> onBoardShapeThreeList=new ArrayList<>();
        public ArrayList<ShapeFour> onBoardShapeFourList=new ArrayList<>();
        public ArrayList<ShapeFive> onBoardShapeFiveList=new ArrayList<>();
        int currentShapeNum,nextShapeNum,currentColorNum,nextColorNum;
        Color colors[]=new Color[]{Color.red,Color.yellow,Color.blue,Color.green,Color.orange};
        public Random rand=new Random();
        public static int maxX,maxY;
        public static float boardWidth,boardHeight;
        public static int gridSizeRows;
        public static int gridSizeCols;
        public static float halfBoardWidth;
        public static float halfBoardHeight;
        public static int [][] grid;
        public static Box [][] grid2;
        public int fallingSpeed;
        public final int scoreFactor,linesFactor;
        public final float speedFactor;
        Timer timer;
        ActionListener actListner = new ActionListener() {@Override
        public void actionPerformed(ActionEvent event) {
            switch(currentShapeNum){
                case 1:
                    currentShapeCanGoDown=currentShapeOne.canMove(1);
                    if(currentShapeCanGoDown){
                        currentShapeOne.center=currentShapeOne.center.decreaseY(0.5F);
                        repaint();
                    }
                    else{
                        currentShapeOne.updateGrid();
                        //displayGrid();
                        onBoardShapeOneList.add(currentShapeOne);
                        timer.stop();
                        isStarted=false;
                        repaint();
                    }
                    break;
                case 2:
                    currentShapeCanGoDown=currentShapeTwo.canMove(1);
                    if(currentShapeCanGoDown){
                        currentShapeTwo.center=currentShapeTwo.center.decreaseY(0.5F);
                        repaint();
                    }
                    else{
                        currentShapeTwo.updateGrid();
                        //displayGrid();
                        onBoardShapeTwoList.add(currentShapeTwo);
                        timer.stop();
                        isStarted=false;
                        repaint();
                    }
                    break;
                case 3:
                    currentShapeCanGoDown=currentShapeThree.canMove(1);
                    if(currentShapeCanGoDown){
                        currentShapeThree.center=currentShapeThree.center.decreaseY(0.5F);
                        repaint();
                    }
                    else{
                        currentShapeThree.updateGrid();
                        //displayGrid();
                        onBoardShapeThreeList.add(currentShapeThree);
                        timer.stop();
                        isStarted=false;
                        repaint();
                    }
                    break;
                case 4:
                    currentShapeCanGoDown=currentShapeFour.canMove(1);
                    if(currentShapeCanGoDown){
                        currentShapeFour.center=currentShapeFour.center.decreaseY(0.5F);
                        repaint();
                    }
                    else{
                        currentShapeFour.updateGrid();
                        //displayGrid();
                        onBoardShapeFourList.add(currentShapeFour);
                        timer.stop();
                        isStarted=false;
                        repaint();
                    }
                    break;
                case 5:
                    currentShapeCanGoDown=currentShapeFive.canMove(1);
                    if(currentShapeCanGoDown){
                        currentShapeFive.center=currentShapeFive.center.decreaseY(0.5F);
                        repaint();
                    }
                    else{
                        currentShapeFive.updateGrid();
                        //displayGrid();
                        onBoardShapeFiveList.add(currentShapeFive);
                        timer.stop();
                        isStarted=false;
                        repaint();
                    }
                    break;
            }
        }};
        public boolean insideShape;
    void displayGrid(){
        for(int i=0;i<gridSizeRows;i++){
            for(int j=0;j<gridSizeCols;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("**********");
    }
    public static boolean rectCanMove(int x,int y,int shape,int direction){
        boolean result=false;
        switch(shape){
            case 1:
                switch(direction){
                    case 1:
                        result=((gridSizeRows-1-y)>0 && (grid[y+1][x]==0));
                        break;
                    case 2:
                        result=false;
                        break;
                    case 3:
                        result=(x>0 && (grid[y][x-1]==0));
                        break;
                    case 4:
                        result=(x<gridSizeCols-1 &&( grid[y][x+1]==0 || (grid[y][x+1]==3 && grid[y][x]==0)));
                        break;
                }
                break;
            case 2:
                switch(direction){
                    case 1:
                        result=((gridSizeRows-1-y)>0 && (grid[y+1][x]==0));
                        break;
                    case 2:
                        result=false;
                        break;
                    case 3:
                        result=(x>0 && (grid[y][x-1]==0 || (grid[y][x-1]==4 && grid[y][x]==0)));
                        break;
                    case 4:
                        result=(x<gridSizeCols-1 && (grid[y][x+1]==0));
                        break;
                }
                break;
            case 3:
                switch(direction){
                    case 1:
                        result=((gridSizeRows-1-y)>0 && (grid[y+1][x]==0 || (grid[y+1][x]==1 && grid[y][x]==0)));
                        break;
                    case 2:
                        break;
                    case 3:
                        result=(x>0 && (grid[y][x-1]==0 ||(grid[y][x-1]==1 && grid[y][x]==0)));
                        break;
                    case 4:
                        result=(x<gridSizeCols-1 && (grid[y][x+1]==0));
                        break;
                }
                break;
            case 4:
                switch(direction){
                    case 1:
                        result=((gridSizeRows-1-y)>0 && (grid[y+1][x]==0 || (grid[y+1][x]==2 && grid[y][x]==0)));
                        break;
                    case 2:
                        break;
                    case 3:
                        result=(x>0 && (grid[y][x-1]==0));
                        break;
                    case 4:
                        result=(x<gridSizeCols-1 && (grid[y][x+1]==0 || (grid[y][x+1]==2 && grid[y][x]==0)));
                        break;
                }
                break;
            case 5:
                switch(direction){
                    case 1:
                        result=((gridSizeRows-1-y)>0 && (grid[y+1][x]==0));
                        break;
                    case 2:
                        result=false;
                        break;
                    case 3:
                        result=(x>0 && (grid[y][x-1]==0));
                        break;
                    case 4:
                        result=(x<gridSizeCols-1 && (grid[y][x+1]==0));
                        break;
                }
                break;
        }
        //System.out.println("Shape="+shape+"\tDirection="+direction+"\tx="+x+" y="+y+"\tresult="+result);
        return result;
    }
    void updateOnBoardCenters(){
        for(int i=0;i<onBoardShapeOneList.size();i++){
            onBoardShapeOneList.get(i).center=new Point2D(onBoardShapeOneList.get(i).center.x,onBoardShapeOneList.get(i).center.y,onBoardShapeOneList.get(i).center.center);
        }
        for(int i=0;i<onBoardShapeTwoList.size();i++){
            onBoardShapeTwoList.get(i).center=new Point2D(onBoardShapeTwoList.get(i).center.x,onBoardShapeTwoList.get(i).center.y,onBoardShapeTwoList.get(i).center.center);
        }
        for(int i=0;i<onBoardShapeThreeList.size();i++){
            onBoardShapeThreeList.get(i).center=new Point2D(onBoardShapeThreeList.get(i).center.x,onBoardShapeThreeList.get(i).center.y,onBoardShapeThreeList.get(i).center.center);
        }
        for(int i=0;i<onBoardShapeFourList.size();i++){
            onBoardShapeFourList.get(i).center=new Point2D(onBoardShapeFourList.get(i).center.x,onBoardShapeFourList.get(i).center.y,onBoardShapeFourList.get(i).center.center);
        }
        for(int i=0;i<onBoardShapeFiveList.size();i++){
            onBoardShapeFiveList.get(i).center=new Point2D(onBoardShapeFiveList.get(i).center.x,onBoardShapeFiveList.get(i).center.y,onBoardShapeFiveList.get(i).center.center);
        }
        switch(currentShapeNum){
            case 1:
                currentShapeOne.center=new Point2D(currentShapeOne.center.x,currentShapeOne.center.y,currentShapeOne.center.center);
                break;
            case 2:
                currentShapeTwo.center=new Point2D(currentShapeTwo.center.x,currentShapeTwo.center.y,currentShapeTwo.center.center);
                break;
            case 3:
                currentShapeThree.center=new Point2D(currentShapeThree.center.x,currentShapeThree.center.y,currentShapeThree.center.center);
                break;
            case 4:
                currentShapeFour.center=new Point2D(currentShapeFour.center.x,currentShapeFour.center.y,currentShapeFour.center.center);
                break;
            case 5:
                currentShapeFive.center=new Point2D(currentShapeFive.center.x,currentShapeFive.center.y,currentShapeFive.center.center);
                break;
        }
    }
    public static void createBox(Box curBox,int shape,int type){
            switch(shape){
                case 1:
                    switch(type){
                        case 1:
                            curBox.leftLine=true;
                            break;
                        case 2:
                            curBox.bottomLine=true;
                            break;
                        case 3:
                            curBox.firstDiagonal=true;
                            break;
                        case 4:
                            curBox.leftLine=true;
                            curBox.bottomLine=true;
                            break;
                        case 5:
                            curBox.bottomLine=true;
                            curBox.firstDiagonal=true;
                            break;
                        case 6:
                            curBox.firstDiagonal=true;
                            curBox.leftLine=true;
                            break;
                    }
                    break;
                case 2:
                    switch(type){
                        case 1:
                            curBox.bottomLine=true;
                            break;
                        case 2:
                            curBox.rightLine=true;
                            break;
                        case 3:
                            curBox.secondDiagonal=true;
                            break;
                        case 4:
                            curBox.bottomLine=true;
                            curBox.rightLine=true;
                            break;
                        case 5:
                            curBox.rightLine=true;
                            curBox.secondDiagonal=true;
                            break;
                        case 6:
                            curBox.secondDiagonal=true;
                            curBox.bottomLine=true;
                            break;
                    }
                    break;
                case 3:
                    switch(type){
                        case 1:
                            curBox.rightLine=true;
                            break;
                        case 2:
                            curBox.topLine=true;
                            break;
                        case 3:
                            curBox.firstDiagonal=true;
                            break;
                        case 4:
                            curBox.rightLine=true;
                            curBox.topLine=true;
                            break;
                        case 5:
                            curBox.topLine=true;
                            curBox.firstDiagonal=true;
                            break;
                        case 6:
                            curBox.firstDiagonal=true;
                            curBox.rightLine=true;
                            break;
                    }
                    break;
                case 4:
                    switch(type){
                        case 1:
                            curBox.topLine=true;
                            break;
                        case 2:
                            curBox.leftLine=true;
                            break;
                        case 3:
                            curBox.secondDiagonal=true;
                            break;
                        case 4:                            
                            curBox.topLine=true;
                            curBox.leftLine=true;
                            break;
                        case 5:
                            curBox.leftLine=true;
                            curBox.secondDiagonal=true;
                            break;
                        case 6:
                            curBox.secondDiagonal=true;
                            curBox.topLine=true;
                            break;
                    }
                    break;
                case 5:
                    switch(type){
                        case 1:
                            curBox.leftLine=true;
                            break;
                        case 2:
                            curBox.bottomLine=true;
                            break;
                        case 3:
                            curBox.rightLine=true;
                            break;
                        case 4:                            
                            curBox.topLine=true;
                            break;
                        case 5:
                            curBox.leftLine=true;
                            curBox.bottomLine=true;
                            break;
                        case 6:
                            curBox.bottomLine=true;
                            curBox.rightLine=true;
                            break;
                        case 7:
                            curBox.rightLine=true;
                            curBox.topLine=true;
                            break;
                        case 8:
                            curBox.topLine=true;
                            curBox.leftLine=true;
                            break;
                    }
                    break;
            }
    }
    public static void showGrid2(){
        boolean fla=false;
        for(int i=0;i<gridSizeRows;i++){
            for(int j=0;j<gridSizeCols;j++){
                if(grid2[i][j].type>0)fla=true;
                break;
            }
            if(fla)break;
        }
        if(fla){
        for(int i=0;i<gridSizeRows;i++){
            for(int j=0;j<gridSizeCols;j++){
                System.out.print(grid2[i][j].type+" ");
            }
            System.out.println();
        }
        }
    }
    public static void update(int y,int x,int shape,int type,Color color){
        if(grid[y][x]==0){
            grid[y][x]=shape;
            grid2[y][x].color1=color;
            grid2[y][x].type=shape;
        }
        else{
            grid2[y][x].type=grid2[y][x].type+5;
            grid2[y][x].color2=color;
            grid[y][x]=5;
        }
        createBox(grid2[y][x],shape,type);
    }
    void initgr(){  
        Dimension d = getSize();
        maxX = d.width - 1; maxY = d.height - 1;
        pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
        globalCenter.copyTo(new Point2D(maxX/2,maxY/2));
        float optionsCenterX=rWidth/4.0F;
        if(boardWidth>(rWidth/2.0F)){
            optionsCenterX=boardWidth-boardWidth/2.0F;
        }
        mainCenter.copyTo(new Point2D(Board.iX(globalCenter,-(rWidth/2.0F-(boardWidth/2.0F))),Board.iY(globalCenter,0.0F)));
        nextShapeCenter.copyTo(new Point2D(Board.iX(globalCenter,optionsCenterX),Board.iY(globalCenter,(halfBoardHeight-1.5F))));
        quitButtonCenter.copyTo(new Point2D(Board.iX(globalCenter,optionsCenterX),Board.iY(globalCenter,-(halfBoardHeight-0.5F))));
        levelTextCenter.copyTo(new Point2D(Board.iX(globalCenter,optionsCenterX),Board.iY(globalCenter,1.5F)));
        linesTextCenter.copyTo(new Point2D(Board.iX(globalCenter,optionsCenterX),Board.iY(globalCenter,0.0F)));
        scoreTextCenter.copyTo(new Point2D(Board.iX(globalCenter,optionsCenterX),Board.iY(globalCenter,-1.5F)));
        fontSize=(maxX*maxY)/((maxX+maxY)*(int)(rHeight));
        font = new Font("SansSerif", Font.BOLD, fontSize);
        updateOnBoardCenters();
    }
    Board(float totalWidth,float totalHeight,float bWidth,float bHeight,final int scoreFactor,int linesFactor,float speedFactor){
        insideShape=false;
        this.scoreFactor=scoreFactor;
        this.linesFactor=linesFactor;
        this.speedFactor=speedFactor;
        this.scoreValue=0;
        this.linesValue=0;
        this.levelValue=1;
        this.fallingSpeed=500;
	isPaused=false;isStarted=false;currentShapeCanGoDown=true;currentShapeCanGoLeft=true;currentShapeCanGoRight=true;
        rWidth = totalWidth;rHeight = totalHeight;
	globalCenter=new Point2D(-1.0F,-1.0F);
	mainCenter=new Point2D(-1.0F,-1.0F);
	nextShapeCenter= new Point2D(-1.0F,-1.0F);
	quitButtonCenter= new Point2D(-1.0F,-1.0F);
	levelTextCenter= new Point2D(-1.0F,-1.0F);
	linesTextCenter= new Point2D(-1.0F,-1.0F);
	scoreTextCenter= new Point2D(-1.0F,-1.0F);
        currentShapeNum=-1;nextShapeNum=-1;currentColorNum=-1;nextColorNum=-1;
        boardWidth=bWidth;
        boardHeight=bHeight;
        gridSizeRows=(int)(2*boardHeight);
        gridSizeCols=(int)(2*boardWidth);
        halfBoardWidth=boardWidth/2.0F;
        halfBoardHeight=boardHeight/2.0F;
        grid =new int[gridSizeRows][gridSizeCols];
        grid2=new Box[gridSizeRows][gridSizeCols];
        
        for(int i=0;i<gridSizeRows;i++){
            for(int j=0;j<gridSizeCols;j++){
                grid[i][j]=0;
                grid2[i][j]=new Box(false,false,false,false,false,false,Color.white,Color.white,0);
            }
        }
        timer = new Timer(fallingSpeed, actListner);
        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                try{
                    xP = fx(main.center,evt.getX()); yP = fy(main.center,evt.getY());
                    Point2D P=new Point2D(xP,yP);
                    if(main.isInside(P)) {
                        if(!isPaused)isPaused=true;
                        Point2D vertices[]=new Point2D[4];
                        Point2D center=globalCenter;
                        switch(currentShapeNum){
                            case 1:
                                vertices=new Point2D[]{currentShapeOne.top,currentShapeOne.bottom,currentShapeOne.right};
                                center=currentShapeOne.center;
                                break;
                            case 2:
                                vertices=new Point2D[]{currentShapeTwo.top,currentShapeTwo.bottomLeft,currentShapeTwo.bottomRight};
                                center=currentShapeTwo.center;
                                break;
                            case 3:
                                vertices=new Point2D[]{currentShapeThree.bottomLeft,currentShapeThree.bottomRight,currentShapeThree.topRight,currentShapeThree.topLeft};
                                center=currentShapeThree.center;
                                break;
                            case 4:
                                vertices=new Point2D[]{currentShapeFour.bottomLeft,currentShapeFour.bottomRight,currentShapeFour.topRight,currentShapeFour.topLeft};
                                center=currentShapeFour.center;
                                break;
                            case 5:
                                vertices=new Point2D[]{currentShapeFive.bottomLeft,currentShapeFive.bottomRight,currentShapeFive.topRight,currentShapeFive.topLeft};
                                center=currentShapeFive.center;
                                break;
                        }
                        boolean c=contains(evt.getX(),evt.getY(),vertices,center);
                        if(c && !insideShape){
                            scoreValue-=(levelValue*scoreFactor);
                            if(scoreValue>=0){
                                insideShape=true;
                                boolean tmpFlag=true;
                                int tmpNextShape=-1;
                                while(tmpFlag){
                                    tmpNextShape=rand.nextInt(5)+1;
                                    if(tmpNextShape!=currentShapeNum && tmpNextShape!=nextShapeNum)tmpFlag=false;
                                }
                                currentShapeNum=tmpNextShape;
                                currentColorNum=tmpNextShape-1;
                                switch(tmpNextShape){
                                    case 1:
                                        currentShapeOne=new ShapeOne(center,1.0F,1.0F,colors[currentColorNum]);
                                        break;
                                    case 2:
                                        currentShapeTwo=new ShapeTwo(center,2.0F,1.0F,colors[currentColorNum]);
                                        break;
                                    case 3:
                                        currentShapeThree=new ShapeThree(center,1.0F,1.0F,colors[currentColorNum]);
                                        break;
                                    case 4:
                                        currentShapeFour=new ShapeFour(center,2.0F,1.0F,colors[currentColorNum]);
                                        break;
                                    case 5:
                                        currentShapeFive=new ShapeFive(center,2.0F,1.0F,colors[currentColorNum]);
                                        break;
                                }
                                Thread.sleep(200);
                            }
                            /*else{
                                System.out.println("Sorry, Not Enough Score to change the Shape!!");
                            }*/
                            scoreValue=Math.max(0,scoreValue);
                        }
                        else if(!c){
                            insideShape=false;
                        }
                    if(insideShape)repaint();
                    }
                    else if(!main.isInside(P) && isPaused){
                        isPaused=false;
                        timer.start();
                        repaint();
                    }
                }
                catch(Exception e){}
            }
        });
        addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int rotation=e.getWheelRotation();
                boolean canBeHorizontally,canBeVertically;
                switch(currentShapeNum){
                    case 1:
                        if(rotation<0 && currentShapeOne.canRotate(2)){
                            switch(currentShapeOne.currentOrientation){
                                case 1:
                                    currentShapeOne.currentOrientation=4;
                                    break;
                                case 2:
                                    currentShapeOne.currentOrientation=1;
                                    break;
                                case 3:
                                    currentShapeOne.currentOrientation=2;
                                    break;
                                case 4:
                                    currentShapeOne.currentOrientation=3;
                                    break;
                            }
                        }
                        else if(rotation>=0 && currentShapeOne.canRotate(1)){
                            switch(currentShapeOne.currentOrientation){
                                case 1:
                                    currentShapeOne.currentOrientation=2;
                                    break;
                                case 2:
                                    currentShapeOne.currentOrientation=3;
                                    break;
                                case 3:
                                    currentShapeOne.currentOrientation=4;
                                    break;
                                case 4:
                                    currentShapeOne.currentOrientation=1;
                                    break;
                            }
                        }
                        break;
                    case 2:
                        if(rotation<0 && currentShapeTwo.canRotate(2)){
                            switch(currentShapeTwo.currentOrientation){
                                case 1:
                                    currentShapeTwo.currentOrientation=4;
                                    break;
                                case 2:
                                    currentShapeTwo.currentOrientation=1;
                                    break;
                                case 3:
                                    currentShapeTwo.currentOrientation=2;
                                    break;
                                case 4:
                                    currentShapeTwo.currentOrientation=3;
                                    break;
                            }
                        }
                        else if(rotation>=0 && currentShapeTwo.canRotate(1)){
                            switch(currentShapeTwo.currentOrientation){
                                case 1:
                                    currentShapeTwo.currentOrientation=2;
                                    break;
                                case 2:
                                    currentShapeTwo.currentOrientation=3;
                                    break;
                                case 3:
                                    currentShapeTwo.currentOrientation=4;
                                    break;
                                case 4:
                                    currentShapeTwo.currentOrientation=1;
                                    break;
                            }
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        switch(currentShapeFour.currentOrientation){
                            case 1:
                                if(currentShapeFour.canRotate(1))
                                currentShapeFour.currentOrientation=2;
                                break;
                            case 2:
                                if(currentShapeFour.canRotate(1))
                                currentShapeFour.currentOrientation=1;
                                break;
                        }
                        break;
                    case 5:
                        switch(currentShapeFive.currentOrientation){
                            case 1:
                                if(currentShapeFive.canRotate(1))
                                currentShapeFive.currentOrientation=2;
                                break;
                            case 2:
                                if(currentShapeFive.canRotate(1))
                                currentShapeFive.currentOrientation=1;
                                break;
                        }
                        break;
                }
                repaint();
            }
        });
        addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent evt){
                xP1 = fx(quitButtonRect.center,evt.getX()); yP1 = fy(quitButtonRect.center,evt.getY());
                Point2D P=new Point2D(xP1,yP1);
                xP2 = fx(main.center,evt.getX()); yP2 = fy(main.center,evt.getY());
                Point2D P2=new Point2D(xP2,yP2,mainCenter);
                if(quitButtonRect.isInside(P)){
                    System.exit(0);
                }
                else if(!main.isInside(P2)){
                    if((evt.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
                        switch(currentShapeNum){
                            case 1:
                                currentShapeCanGoRight=currentShapeOne.canMove(4);
                                if(currentShapeCanGoRight){
                                    currentShapeOne.center=currentShapeOne.center.increaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 2:
                                currentShapeCanGoRight=currentShapeTwo.canMove(4);
                                if(currentShapeCanGoRight){
                                    currentShapeTwo.center=currentShapeTwo.center.increaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 3:
                                currentShapeCanGoRight=currentShapeThree.canMove(4);
                                if(currentShapeCanGoRight){
                                    currentShapeThree.center=currentShapeThree.center.increaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 4:
                                currentShapeCanGoRight=currentShapeFour.canMove(4);
                                if(currentShapeCanGoRight){
                                    currentShapeFour.center=currentShapeFour.center.increaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 5:
                                currentShapeCanGoRight=currentShapeFive.canMove(4);
                                if(currentShapeCanGoRight){
                                    currentShapeFive.center=currentShapeFive.center.increaseX(0.5F);
                                    repaint();
                                }
                                break;
                        }
                    }
                    else{
                        switch(currentShapeNum){
                            case 1:
                                currentShapeCanGoLeft=currentShapeOne.canMove(3);
                                if(currentShapeCanGoLeft){
                                    currentShapeOne.center=currentShapeOne.center.decreaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 2:
                                currentShapeCanGoLeft=currentShapeTwo.canMove(3);
                                if(currentShapeCanGoLeft){
                                    currentShapeTwo.center=currentShapeTwo.center.decreaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 3:
                                currentShapeCanGoLeft=currentShapeThree.canMove(3);
                                if(currentShapeCanGoLeft){
                                    currentShapeThree.center=currentShapeThree.center.decreaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 4:
                                currentShapeCanGoLeft=currentShapeFour.canMove(3);
                                if(currentShapeCanGoLeft){
                                    currentShapeFour.center=currentShapeFour.center.decreaseX(0.5F);
                                    repaint();
                                }
                                break;
                            case 5:
                                currentShapeCanGoLeft=currentShapeFive.canMove(3);
                                if(currentShapeCanGoLeft){
                                    currentShapeFive.center=currentShapeFive.center.decreaseX(0.5F);
                                    repaint();
                                }
                                break;
                        }
                    }
        }
            }
        });
    }
    public static int iX(Point2D P, float x){return Math.round(P.X + x/pixelSize);}
    public static int iY(Point2D P, float y){return Math.round(P.Y - y/pixelSize);}
    public static int iX(float x){return Math.round(x/pixelSize);}
    public static int iY(float y){return maxY-Math.round(y/pixelSize);}
    
    public static float fx(Point2D P, int x){return (x - P.X) * pixelSize;}
    public static float fy(Point2D P, int y){return (P.Y - y) * pixelSize;}   
    
    public static float fx(int x){return x * pixelSize;}
    public static float fy(int y){return (maxY - y) * pixelSize;}   
    public int clearRow(){
        int result=1;
        int r=-1;
        for(int i=gridSizeRows-1;i>=0;i-=2){
            int tmpResult=1;
            for(int j=0;j<gridSizeCols;j++){
                if(grid[i][j]!=5 || grid[i-1][j]!=5){tmpResult=0;break;}
            }
            if(tmpResult==1){r=i;break;}
            else result=0;
        }
        if(result==1){
            System.out.println("First Row Yes");
            for(int i=r;i>1;i--){
                for(int j=0;j<gridSizeCols;j++){
                    grid[i][j]=grid[i-2][j];
                    grid2[i][j]=new Box(grid2[i-2][j]);
                }
            }
            for(int j=0;j<gridSizeCols;j++){
                grid[0][j]=0;
                grid[1][j]=0;
                grid2[0][j]=new Box(false,false,false,false,false,false,Color.white,Color.white,0);
                grid2[1][j]=new Box(false,false,false,false,false,false,Color.white,Color.white,0);
            }
        }
    return result;
    }
    public boolean getNewPeice(Graphics g){
        if(nextShapeNum==-1){
            currentShapeNum=rand.nextInt(5)+1;
            //currentShapeNum=3;
            currentColorNum=currentShapeNum-1;
        }
        else{
            currentShapeNum=nextShapeNum;
            currentColorNum=nextColorNum;
        }
        if(grid[1][(gridSizeCols-1)/2]>0 || grid[1][gridSizeCols/2]>0 || grid[2][(gridSizeCols-1)/2]>0 || grid[2][gridSizeCols/2]>0)return false;
        nextShapeNum=rand.nextInt(5)+1;
        //nextShapeNum=3;
        nextColorNum=nextShapeNum-1;
        switch(currentShapeNum){
            case 1:
                currentShapeOne=new ShapeOne(new Point2D(0.0F,halfBoardHeight-0.5F,mainCenter),1.0F,1.0F,colors[currentColorNum]);
                currentShapeOne.draw(g);
                break;
            case 2:
                currentShapeTwo=new ShapeTwo(new Point2D(0.0F,halfBoardHeight-0.5F,mainCenter),2.0F,1.0F,colors[currentColorNum]);
                currentShapeTwo.draw(g);
                break;
            case 3:
                currentShapeThree=new ShapeThree(new Point2D(0.0F,halfBoardHeight-0.5F,mainCenter),1.0F,1.0F,colors[currentColorNum]);
                currentShapeThree.draw(g);
                break;
            case 4:
                currentShapeFour=new ShapeFour(new Point2D(0.0F,halfBoardHeight-0.5F,mainCenter),2.0F,1.0F,colors[currentColorNum]);
                currentShapeFour.draw(g);
                break;
            case 5:
                currentShapeFive=new ShapeFive(new Point2D(0.0F,halfBoardHeight-0.5F,mainCenter),2.0F,1.0F,colors[currentColorNum]);
                currentShapeFive.draw(g);
                break;
        }
        timer.start();
        isStarted=true;
    return true;
    }
    public void drawOnBoardPieces(Graphics g){
        Color prevColor=g.getColor();
        for(int i=0;i<gridSizeRows;i++){
            for(int j=0;j<gridSizeCols;j++){
                float x1,x2,x3,x4,y1,y2,y3,y4,cX,cY;
                cX=(j/2.0F)-(halfBoardWidth);
                cY=boardHeight-(i/2.0F)-(halfBoardHeight+0.25F);
                x1=x2=cX;
                x3=x4=cX+0.5F;
                y2=y3=cY+0.25F;
                y1=y4=cY-0.25F;
                int X1,X2,X3,X4,Y1,Y2,Y3,Y4;
                X1=iX(mainCenter,x1);
                X2=iX(mainCenter,x2);
                X3=iX(mainCenter,x3);
                X4=iX(mainCenter,x4);
                Y1=iY(mainCenter,y1);
                Y2=iY(mainCenter,y2);
                Y3=iY(mainCenter,y3);
                Y4=iY(mainCenter,y4);
                
                g.setColor(grid2[i][j].color1);
                switch(grid2[i][j].type){
                    case 1:
                        g.fillPolygon(new int[]{X1,X4,X2},new int[]{Y1,Y4,Y2},3);
                        break;
                    case 2:
                        g.fillPolygon(new int[]{X3,X4,X1},new int[]{Y3,Y4,Y1},3);
                        break;
                    case 3:
                        g.fillPolygon(new int[]{X3,X4,X2},new int[]{Y3,Y4,Y2},3);
                        break;
                    case 4:
                        g.fillPolygon(new int[]{X3,X1,X2},new int[]{Y3,Y1,Y2},3);
                        break;
                    case 5:
                        g.fillPolygon(new int[]{X1,X2,X3,X4},new int[]{Y1,Y2,Y3,Y4},4);
                        break;
                    case 6:
                        g.fillPolygon(new int[]{X4,X1,X2},new int[]{Y4,Y1,Y2},3);
                        g.setColor(grid2[i][j].color2);
                        g.fillPolygon(new int[]{X2,X3,X4},new int[]{Y2,Y3,Y4},3);
                        break;
                    case 7:
                        g.fillPolygon(new int[]{X3,X4,X1},new int[]{Y3,Y4,Y1},3);
                        g.setColor(grid2[i][j].color2);
                        g.fillPolygon(new int[]{X1,X2,X3},new int[]{Y1,Y2,Y3},3);
                        break;
                    case 8:
                        g.fillPolygon(new int[]{X2,X3,X4},new int[]{Y2,Y3,Y4},3);
                        g.setColor(grid2[i][j].color2);
                        g.fillPolygon(new int[]{X4,X1,X2},new int[]{Y4,Y1,Y2},3);
                        break;
                    case 9:
                        g.fillPolygon(new int[]{X1,X2,X3},new int[]{Y1,Y2,Y3},3);
                        g.setColor(grid2[i][j].color2);
                        g.fillPolygon(new int[]{X3,X4,X1},new int[]{Y3,Y4,Y1},3);
                        break;
                }
                g.setColor(Color.black);
                if(grid2[i][j].leftLine){
                    g.drawLine(X1,Y1,X2,Y2);
                }
                if(grid2[i][j].topLine){
                    g.drawLine(X2,Y2,X3,Y3);
                }
                if(grid2[i][j].rightLine){
                    g.drawLine(X3,Y3,X4,Y4);
                }
                if(grid2[i][j].bottomLine){
                    g.drawLine(X4,Y4,X1,Y1);
                }
                if(grid2[i][j].secondDiagonal){
                    g.drawLine(X1,Y1,X3,Y3);
                }
                if(grid2[i][j].firstDiagonal){
                    g.drawLine(X2,Y2,X4,Y4);
                }
            }
        }
    g.setColor(prevColor);
    }
    public boolean contains(int X,int Y,Point2D vertices[],Point2D center){
      boolean result = false;
      for (int i = 0, j =vertices.length-1; i<vertices.length; j =i++){
          if ((iY(center,vertices[i].y) > Y) != (iY(center,vertices[j].y) > Y) &&
            (X < (iX(center,vertices[j].x) - iX(center,vertices[i].x)) * (Y - iY(center,vertices[i].y)) / (iY(center,vertices[j].y)-iY(center,vertices[i].y)) + iX(center,vertices[i].x))) {
             result = !result;
         }
      }    
      return result;
    }
    @Override
    public void paint(Graphics g){
        initgr();
        g.setFont(font);
        g.setColor(Color.black);
        float rMainWidth=boardWidth,rMainHeight=boardHeight;
        main=new Rectangle(mainCenter,rMainWidth,rMainHeight);
        main.draw(g);
        drawOnBoardPieces(g);
        if(isPaused){
            timer.stop();
            g.setColor(Color.blue);
            float rFirstWidth=3.0F,rFirstHeight=1.0F;
            firstRect=new Rectangle(mainCenter,rFirstWidth,rFirstHeight);
            firstRect.draw(g);
            String s="PAUSE";
            firstRect.drawCenteredString(g,s);
            g.setColor(Color.black);
        }
        //else{
            if(!isStarted){
                int tmp1=clearRow();
                if(tmp1==1){
                    linesValue+=1;
                    scoreValue=scoreValue+levelValue*scoreFactor;
                    int tmp2=clearRow();
                    if(tmp2==1){
                        linesValue+=1;
                        scoreValue=scoreValue+levelValue*scoreFactor;
                        tmp2=0;
                    }
                    tmp1=0;
                }
                if(linesValue>=linesFactor){
                    System.out.println("Speed Increased");
                    linesValue%=linesFactor;
                    levelValue+=1;
                    fallingSpeed=(int)(((float)fallingSpeed)*(1.0F-((float)levelValue)*speedFactor));
                }
                boolean tmp=getNewPeice(g);
                if(!tmp){
                    JOptionPane.showMessageDialog(null, "Game Over! Score:"+scoreValue, "Game Over",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
            else{
                switch(currentShapeNum){
                    case 1:                            
                        currentShapeOne.draw(g);
                        break;
                    case 2:
                        currentShapeTwo.draw(g);
                        break;
                    case 3:
                        currentShapeThree.draw(g);
                        break;
                    case 4:
                        currentShapeFour.draw(g);
                        break;

                    case 5:
                        currentShapeFive.draw(g);
                        break;
                }                   
            }
        //}
        switch(nextShapeNum){
            case 1:
                nextShapeOne=new ShapeOne(nextShapeCenter,1.0F,1.0F,colors[nextColorNum]);
                nextShapeOne.draw(g);
                break;
            case 2:
                nextShapeTwo=new ShapeTwo(nextShapeCenter,2.0F,1.0F,colors[nextColorNum]);
                nextShapeTwo.draw(g);
                break;
            case 3:
                nextShapeThree=new ShapeThree(nextShapeCenter,1.0F,1.0F,colors[nextColorNum]);
                nextShapeThree.draw(g);
                break;
            case 4:
                nextShapeFour=new ShapeFour(nextShapeCenter,2.0F,1.0F,colors[nextColorNum]);
                nextShapeFour.draw(g);
                break;
            case 5:
                nextShapeFive=new ShapeFive(nextShapeCenter,2.0F,1.0F,colors[nextColorNum]);
                nextShapeFive.draw(g);
                break;
        }
        float nextShapeRectWidth=3.0F,nextShapeRectHeight=2.0F;
        nextShapeRect=new Rectangle(nextShapeCenter,nextShapeRectWidth,nextShapeRectHeight);
        nextShapeRect.draw(g);

        //float nextShapeWidth=2.0F,nextShapeHeight=1.0F;		
        //nextShape=new ShapeFive(nextShapeCenter,nextShapeWidth,nextShapeHeight,Color.yellow);
        //nextShape.draw(g,2);
        g.setColor(Color.black);

        float quitButtonWidth=2.0F,quitButtonHeight=1.0F;
        quitButtonRect=new Rectangle(quitButtonCenter,quitButtonWidth,quitButtonHeight);
        quitButtonRect.draw(g);
        String s1="QUIT";
        quitButtonRect.drawCenteredString(g,s1);

        float levelTextWidth=3.0F,levelTextHeight=1.0F;
        levelTextRect=new Rectangle(levelTextCenter,levelTextWidth,levelTextHeight);
        levelText="Level:";
        levelTextRect.drawLeftString(g,levelText);
        levelTextRect.drawRightString(g,Integer.toString(levelValue));

        float linesTextWidth=3.0F,linesTextHeight=1.0F;
        linesTextRect=new Rectangle(linesTextCenter,linesTextWidth,linesTextHeight);
        linesText="Lines:";
        linesTextRect.drawLeftString(g,linesText);
        linesTextRect.drawRightString(g,Integer.toString(linesValue));

        float scoreTextWidth=3.0F,scoreTextHeight=1.0F;
        scoreTextRect=new Rectangle(scoreTextCenter,scoreTextWidth,scoreTextHeight);
        scoreText="Score:";
        scoreTextRect.drawLeftString(g,scoreText);
        scoreTextRect.drawRightString(g,Integer.toString(scoreValue));


    }
	Point2D midPoint(Point2D X,Point2D Y){
		return new Point2D((X.x+Y.x)/2,(X.y+Y.y)/2);
	}
}

