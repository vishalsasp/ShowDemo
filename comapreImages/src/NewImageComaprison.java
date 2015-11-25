import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NewImageComaprison 
{
	 public boolean compareTwoImages(File fileOne, File  fileTwo) {
	        Boolean isTrue = true;
	        int temp=0;
	        int a=0;
	        int b=0;
	        try{
	        	System.out.println("I am here");
	            Image imgOne = ImageIO.read(fileOne);
	            Image imgTwo = ImageIO.read(fileTwo);
	            BufferedImage bufImgOne = ImageIO.read(fileOne);
	            BufferedImage bufImgTwo = ImageIO.read(fileTwo);
	            int imgOneHt = bufImgOne.getHeight();
	            int imgTwoHt = bufImgTwo.getHeight();
	            int imgOneWt = bufImgOne.getWidth();
	            int imgTwoWt = bufImgTwo.getWidth();
	            System.out.println("The all coordinations are "+imgOneHt+" "+imgTwoHt+" "+imgOneWt+" "+imgTwoWt);
	            if(imgOneHt!=imgTwoHt ||(imgOneWt!=imgTwoWt))
	            {
	                System.out.println(" size are not equal ");
	                isTrue = false;
	                return false;
	            }

	            if(imgOneHt>imgOneWt)
	            {
	            	a=imgOneHt;
	            	b=imgOneWt;
	            }
	            else
	            {
	            	a=imgOneWt;
	            	b=imgOneHt;
	            }
	            
	            for(int x=0; x<a; x++ ){
	                for(int y=0; y<b; y++){
	                    if(bufImgOne.getRGB(x, y) != bufImgTwo.getRGB(x, y) ){
	                    	temp++; 
	                       isTrue = false;
	                        break;
	                        
	                    }
	                }
	            }
     
	            
	            if(temp!=0)
	            {
	            	
	            	System.out.println("Images are not Similar");
	            	
	            }
	            
	            else
	            {
	            	System.out.println("Images are simislar");
	            	
	            	
	            }	
	            	
         }catch (IOException e)
            {
                  e.printStackTrace();
                  return false;
            }
			return isTrue;
       
    }
  
  
    public static void main(String[] softwareEngineer) {
      
        File OracleJava = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\abc.png");
        File javaOracle = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\Tulips.jpg");
        //File Three = new File("C:\\EclipseJDk\\comapreImages\\src\\comapreImages\\images\\xyz.jpg");
        NewImageComaprison imgComp = new NewImageComaprison();
        System.out.println(imgComp.compareTwoImages( OracleJava , javaOracle));
      
    }


	
    
	
}
