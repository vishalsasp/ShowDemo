package comapreImages;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

public class TempPost {
	public static  String abc2;
	 private static final String JSON_HEIGHT_KEY = "height";
	    private static final String JSON_WIDTH_KEY = "width";
	    private static final String JSON_SIZE_KEY = "size";
	    private static final String JSON_URL_KEY = "url";
	    private static final String JSON_NAMESPACE_KEY = "namespace";
	    private static final String JSON_SLUG_KEY = "slug";
	    static HttpClient client = new HttpClient();
	    static HttpResponse httpResponse = null;
	    static JSONObject httpResponseJSON = null;
	    String responseString = null;
	    static String mimeType = null;
	    static int statusCode = 0;
	    static String photoID = null;
	    static String sourceURL = null;
	    public static boolean verification;

	 public static String randomNameGenerator() throws Exception {

			Random rand = new Random();
			String randomName = "";
			int n = rand.nextInt(10000) + 1;
			randomName = n + "";

			return randomName;
	
	 }
	
	
	
	 private static void setRequestHeaders(HttpMethod method, String bearer,
			    String auth) {

			method.addRequestHeader("Authorization", bearer + ' ' + auth);
			method.addRequestHeader("Accept", "application/json");
			//method.addRequestHeader("Content-type","Application/json");
			method.addRequestHeader("Accept-Encoding", "gzip, deflate");
		    }
	
	
	
	
	 public static boolean postRequest(String url, String namespace, String bearer,
			    String auth, String fileName) throws ClientProtocolException,
			    IOException {
		    
		   
			PostMethod post = new PostMethod(url);
			try {
			    setRequestHeaders(post, bearer, auth);
			    System.out.println(fileName);
			    
			    String file1=fileName;
			    String[] abc = file1.split(Pattern.quote("."));
			    abc2=abc[1];
			    System.out.println(abc2);
			    
			    StringPart namespacePart = new StringPart("photo[namespace]",
				    namespace);
			        
			    StringPart slugPart = new StringPart("photo[slug]",
				    randomNameGenerator());
			    
			    System.out.println( randomNameGenerator());
			    // next two lines annoying work around for this bug:
			    // https://github.com/rack/rack/issues/186
			    namespacePart.setContentType(null);
			    slugPart.setContentType(null);
			    
			    File f = new File("C:\\EclipseJDk\\comapreImages\\src\\comapreImages\\images\\"+ fileName);
			    System.out.println(f);
//			    Part[] parts = {
//				    namespacePart,
//				    slugPart,
//				    new FilePart("photo[file]", f, "image/png",
//					    FilePart.DEFAULT_CHARSET) };
//			    
			    Part[] parts = {
					    namespacePart,
					    slugPart,new FilePart("photo[file]",f,"image/png",FilePart.DEFAULT_CHARSET)};
				    
			    
			    
			    post.setRequestEntity(new MultipartRequestEntity(parts, post
				    .getParams()));    
			    statusCode = client.executeMethod(post);
			    System.out.println(statusCode);
//			    String httpResponseString = post.getResponseBodyAsString();
//		        httpResponseJSON = new JSONObject(httpResponseString);
//		       
//			    String photoIdJSONValue = httpResponseJSON.getString("id");
//			    
//			 	String[] photoIDArray = photoIdJSONValue.split("/");
//			    photoID = photoIDArray[1];
//			    sourceURL=httpResponseJSON.getString("url");
			    return true;
			} catch (Exception e) {
			    System.out.println("This is errror code"+e.getMessage());
			    return false;
			} finally {
			    // Release the connection.
			    post.releaseConnection();
			}
		    }
					
			
	
}
