package service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ManageString {
	
	public static void telStr(String str) throws Exception{
		try {
			String JsonStr=str.substring(str.indexOf("=")+1).trim();
			JsonParser parser=new JsonParser();
			JsonElement element=parser.parse(JsonStr);
			if(element.isJsonObject()) {
				JsonObject object=element.getAsJsonObject();
				//取出对应的字段
				String province=object.get("province").getAsString();
				String catName=object.get("catName").getAsString();
				System.out.println(province+","+catName);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void ipStr(String str) throws Exception{
		JsonParser parser=new JsonParser();
		JsonElement element=parser.parse(str);
		try {
			if(element.isJsonObject()) {
				JsonObject object=element.getAsJsonObject();
				//取出对应的字段
				JsonObject object2=object.get("result").getAsJsonObject();
				String Country=object2.get("Country").getAsString();
				String Province=object2.get("Province").getAsString();
				String City=object2.get("City").getAsString();
				String Isp=object2.get("Isp").getAsString();
				System.out.println(Country+","+Province+","+City+","+Isp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void weatherStr(String str) throws Exception{
		ArrayList<String> strlist=new ArrayList<String>(); 
		JsonParser parser=new JsonParser();
		JsonElement element=parser.parse(str);
		try {
			if(element.isJsonObject()) {
				JsonObject object=element.getAsJsonObject();
				JsonObject object2=object.get("result").getAsJsonObject();
				JsonObject object3=object2.get("data").getAsJsonObject();
				JsonArray array=object3.get("weather").getAsJsonArray();
				//JsonArray array2=array.get(1).getAsJsonArray();
				for(int i=0;i<array.size();i++) {
					String message="";
					JsonObject object4=array.get(i).getAsJsonObject();
					String date=object4.get("date").getAsString();
					JsonObject object5=object4.get("info").getAsJsonObject();
					JsonArray array3=object5.get("day").getAsJsonArray();
					for(int j=1;j<5;j++) {
						String s=array3.get(j).getAsString();
						s=s+",";
						message=message+s;
					}
					message=message+date;
					strlist.add(message);
				}
			} 
			for(String sss:strlist) {
				System.out.println(sss);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newsStr(String str) throws Exception{
		JsonParser parser=new JsonParser();
		JsonElement element=parser.parse(str);
		try {
			if(element.isJsonObject()) {
				JsonObject object=element.getAsJsonObject();
				JsonObject object2=object.get("result").getAsJsonObject();
				JsonArray array=object2.get("data").getAsJsonArray();
				for(int i=0;i<array.size();i++) {
					JsonObject object3=array.get(i).getAsJsonObject();
					String title=object3.get("title").getAsString();
					String date=object3.get("date").getAsString();
					String author=object3.get("author_name").getAsString();
					System.out.println("标题:"+title);
					System.out.println("日期:"+date);
					System.out.println(author);
					System.out.println();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void translateStr(String str) throws Exception {
		try {
			JsonParser parser=new JsonParser();
			JsonElement element=parser.parse(str);
			if(element.isJsonObject()) {
				JsonObject object=element.getAsJsonObject();
				JsonArray array=object.get("trans_result").getAsJsonArray();
				JsonObject object2=array.get(0).getAsJsonObject();
				String word=object2.get("dst").getAsString();
				//word=URLEncoder.encode(word, "utf-8");
				System.out.println(word);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
