package guiandmenu;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;



public class ImagePanel extends JInternalFrame{

	BufferedImage bufferedimage;
	
	public ImagePanel(JDesktopPane parent, String title, BufferedImage bufferedimage){

                JInternalFrame internalFrame = new JInternalFrame(title,true, true, true, true);
                JLabel label = new JLabel(new ImageIcon(bufferedimage));
                internalFrame.getContentPane().add(label);
                internalFrame.setSize(bufferedimage.getWidth(), bufferedimage.getHeight());
                parent.add(internalFrame);
                internalFrame.setVisible(true);
        };


	public void activateListener(){
		
	}

        public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


