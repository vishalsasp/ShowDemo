import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class getDimension
{

	
		
		

		    public static void getImageDim() throws IOException 
		    {
		    	File expImageFile = new File("C:\\EclipseJDk\\comapreImages\\src\\comapreImages\\images\\image2.png");
				ImageInputStream in;
				ImageReader reader = null;
				try {
				    in = ImageIO.createImageInputStream(expImageFile);
				    final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
				    if (readers.hasNext()) {
					reader = readers.next();
					reader.setInput(in);
				    }
				} catch (Exception e) {
				   System.out.println("Exception occurred: " + e);
				} finally {
				    reader.dispose();
				}
				  
				System.out.println(new Dimension(reader.getWidth(0), reader.getHeight(0)));
				//return new Dimension(reader.getWidth(0), reader.getHeight(0));
			    }
		
		
		
		
		
		public static void main(String args[]) throws IOException
		{
			
			getImageDim();
			
			
		}

	
	
	
}
