package info.jubeat.saucer;

import info.jubeat.privacy.PrivacyInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
/* form based login */
public class HtmlDown {
	private String jmainPage = "";
	private String loginPage = "https://p.eagate.573.jp/gate/p/login.html";
	private String KID = PrivacyInfo.ID;
	private String pass = PrivacyInfo.pw;
	private DefaultHttpClient httpClient= null;
	private boolean isSession = false;
	
	public HtmlDown() {
		httpClient = new DefaultHttpClient();
	}
	
	public HtmlDown(String url) {
		jmainPage = url;
		httpClient = new DefaultHttpClient();
	} 
	
	public HtmlDown(String url, String id, String pw) {
		jmainPage = url;
		KID = id;
		pass = pw;
		httpClient = new DefaultHttpClient();
	}
	
	public HtmlDown setUrl(String url) {
		jmainPage = url;
		return this;
	}
	
	public HtmlDown setAccount(String id, String pw) {
		KID = id;
		pass = pw;
		return this;
	}
	
	// 1. prelogin : get cookie
	private boolean preLogin() {
		HttpGet httpGet = new HttpGet(loginPage);
		HttpResponse response = null;
		HttpEntity entity = null;
		List<Cookie> cookies = null;
		
		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			System.out.println("get cookie : " + response.getStatusLine());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { EntityUtils.consume(entity); }
			catch (IOException e) { e.printStackTrace(); }
			httpGet.abort();
		}

		cookies = httpClient.getCookieStore().getCookies();
		
		if(cookies.size() != 0)
			isSession = true;
		

		/* print cookie */
		if (cookies.isEmpty())
			System.out.println("None");
		else
			for (int i = 0; i < cookies.size(); i++)
				System.out.println("- " + cookies.get(i).toString());
		/* */
		return isSession;
	}
	
	// 2. login to eagete using http post method.
	public HtmlDown login() {
		if(!preLogin())
			System.out.println("session inactivity.");
		else if(KID.equals(""))
			System.out.println("not setting ID");
		else if(pass.equals(""))
			System.out.println("not setting pass");
		
		HttpPost httpost = new HttpPost(loginPage);
		HttpResponse response = null;
		HttpEntity entity = null;

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("KID", KID));
		nvps.add(new BasicNameValuePair("pass", pass));
		nvps.add(new BasicNameValuePair("OTP", ""));
		
		// get login session
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpClient.execute(httpost);
			entity = response.getEntity();
			System.out.println("get login session : " + response.getStatusLine());
			
			EntityUtils.consume(entity);
		} catch (UnsupportedEncodingException e) { e.printStackTrace();
		} catch(IOException e) { e.printStackTrace();
		}
		return this;
	}
	
	// 3. redirect main page and return http entity.
	private InputStream getDataStream() {
		if(httpClient == null)	return null;
		
		/* redirect *
		int statusCode = login();
		
		if(statusCode == 301 || statusCode == 302)
			System.out.println("status code = " + statusCode + ". perform redirection.");
		else
			return null;
		/* */
		HttpResponse response = null;
		HttpGet httpGet = new HttpGet(jmainPage);
		try {
			response = httpClient.execute(httpGet);
			System.out.println("Content-Length : " + response.getFirstHeader("Content-Length").getValue());
			
			/* check charset in http header *
			Header[] header = response.getAllHeaders();
			
			for(Header h : header) {
				System.out.println(h.getName() + " : " + h.getValue());
			}
			/* */
			
			return response.getEntity().getContent();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getData() {
		String result = "";
		
		InputStream istream = null;
		BufferedReader br = null;
		
		try {
			istream = getDataStream();
			// page charset : Windows-31J (=MS932). via http header : Content-type
			// explain for provide java encoding list (korean translation)
			// http://xrath.com/javase/ko/6/docs/ko/technotes/guides/intl/encoding.doc.html
			br = new BufferedReader(new InputStreamReader(istream, "MS932"));
			
			String line;
			while ((line = br.readLine()) != null)
				result = result + line + "\n";
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				istream.close();
				br.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
		/* *
		System.out.println("---------------content------------------");
		System.out.print(result);
		System.out.println("----------------------------------------");
		/* */
		return result;
	}
	
	public void close() {
		httpClient.getConnectionManager().shutdown();
	}
}