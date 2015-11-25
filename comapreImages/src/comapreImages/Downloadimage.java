package comapreImages;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
public class Downloadimage {

	public static File destinationFile1;
	public static File destinationFile2;
	public static boolean verification;
	
	public static void main(String[] args) throws IOException
	{
		
		     String imageUrl1 = "http://www.trbimg.tribstage.com/img-56320036/turbine/1639";
		     String imageUrl2= "http://www.trbimg.tribstage.com/img-56320044/turbine/2663";
		   
		        destinationFile1 = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\image1.png");
			    destinationFile2 =new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\selenium\\image2.png");
		       saveImage(imageUrl1, destinationFile1);
		       saveImage(imageUrl2, destinationFile2);
		       verification= Imagecomapre.compareImage(destinationFile1,destinationFile2);
		       System.out.println(verification);
		 
		
	}

	public static void saveImage(String imageUrl, File destinationFile) throws IOException {
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

}
