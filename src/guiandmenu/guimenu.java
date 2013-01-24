package guiandmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author HP
 */

public class guimenu extends DataTransformers implements ActionListener{
    
    int[][] theImageArray;
    BufferedImage theimage;
    
    //Where the GUI is created:
    JMenuBar menuBar;    
    JFrame frame ;
    JMenu File, GrayLevel, Filtering, Help, HighPass;
    
    //JMenuItems for file menu
    JMenuItem open, save, saveas, print, exit, about;
    // JMenuItems for GrayLevel menu
    JMenuItem brighter, darker, histEq, negative, squareRoot, squareTransfer;
    // JMenuItems for Filtering menu
    JMenuItem average, lowpass, laplacian, median;
    // JMenuItems for HighPass menu
    JMenuItem partDifX, partDifY, enPartDifX, enPartDifY, sobelX, sobelY, sobelXY, sobelYX;
    
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;

    
    ImagePanel imagePanel;
    JDesktopPane desktop;

    
    //constructor
    public guimenu(){
        

 //       JFrame.setDefaultLookAndFeelDecorated(true);


        frame = new JFrame("The Image Processor - V1.0");

        //3. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        
        //Building the menus
        File = new JMenu("File");
       // File.getAccessibleContext().setAccessibleDescription("bu accessible de");
        GrayLevel = new JMenu("Gray Level");
        Filtering = new JMenu("Filtering");
        Help = new JMenu("Help");
        HighPass = new JMenu("High Pass Filters");
        // adding menus to menubar
        menuBar.add(File);
        menuBar.add(GrayLevel);
        menuBar.add(Filtering);
        menuBar.add(Help);
        
        //JMenuItems for File Menu
        open = new JMenuItem("Open");
        open.addActionListener(this);
        File.add(open);
        
        File.addSeparator();
        
        save = new JMenuItem("Save");
        save.addActionListener(this);
        File.add(save);
        
        saveas = new JMenuItem("Save As...");
        saveas.addActionListener(this);
        File.add(saveas);

        File.addSeparator();

        print = new JMenuItem("Print...");
        print.addActionListener(this);
        File.add(print);
        File.addSeparator();
        
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        File.add(exit);
        /*saveas
        print
        exit*/
        
        //JMenuItems items for GrayLevel Menu
        brighter = new JMenuItem("Brighter");
        brighter.addActionListener(this);
        darker = new JMenuItem("Darker");
        darker.addActionListener(this);
        histEq = new JMenuItem("Histogram Equalisation");
        histEq.addActionListener(this);
        histEq.setToolTipText("Click this button to disable the middle button.");
        negative = new JMenuItem("Negative");
        negative.addActionListener(this);
        squareRoot = new JMenuItem("Square Root Transfer Function");
        squareRoot.addActionListener(this);
        squareTransfer = new JMenuItem("Square Transfer Function");    
        squareTransfer.addActionListener(this);
        GrayLevel.add(brighter);
        GrayLevel.add(darker);
        GrayLevel.add(histEq);
        GrayLevel.add(negative);
        GrayLevel.add(squareRoot);
        GrayLevel.add(squareTransfer);
        
        //JMenuItems for Filtering Menu
        average = new JMenuItem("Average Filter");
        average.addActionListener(this);
        lowpass = new JMenuItem("Gaussian Low-pass Filter");
        lowpass.addActionListener(this);
        laplacian = new JMenuItem("Laplacian Filter");
        laplacian.addActionListener(this);
        median = new JMenuItem("Median Filter");
        median.addActionListener(this);
        Filtering.add(average);
        Filtering.add(lowpass);
        Filtering.add(HighPass);
        Filtering.add(laplacian);
        Filtering.add(median);
        
        //JMenuItems for High Pass Filter Menu
        partDifX = new JMenuItem("Partial Differentiation in X");
        partDifX.addActionListener(this);
        partDifY = new JMenuItem("Partial Differentiation in Y");
        partDifY.addActionListener(this);
        enPartDifX = new JMenuItem("Enhanced Partial Differentiation in X");
        enPartDifX.addActionListener(this);
        enPartDifY = new JMenuItem("Enhanced Partial Differentiation in Y");
        enPartDifY.addActionListener(this);
        sobelX = new JMenuItem("Sobel Operator in X");
        sobelX.addActionListener(this);
        sobelY = new JMenuItem("Sobel Operator in Y");
        sobelY.addActionListener(this);
        sobelXY = new JMenuItem("Sobel Operator in XY");
        sobelXY.addActionListener(this);
        sobelYX = new JMenuItem("Sobel Operator in YX");
        sobelYX.addActionListener(this);
        HighPass.add(partDifX);
        HighPass.add(partDifY);
        HighPass.add(enPartDifX);
        HighPass.add(enPartDifY);
        HighPass.add(sobelX);
        HighPass.add(sobelY);
        HighPass.add(sobelXY);
        HighPass.add(sobelYX);

        about = new JMenuItem("About...");
        about.addActionListener(this);
        Help.add(about);
        // bu frame'in menusu budur
        frame.setJMenuBar(menuBar);
        
        //5. Size the frame.
        frame.setSize(400, 400);
        //6. Show it.
        frame.setVisible(true);
       /* JDesktopPane desktop = new JDesktopPane();
        desktop.setOpaque(true);
        frame.getContentPane().add(desktop, BorderLayout.CENTER);
     */
        desktop = new JDesktopPane();
        desktop.setOpaque(true);
        frame.setContentPane(desktop);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // formaliteden classı çağırmak
        guimenu a = new guimenu();

    }
    
