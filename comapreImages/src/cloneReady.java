import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;

public class cloneReady 
{
	 static File destinationFile1=null;
	 static File destinationFile2=null;
	 static File destinationFile3=null;
	 Dimension expImgDim, actImgDim;
	 File imageDir = null;
	 File diffimageDir = null;
	
	 public static  boolean cloneComapreimage(String sourceURL,String cloneURL,String fileName) throws IOException
     {
    	try{
   	    String[] photoIDtype = fileName.split("\\.");
 		String photoType = photoIDtype[1];
        File destinationFile1 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\image1."+photoType);
 	    File destinationFile2 =new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\image2."+ photoType);
 	    File destinationFile3=  new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\image3."+ photoType);
 	    saveImage(sourceURL,destinationFile1);
 	    saveImage(cloneURL,destinationFile2);
 	    comparision(destinationFile1,destinationFile2,destinationFile3);
 	    System.out.println(destinationFile1.getName());
 		System.out.println(destinationFile2.getName());
		return true;
    	}
    	
    	catch(Exception e)
    	{
    		
    		System.out.println(e.getMessage());
    		return false;
    	}
     }
	 
	 public static void saveImage(String imageUrl, File destinationFile) throws IOException {
		try{
		 
		 URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		}
		
		catch(Exception e)
		{
			 System.out.println(e.getMessage());
			
			
		}
	 
	 }
	 
	 
	 
	 
	  public static void comparision(File  destinationFile1,File  destinationFile2,File  destinationFile3)
	  
	  {
		  Dimension expImgDim, actImgDim;
			try {
			    File expImgFile = destinationFile1;
			    File actImgFile = destinationFile2;
 			    File diffImgFile = destinationFile3;

			    int sample = 0;

			    if (expImgFile.exists()) 
			    {

				    expImgDim = getImageDim(expImgFile);
				    actImgDim = getImageDim(actImgFile);
				   
				   System.out.println("This is expected image"+expImgDim);
				   System.out.println("This is actualImag Dime"+ actImgDim);

			       BufferedImage expBuffImg = ImageIO.read(expImgFile);
				   BufferedImage actBuffImg = ImageIO.read(actImgFile);

				         if (expBuffImg.getHeight() != actBuffImg.getHeight()
					        || expBuffImg.getWidth() > actBuffImg.getWidth())
				             {
				                 if (expBuffImg.getHeight() > actBuffImg.getHeight()) 
				                 {
					              FileUtils.copyFile(expImgFile, diffImgFile);
				          
				                 } 
				                 else 
				                 {
				         	     FileUtils.copyFile(actImgFile, diffImgFile);
				                 }
				                       int loopwidth = expBuffImg.getWidth();
				                       int loopheight = expBuffImg.getHeight();
				                        if (expBuffImg.getWidth() > actBuffImg.getWidth()) 
				                        {
					                       loopwidth = actBuffImg.getWidth();
				                        }
				                        if (expBuffImg.getHeight() > actBuffImg.getHeight())
				                         {
					                        loopheight = actBuffImg.getHeight();
				                         }
				                            System.out.println((String.valueOf(loopwidth)));
				                            System.out.println((String.valueOf(loopheight)));
		             		                BufferedImage diffBuffImg = ImageIO.read(diffImgFile);
				                            System.out.println("Comparing Started....");
				                   for (int i = 0; i < loopwidth; i++) 
				                   {
					                  for (int j = 100; j < loopheight; j++) 
					                  {
					                   if (!(expBuffImg.getRGB(i, j) == actBuffImg.getRGB(
						                i, j))) 
					                    {
						                  diffBuffImg.setRGB(i, j, -147220175);
						                  diffBuffImg.flush();
						                  sample++;
					                     }
					                      if (j == loopheight - 1) 
					                      {
						                   diffBuffImg.setRGB(i, j, -147220175);
					                       }
					   }
				    }
				     if(sample!=0)
				     {
				    FileOutputStream strm = new FileOutputStream(diffImgFile);
				    ImageIO.write(diffBuffImg, "png", strm);
				    strm.close();
				     }
				     else
				     {
				    	 
				       System.out.println("first loop images are similar");	 
				    	 
				     }
				} else {
				    FileUtils.copyFile(expImgFile, diffImgFile);

				    BufferedImage diffBuffImg = ImageIO.read(diffImgFile);


				    System.out.println("Comparing Started....");
				    for (int i = 0; i < expBuffImg.getWidth(); i++) 
				    {
					for (int j = 100; j < expBuffImg.getHeight(); j++) 
					    {
					    if (!(expBuffImg.getRGB(i, j) == actBuffImg.getRGB(
						    i, j))) {
						diffBuffImg.setRGB(i, j, -147220175);
						diffBuffImg.flush();
						sample++;
					    }
					}
				    }
				    if(sample!=0)
				    {
				    System.out.println("This is sample value of second loop shows differences"+sample);
				    FileOutputStream strm = new FileOutputStream(diffImgFile);
				    ImageIO.write(diffBuffImg, "png", strm);
				    strm.close();
				    }
				    else
				    {
				    	System.out.println(sample);
				    	System.out.println("Images are similar");
				    	
				    }
				}
			    } else {
				System.out.println("Expected Image is not present at location:-  "
					);
				System.out.println("Expected Image is not present at location:-  "
					);
			    }
			    if (sample != 0) 
			    {
				if (diffImgFile.exists()) 
				   {
				    System.out.println("There is diff in actual and expected images.");
				    System.out.println("Mismatch in the Actual and Expected Image: ");

				   } else 
				   {
	                  System.out.println("i m here");			    
			       } 
				 
				   }
			    if (actImgFile.exists() && !expImgFile.exists()) {
				actImgFile.renameTo(expImgFile);
				System.out.println("Expected screenshot not found.\n New expected screenshot created: "
					+ expImgFile);
				System.out.println("Expected screenshot not found.\n New expected screenshot created: ");
				}

			} catch (Exception e) {
			    // exceptionMessage(e);
			    System.out.println("Exception occurred: " + e);
			    System.out.println("Unexpected error occurred:" + e.getMessage());
			}
		
			
	  }
	  
	  
	  
			   
	  public static Dimension getImageDim(File expImgFile) throws IOException {
			ImageInputStream in;
			ImageReader reader = null;
			try {
			    in = ImageIO.createImageInputStream(expImgFile);
			    final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
			    if (readers.hasNext()) {
				reader = readers.next();
				reader.setInput(in);
			    }
			} catch (Exception e) {
			   System.out.println("Exception occurred: " + e);
			} finally {
			   // reader.dispose();
			}
			
			return new Dimension(reader.getWidth(0), reader.getHeight(0));
		    }
	  
	  
	  
	  
	  
	  
	  
}
      
      
      
      
      
      
      
      
     	
			 
     

        
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
	
	


