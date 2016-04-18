/**
 *
 * @author Varun
 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class Tetris extends Frame{
   public boolean first=true;
   public static Tetris tetris;
   public Board board;
   public Label headerLabel;
   public Label statusLabel;
   public Panel controlPanel;
   public static JSlider scoreFactorSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
   public static JSlider linesFactorSlider = new JSlider(JSlider.HORIZONTAL, 20, 50, 35);
   public static JSlider speedFactorSlider = new JSlider(JSlider.HORIZONTAL,0,10,5);
   public static JSlider boardWidthSlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 5);
   public static JSlider boardHeightSlider = new JSlider(JSlider.HORIZONTAL, 10, 15, 13);
   
   public static JSlider totalWidthSlider = new JSlider(JSlider.HORIZONTAL, 10, 15, 13);
   public static JSlider totalHeightSlider = new JSlider(JSlider.HORIZONTAL, 10, 15, 13);
   
   public static int scoreFactor=-1,linesFactor=-1;
   public static float speedFactor=-1.0F,boardWidth,boardHeight,totalWidth,totalHeight;
   public static boolean paramSet=false;
   public AboutDialog aboutDialog;
	public static void main(String[] args){
               Hashtable labelTable = new Hashtable();
               labelTable.put( new Integer( 0 ), new JLabel("0.0") );
               labelTable.put( new Integer( 5 ), new JLabel("0.5") );
               labelTable.put( new Integer( 10 ), new JLabel("1.0") );
               totalWidthSlider.setMajorTickSpacing(1);
               totalWidthSlider.setPaintTicks(true);
               totalWidthSlider.setPaintLabels(true);
               totalHeightSlider.setMajorTickSpacing(1);
               totalHeightSlider.setPaintTicks(true);
               totalHeightSlider.setPaintLabels(true);
               
               boardWidthSlider.setMajorTickSpacing(1);
               boardWidthSlider.setPaintTicks(true);
               boardWidthSlider.setPaintLabels(true);
               boardHeightSlider.setMajorTickSpacing(1);
               boardHeightSlider.setPaintTicks(true);
               boardHeightSlider.setPaintLabels(true);
   
               scoreFactorSlider.setMajorTickSpacing(1);
               scoreFactorSlider.setPaintTicks(true);
               scoreFactorSlider.setPaintLabels(true);
               linesFactorSlider.setMinorTickSpacing(1);
               linesFactorSlider.setMajorTickSpacing(5);
               linesFactorSlider.setPaintTicks(true);
               linesFactorSlider.setPaintLabels(true);
               speedFactorSlider.setMajorTickSpacing(1);
               speedFactorSlider.setPaintTicks(true);
               speedFactorSlider.setPaintLabels(true);
               speedFactorSlider.setLabelTable( labelTable );

            tetris = new Tetris();
            tetris.createMenu();
	}
   Tetris(){
      super("Tetris Game");
      aboutDialog= new AboutDialog(this);
      addWindowListener(new WindowAdapter()
         {@Override
         public void windowClosing(WindowEvent e){System.exit(0);}});
      setLayout(new GridLayout());      
      setSize(400, 300);
      show();
   }
   public void createMenu(){
   final MenuBar menuBar = new MenuBar();
      Menu fileMenu = new Menu("File");
      Menu editMenu= new Menu("Edit");
      MenuItem newMenuItem = 
      new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
      newMenuItem.setActionCommand("New");
      MenuItem exitMenuItem = new MenuItem("Exit",new MenuShortcut(KeyEvent.VK_Q));
      exitMenuItem.setActionCommand("Exit");   
      MenuItem settingsMenuItem = new MenuItem("Settings",new MenuShortcut(KeyEvent.VK_S));
      settingsMenuItem.setActionCommand("Settings");   
      MenuItemListener menuItemListener = new MenuItemListener();
      newMenuItem.addActionListener(menuItemListener);
      exitMenuItem.addActionListener(menuItemListener);
      settingsMenuItem.addActionListener(menuItemListener);
      //add menu items to menus
      fileMenu.add(newMenuItem);
      fileMenu.addSeparator();
      fileMenu.addSeparator();
      fileMenu.add(exitMenuItem);
      editMenu.add(settingsMenuItem);
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      setMenuBar(menuBar);
   }
   class MenuItemListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if(e.getActionCommand()=="Settings"){
            aboutDialog.setVisible(true);
         }
         else if(e.getActionCommand()=="New"){
            if(!first){tetris.remove(board);}
            if(paramSet){
               board=new Board(totalWidth,totalHeight,boardWidth,boardHeight,scoreFactor,linesFactor,speedFactor);
               tetris.add(board);
               tetris.show();
               first=false;
            }
            else{
            JOptionPane.showMessageDialog(null, "Set the parameters in settings menu", "Notice", JOptionPane.INFORMATION_MESSAGE);
            }
         }
         else if(e.getActionCommand()=="Exit"){
             dispose();
         }
      }    
   }
   class AboutDialog extends Dialog {
      public AboutDialog(Frame parent){        
        super(parent, true);      
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               dispose();
            }
         });
        setBackground(Color.gray);        
        Panel panel = new Panel(new GridLayout(8,5,5,10));
        add("South", panel);
        setSize(400,470);
        panel.add(totalWidthSlider);                        
        JLabel totalWidthLabel = new JLabel("Total Width", JLabel.LEFT);
        panel.add(totalWidthLabel);
        panel.add(totalHeightSlider);  
        JLabel totalHeightLabel = new JLabel("Total Height", JLabel.LEFT);
        panel.add(totalHeightLabel);        
        panel.add(boardWidthSlider);                        
        JLabel boardWidthLabel = new JLabel("Board Width", JLabel.LEFT);
        panel.add(boardWidthLabel);
        panel.add(boardHeightSlider);  
        JLabel boardHeightLabel = new JLabel("Board Height", JLabel.LEFT);
        panel.add(boardHeightLabel);
        panel.add(scoreFactorSlider);                        
        JLabel scoringFactorLabel = new JLabel("Scoring Factor", JLabel.LEFT);
        panel.add(scoringFactorLabel);
        panel.add(linesFactorSlider);
        JLabel levelFactorLabel = new JLabel("Level Factor", JLabel.LEFT);
        panel.add(levelFactorLabel);
        panel.add(speedFactorSlider);
        JLabel speedFactorLabel = new JLabel("Speed Factor", JLabel.LEFT);
        panel.add(speedFactorLabel);
        JButton close = new JButton("");
        Action buttonAction = new AbstractAction("Set Parameters") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                scoreFactor=scoreFactorSlider.getValue();
                linesFactor=linesFactorSlider.getValue();
                speedFactor=speedFactorSlider.getValue()/10.0F;
                boardWidth=(float)boardWidthSlider.getValue();
                boardHeight=(float)boardHeightSlider.getValue();
                totalWidth=(float)totalWidthSlider.getValue();
                totalHeight=(float)totalHeightSlider.getValue();
                if(totalWidth-boardWidth>=3){
                    paramSet=true;
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Difference of Total Width & Board Width should be >=3", "Notice", JOptionPane.INFORMATION_MESSAGE);                
                }
                //System.out.println(totalWidth+"\t"+totalHeight+"\t"+boardWidth+"\t"+boardHeight+"\t"+scoreFactorSlider.getValue()+"\t"+linesFactorSlider.getValue()+"\t"+speedFactorSlider.getValue());                
           }
        };
        close.setAction(buttonAction);
        String key="Setting Parameters";
        buttonAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_ENTER);
        close.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),key);
        close.getActionMap().put(key, buttonAction);
        panel.add(close);
      }
      public boolean action(Event evt, Object arg){
            dispose();
            return true;
      }
    }
}

