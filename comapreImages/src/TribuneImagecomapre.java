import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;

public class TribuneImagecomapre 
{
	Dimension expImgDim, actImgDim;
	File imageDir = null;
	File diffimageDir = null;
	public static void main(String args[])
	{
		
		Dimension expImgDim, actImgDim;
		try {
		    File expImgFile = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.png");
		    File actImgFile = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\pngimage.png");
		    File diffImgFile = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\test.png");

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
				                  for (int j = 0; j < loopheight; j++) 
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
			    FileOutputStream strm = new FileOutputStream(diffImgFile);
			    ImageIO.write(diffBuffImg, "png", strm);
			    strm.close();
			} else {
			    FileUtils.copyFile(expImgFile, diffImgFile);

			    BufferedImage diffBuffImg = ImageIO.read(diffImgFile);


			    System.out.println("Comparing Started....");
			    for (int i = 0; i < expBuffImg.getWidth(); i++) {
				for (int j = 100; j < expBuffImg.getHeight(); j++) {
				    if (!(expBuffImg.getRGB(i, j) == actBuffImg.getRGB(
					    i, j))) {
					diffBuffImg.setRGB(i, j, -147220175);
					diffBuffImg.flush();
					sample++;
				    }
				}
			    }
			    FileOutputStream strm = new FileOutputStream(diffImgFile);
			    ImageIO.write(diffBuffImg, "png", strm);
			    strm.close();
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