    public void showImage(BufferedImage buf){

        //if(buf==null) System.exit(1);
        imagePanel = new ImagePanel(desktop,"resimgöster",buf);
        imagePanel.setSize(buf.getWidth(),buf.getHeight());
        //this.imagePanel.setVisible(true);
                
    }
    
    public void actionPerformed(ActionEvent e) {
        //System.out.println("bastik");   //open tusunu kontrol icin
        
        Object source = e.getSource();
        
        if (source == open){
        JFileChooser chooser = new JFileChooser();  //file chooserımızı yarattık
        chooser.showOpenDialog(frame);              //file chooserimizi açtık
        System.out.println(chooser.getSelectedFile().getPath());//yolumuzu yazdıralım bakalım
        File file = new File(chooser.getSelectedFile().getPath());//yolumuzu dosya olarak kaydedelim
        try {
            theimage = ImageIO.read(file);//bu yoldaki goruntuyu okumaya calıs
        } catch (IOException ex) {
            Logger.getLogger(guimenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // doğru okumuşmuyuz çözünürlk ile test et
        // System.out.println("Çözünürlük: "+theimage.getWidth()+"*"+theimage.getHeight());
        
        showImage(theimage);
        //int[][] deneyis;
        theImageArray = image2matrix(theimage);

        } else if (source == save){
        } else if (source == saveas){
        } else if (source == print){
        } else if (source == exit){
            System.exit(0);
        } else if (source == brighter){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n];
            
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = (int)((float)theImageArray[i][j]*8/5.f);
                    if (filtered[i][j]>255) filtered[i][j]=255;
                    if (filtered[i][j]<0) filtered[i][j]=0;
                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);            
        } else if (source == darker){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n];
            
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = (int)((float)theImageArray[i][j]*5/8.f);
                    if (filtered[i][j]>255) filtered[i][j]=255;
                    if (filtered[i][j]<0) filtered[i][j]=0;
                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);     
            
        } else if (source == histEq){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n];
            int totalPixel = m*n;
            int[] NormalisedHistogram = new int[256];
            int[] CDF = new int[256];
            
            // önce NormlisedHistogram'ı bulalım
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    NormalisedHistogram[theImageArray[i][j]]=NormalisedHistogram[theImageArray[i][j]]+1;
                }
            }
            // cumulative distribution function'ı bulalım.
            CDF[0] = NormalisedHistogram[0];
            for (int i=1; i<256; i++){
                CDF[i]=NormalisedHistogram[i]+CDF[i-1];
            }
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = (int)((((float)CDF[theImageArray[i][j]]-1)/(float)(totalPixel-1))*255);
                    if (filtered[i][j]>255) filtered[i][j]=255;
                    if (filtered[i][j]<0) filtered[i][j]=0;
                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == negative){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n];
            
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = 255-theImageArray[i][j];

                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
        } else if (source == squareRoot){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n]; 
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = (int)(Math.sqrt((double)(theImageArray[i][j]*255)));
                    if (filtered[i][j]>255) filtered[i][j]=255;
                    if (filtered[i][j]<0) filtered[i][j]=0;
                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
                    
        } else if (source == squareTransfer){
            int m = theImageArray.length;
            int n = theImageArray[0].length;
            int[][] filtered = new int[m][n]; 
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    filtered[i][j] = (int)((theImageArray[i][j]*theImageArray[i][j])/255);
                    if (filtered[i][j]>255) filtered[i][j]=255;
                    if (filtered[i][j]<0) filtered[i][j]=0;
                }
            }
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
        } else if (source == average){
            float averageKernel[][] = {
                { 1/9.f, 1/9.f, 1/9.f },
                { 1/9.f, 1/9.f, 1/9.f },
                { 1/9.f, 1/9.f, 1/9.f },
            };
            int[][] filtered = myFilter(theImageArray, averageKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);

        } else if (source == lowpass){
            float lowpassKernel[][] = {
                { 1/16.f, 2/16.f, 1/16.f },
                { 2/16.f, 4/16.f, 2/16.f },
                { 1/16.f, 2/16.f, 1/16.f },
            };
            int[][] filtered = myFilter(theImageArray, lowpassKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == laplacian){
            float laplacianKernel[][] = {
                { 1.f, 1.f, 1.f },
                { 1.f, -8.f, 1.f },
                { 1.f, 1.f, 1.f },
            };
            int[][] filtered = myFilter(theImageArray, laplacianKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == median){
            
        } else if (source == partDifX){
            float partDifXKernel[][] = {
                { 0.f, 0.f, 0.f },
                { 0.f, -1.f, 1.f },
                { 0.f, 0.f, 0.f },
            };
            int[][] filtered = myFilter(theImageArray, partDifXKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == partDifY){
            float partDifYKernel[][] = {
                { 0.f, 1.f, 0.f },
                { 0.f, -1.f, 0.f },
                { 0.f, 0.f, 0.f },
            };
            int[][] filtered = myFilter(theImageArray, partDifYKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == enPartDifX){
            float enPartDifXKernel[][] = {
                { 0.f, 0.f, 0.f },
                { -1.f, 0.f, 1.f },
                { 0.f, 0.f, 0.f },
            };
            int[][] filtered = myFilter(theImageArray, enPartDifXKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);

            
        } else if (source == enPartDifY){
            float enPartDifYKernel[][] = {
                { 0.f, 1.f, 0.f },
                { 0.f, 0.f, 0.f },
                { 0.f, -1.f, 0.f },
            };
            int[][] filtered = myFilter(theImageArray, enPartDifYKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == sobelX){
            float sobelXKernel[][] = {
                { -1.f, 0.f, 1.f },
                { -2.f, 0.f, 2.f },
                { -1.f, 0.f, 1.f },
            };
            int[][] filtered = myFilter(theImageArray, sobelXKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == sobelY){
            float sobelYKernel[][] = {
                { 1.f, 2.f, 1.f },
                { 0.f, 0.f, 0.f },
                { -1.f, -2.f, -1.f },
            };
            int[][] filtered = myFilter(theImageArray, sobelYKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == sobelXY){
            float sobelXYKernel[][] = {
                { 0.f, 1.f, 2.f },
                { -1.f, 0.f, 1.f },
                { -2.f, -1.f, 0.f },
            };
            int[][] filtered = myFilter(theImageArray, sobelXYKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if (source == sobelYX){
            float sobelYXKernel[][] = {
                { 2.f, 1.f, 0.f },
                { 1.f, 0.f, -1.f },
                { 0.f, -1.f, 2.f },
            };
            int[][] filtered = myFilter(theImageArray, sobelYXKernel);
            BufferedImage showableImage = imageMaker(filtered);
            showImage(showableImage);
            
        } else if(source == about){
            JOptionPane.showMessageDialog(frame, "This software was produced by Aydoğan Karagöz.\nFor any question, suggestion or request of source codes,\nplease contact to aydogan.karagoz@gmail.com");
        }
        }

}