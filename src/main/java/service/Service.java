package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Service {
	//psot�����绰���������
	public static String getinformationpost(String tel) throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String content="";
		HttpPost httpPost=new HttpPost("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm");
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("tel", tel));
		//����ʵ��
		UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(parameters,"utf-8");
		httpPost.setEntity(formEntity);
		
		CloseableHttpResponse response=null;
		try {
			response=httpclient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200) {
				content=EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(response!=null) {
				response.close();
			}
			httpclient.close();
		}
		return content;
	}
	//����Ԥ��
	public static String gettianqi(String city) throws Exception{
		String tianqi="";
		String AppKey="a79ad620486e9db6d35956e4567838d5";
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost("http://op.juhe.cn/onebox/weather/query");
		
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("cityname", city));
		parameters.add(new BasicNameValuePair("key", AppKey));
		
		//����ʵ��
		UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(parameters,"utf-8");
		httpPost.setEntity(formEntity);
		CloseableHttpResponse response=null;
		try {
			response=httpclient.execute(httpPost);
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200) {
				tianqi=EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(tianqi);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(response!=null) {
				response.close();
			}
			httpclient.close();
		}
		return tianqi;
	}
	
	//ip��ַ��ѯ
	public static String getip(String ip) throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String AppKey="7f13ab7ce0713fb598a3b53bb9fd8571";
		String content="";
		HttpPost httpPost=new HttpPost("http://apis.juhe.cn/ip/ipNew");
		//URIBuilder builder=new URIBuilder("http://apis.juhe.cn/ip/ipNew");
		//���ò���
		
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("ip", ip));
		parameters.add(new BasicNameValuePair("key",AppKey));
		//����ʵ��
		UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(parameters,"utf-8");
		httpPost.setEntity(formEntity);
				
		CloseableHttpResponse response=null;
		try {
			response=httpclient.execute(httpPost);
			if(response.getStatusLine().getStatusCode()==200) {
				content=EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(response!=null) {
				response.close();
			}
			httpclient.close();
		}
		return content;
	}
	
	//����ͷ����ѯ
	public static String getPress() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String AppKey="92fd2d3f3745a65ecd0ac39cfffb312e";
		String content="";
		HttpPost httpPost=new HttpPost("http://v.juhe.cn/toutiao/index");
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("key",AppKey));
		//parameters.add(new BasicNameValuePair("type","top"));
		// ����ʵ��
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
		httpPost.setEntity(formEntity);
		CloseableHttpResponse response=null;
		try {
			response=httpclient.execute(httpPost);
			//System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200) {
				content=EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(response!=null) {
				response.close();
			}
			httpclient.close();
		}
		return content;
	}
	
	//��Ӣ��˫����
	public static String translate(String word,String type1,String type2) throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		String AppKey="ki5zj6k0awM3goaYJ4X4";
		String appid="20200610000491551";
		String content="";
		//Scanner scan=new Scanner(System.in);
		//System.out.println("��������Ҫ���������:");
		//String q=scan.nextLine();
		
		String salt = Salt();
		//System.out.println(salt);
		//HttpPost httpPost=new HttpPost("https://api.fanyi.baidu.com/v2transapi");
		HttpPost httpPost=new HttpPost("http://api.fanyi.baidu.com/api/trans/vip/translate");
		//https://api.fanyi.baidu.com/
		//System.out.println("1");
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("q",word));
		parameters.add(new BasicNameValuePair("from",type1));
		parameters.add(new BasicNameValuePair("to",type2));
		parameters.add(new BasicNameValuePair("appid",appid));
		parameters.add(new BasicNameValuePair("salt",salt));
		//20200610000491551����7679167956ki5zj6k0awM3goaYJ4X4
		//MD5����
		String src=appid+word+salt+AppKey;
		String sign=MD5(src);
		
		//System.out.println(sign);
		parameters.add(new BasicNameValuePair("sign",sign));
		
		//parameters.add(new BasicNameValuePair("type","top"));
		
		// ����ʵ��
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
		
		formEntity.setContentType("application/x-www-form-urlencoded");
		
		httpPost.setEntity(formEntity);
		
		CloseableHttpResponse response=null;
		try {
			response=httpclient.execute(httpPost);
			
			//System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200) {
				content=EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(content);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(response!=null) {
				response.close();
			}
			httpclient.close();
		}
		return content;
	}
	//�����
	public static String Salt() {
		String result;
		String text="0123456789";
		StringBuffer salt=new StringBuffer();
		char[] m=text.toCharArray();
		for(int i=0;i<10;i++) {
			char c=m[(int) (Math.random()*10)];
			salt=salt.append(c);
		}
		return salt.toString();
	}
	//MD5����
	public static String MD5(String s) {
		return DigestUtils.md5Hex(s);
	}
}
