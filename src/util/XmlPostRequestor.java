package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public abstract class XmlPostRequestor {

	protected String _baseUrl;
	
	protected List<NameValuePair> _formParams;
	
	protected HttpClient _httpClient;
	
	public XmlPostRequestor(){
		_httpClient = new DefaultHttpClient();
		_formParams = new ArrayList<NameValuePair>();
	}
	
	protected void setSecure() throws IOException{

        //sets up parameters
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "utf-8");
        params.setBooleanParameter("http.protocol.expect-continue", false);

        //registers schemes for both http and https
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
//        sslSocketFactory.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        registry.register(new Scheme("https", 443, sslSocketFactory));

        ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(registry);
        _httpClient = new DefaultHttpClient(manager, params);
	}
	
	protected void addParam(String key, String value){
		_formParams.add(new BasicNameValuePair(key, value));
	}
	
	public HttpResponse post(){
		
		HttpResponse response = null;
		
		HttpPost post = new HttpPost(_baseUrl);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(_formParams, "UTF-8");
			post.setEntity(entity);
			response = _httpClient.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	public String get_baseUrl() {
		return _baseUrl;
	}

	public void set_baseUrl(String url) {
		_baseUrl = url;
	}
	
}
