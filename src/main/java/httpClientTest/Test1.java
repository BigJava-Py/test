package httpClientTest;

import java.net.URI;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test1 {
	public static void main(String args[]) throws Exception{
		//System.out.println(md5("20200610000491551"));
		//System.out.println(MD5());
		int choose=menu();
		while(true) {
			switch(choose) {
			case 1:getinformationpost();break;
			case 2:gettianqi();break;
			case 3:getip();break;
			case 4:getPress();break;
			case 5:translate();break;
			case 6:System.out.println("�����˳�!!!");return;
			default:System.out.println("������������������1-6");
			}
			Scanner sc=new Scanner(System.in);
			choose=menu();
		}
		//getip();
		 //gettianqi();
		//getinformationpost();
		//getinformation();
	}
	//psot�����绰���������
	public static String getinformationpost() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String content="";
		HttpPost httpPost=new HttpPost("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm");
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("tel", "13871981401"));
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
	//get�绰���������
	public static void getinformation() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		
		URIBuilder builder=new URIBuilder("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm");
		builder.setParameter("tel", "13871981401");
		URI uri=builder.build();
		HttpGet httpGet=new HttpGet(uri);
		
		CloseableHttpResponse response=null;
		
		try {
			response=httpclient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==0) {
				HttpEntity entity=response.getEntity();
				String content=EntityUtils.toString(entity, "utf-8");
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
	}
	//����Ԥ��
	public static String gettianqi() throws Exception{
		String tianqi="";
		String AppKey="a79ad620486e9db6d35956e4567838d5";
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httpPost=new HttpPost("http://op.juhe.cn/onebox/weather/query");
		
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("cityname", "�人"));
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
	public static void getip() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String AppKey="7f13ab7ce0713fb598a3b53bb9fd8571";
		String content="";
		HttpPost httpPost=new HttpPost("http://apis.juhe.cn/ip/ipNew");
		//URIBuilder builder=new URIBuilder("http://apis.juhe.cn/ip/ipNew");
		//���ò���
		
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("ip", "112.112.11.11"));
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
		
	}
	//����ͷ����ѯ
	public static void getPress() throws Exception{
		
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
	}
	//��Ӣ��˫����
	public static void translate() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		
		String AppKey="ki5zj6k0awM3goaYJ4X4";
		String appid="20200610000491551";
		String content="";
		Scanner scan=new Scanner(System.in);
		System.out.println("��������Ҫ���������:");
		String q=scan.nextLine();
		
		String salt = Salt();
		System.out.println(salt);
		//HttpPost httpPost=new HttpPost("https://api.fanyi.baidu.com/v2transapi");
		HttpPost httpPost=new HttpPost("http://api.fanyi.baidu.com/api/trans/vip/translate");
		//https://api.fanyi.baidu.com/
		//System.out.println("1");
		//���ò���
		List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
		parameters.add(new BasicNameValuePair("q",q));
		parameters.add(new BasicNameValuePair("from","zh"));
		parameters.add(new BasicNameValuePair("to","en"));
		parameters.add(new BasicNameValuePair("appid",appid));
		parameters.add(new BasicNameValuePair("salt",salt));
		//20200610000491551����7679167956ki5zj6k0awM3goaYJ4X4
		//MD5����
		String src=appid+q+salt+AppKey;
		String sign=MD5(src);
		
		System.out.println(sign);
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
	}
	public static void translate2() throws Exception{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String AppKey="ki5zj6k0awM3goaYJ4X4";
		String appid="20200610000491551";
		String content="";
		Scanner scan=new Scanner(System.in);
		System.out.println("��������Ҫ���������:");
		String q=scan.nextLine();
		String salt = Salt();
		
		URIBuilder builder=new URIBuilder("http://api.fanyi.baidu.com/api/trans/vip/translate");
		builder.setParameter("q", q);
		builder.setParameter("q", q);
		URI uri=builder.build();
		HttpGet httpGet=new HttpGet(uri);
		
		httpclient.close();
	}
	public static int menu() {
		int choose;
		System.out.println("����ٱ���");
		System.out.println("1.�ֻ��Ų�ѯ�����ز�ѯ");
		System.out.println("2.����Ԥ����ѯ");
		System.out.println("3.ip��ַ��ѯ");
		System.out.println("4.����ͷ����ѯ");
		System.out.println("5.��Ӣ��˫����");
		System.out.println("6.�˳�");
		Scanner scan=new Scanner(System.in);
		System.out.println("����������ѡ��1-6");
		choose=scan.nextInt();
		return choose;
	}
	//md5����
	public static String md511(byte[] bytes) {
		String text="";
		for(int i=0;i<bytes.length;i++) {
			int temp=bytes[i] & 0xff;
			String tempHex=Integer.toHexString(temp);
			if(tempHex.length()<2) {
				text +="0"+tempHex;
			}
			else {
				text += tempHex;
			}
		}
		return text;
	}
	public static String md5(String message) {
		String result="";
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			byte[] encodeMd5Digest =md5Digest.digest(message.getBytes());
			result=md511(encodeMd5Digest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
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
	public static String MD5(String s) {
		
		return DigestUtils.md5Hex(s);
		
	}

}
