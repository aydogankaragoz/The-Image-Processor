package guiandmenu;

import java.awt.image.BufferedImage;

/**
 *
 * @author HP
 */
public class DataTransformers {
    byte pix[] = new byte[1];
    
    public int[][] image2matrix(BufferedImage bufferedImage){
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        int fixed_data;
        
        int[][] thematrix = new int[imageWidth][imageHeight];
        
        for (int i=0; i<imageWidth; i++){
            for (int j=0; j<imageHeight; j++){
                bufferedImage.getRaster().getDataElements(i, j, pix);
                fixed_data = ((int) pix[0]) & 0xff;
                thematrix[i][j] = fixed_data;
            }
        }  
        return thematrix;
    }
    public int[][] myFilter(int data[][], float kernel[][]){
        int m = data.length;
        int n = data[0].length;
        int k = kernel.length;
        k = (k-1)/2;
        float temp;
        int filtered[][] = new int[m][n];
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                for (int ii=i-k; ii<=i+k; ii++){
                    for (int jj=j-k; jj<=j+k; jj++){
                        if(ii<0 || jj<0 || ii>=m || jj>=n){
                            temp = 0.f;
                        }
                        else{
                            temp = (float)data[ii][jj]*kernel[ii-(i-k)][jj-(j-k)];
                            //if (temp<0) temp=0;
                        }
                        filtered[i][j] += (int)temp;
                    }
                }
            }

        }
        
        return filtered;
    }
    public BufferedImage imageMaker(int theArray[][]){
        BufferedImage viewableImage;
        viewableImage = new BufferedImage(theArray.length, theArray[0].length, BufferedImage.TYPE_BYTE_GRAY);
		// switch to new pixel data
	for (int i = 0; i < theArray.length; i++) {
		for (int j = 0; j < theArray[0].length; j++) {
			int r = theArray[i][j];
			viewableImage.setRGB(i, j, 0xff000000 | (r << 16) | (r << 8) | r);
		}
	}        
        return viewableImage;
        
    }

}
