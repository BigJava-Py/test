package httpClientTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.Service;
import service.ManageString;

import java.util.Scanner;

public class Driver {
	public static void main(String args[]) throws Exception{
		//System.out.println(md5("20200610000491551"));
		//System.out.println(MD5());
		int choose=menu();
		while(true) {
			switch(choose) {
			case 1:telinquire();break;
			case 2:weatherinquire();break;
			case 3:ipinquire();break;
			case 4:newsinquire();break;
			case 5:translate();break;
			case 6:System.out.println("程序退出!!!");return;
			default:System.out.println("输入有误，请重新输入1-6");
			}
			Scanner sc=new Scanner(System.in);
			choose=menu();
		}
	}
	
	
	public static void telinquire() throws Exception  {
		Service ser=new Service();
		Pattern p1=Pattern.compile("1[358][\\d]{9}");
		String tel="";
		while(true) {
			System.out.println("请输入您的电话号码:");
			Scanner scan=new Scanner(System.in);
			tel=scan.nextLine();
			Matcher m=p1.matcher(tel);
			boolean haspic=m.find();
			if(haspic==true && tel.length()==11) {
				break;
			}
			else {
				System.out.println("格式错误!(11位数字)");
			}
		}
		String message=ser.getinformationpost(tel);
		ManageString ms=new ManageString();
		ms.telStr(message);
		
	}
	public static void weatherinquire() throws Exception  {
		Service ser=new Service();
		Pattern p1=Pattern.compile("[\\u4e00-\\u9fa5]{1,}");
		String city="";
		while(true) {
			System.out.println("请输入城市(中文):");
			Scanner scan=new Scanner(System.in);
			city=scan.nextLine();
			Matcher m=p1.matcher(city);
			boolean haspic=m.find();
			if(haspic==true) {
				break;
			}
			else {
				System.out.println("格式错误!");
			}
		}
		String message=ser.gettianqi(city);
		ManageString ms=new ManageString();
		ms.weatherStr(message);
	}
	
	public static void ipinquire() throws Exception  {
		Service ser=new Service();
		Pattern p1=Pattern.compile("([1-9]{1,3}\\.){3}[1-9]");
		String ip="";
		while(true) {
			System.out.println("请输入ip地址(例如:112.112.11.11):");
			Scanner scan=new Scanner(System.in);
			ip=scan.nextLine();
			Matcher m=p1.matcher(ip);
			boolean haspic=m.find();
			if(haspic==true) {
				break;
			}
			else {
				System.out.println("格式错误!");
			}
		}
		String message=ser.getip(ip);
		ManageString ms=new ManageString();
		ms.ipStr(message);
	}
	
	public static void newsinquire() throws Exception  {
		Service ser=new Service();
		String message=ser.getPress();
		ManageString ms=new ManageString();
		ms.newsStr(message);
	}
	
	public static void translate() throws Exception  {
		System.out.println("请联系开发者更改服务器地址，不然会运行失败!!!");
		Service ser=new Service();
		ManageString ms=new ManageString();
		Pattern p1=Pattern.compile("[\\u4e00-\\u9fa5]{1,}");
		Pattern p2=Pattern.compile("([a-zA-Z]+[\\s]*){1,}");
		int choose=menu2();
		while(true) {
			switch(choose) {
				case 1:
					String word="";
					while(true) {
						//String word="";
						System.out.println("请输入中文:");
						Scanner scan=new Scanner(System.in);
						word=scan.nextLine();
						Matcher m=p1.matcher(word);
						boolean haspic=m.find();
						if(haspic==true) {
							break;
						}
						else {
							System.out.println("输入格式错误!");
						}
					}
					String message=ser.translate(word, "zh", "en");
					ms.translateStr(message);
					//字符串处理
					break;
				case 2:
					String word2="";
					while(true) {
						//String word="";
						System.out.println("请输入英文:");
						Scanner scan=new Scanner(System.in);
						word2=scan.nextLine();
						Matcher m=p2.matcher(word2);
						boolean haspic=m.find();
						if(haspic==true) {
							break;
						}
						else {
							System.out.println("输入格式错误!");
						}
					}
					String message2=ser.translate(word2, "en", "zh");
					//字符串处理
					ms.translateStr(message2);
					break;
				case 3:return;
				default :System.out.println("请输入1-3");
			}
			choose = menu2();
		}
	}
	public static int menu() {
		int choose;
		System.out.println("生活百宝箱");
		System.out.println("1.手机号查询归属地查询");
		System.out.println("2.天气预报查询");
		System.out.println("3.ip地址查询");
		System.out.println("4.新闻头条查询");
		System.out.println("5.中英文双向翻译");
		System.out.println("6.退出");
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入您的选择1-6");
		choose=scan.nextInt();
		return choose;
	}
	public static int menu2() {
		System.out.println("1.中文转换英文:");
		System.out.println("2.英文转换中文:");
		System.out.println("3.返回主界面");
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入你的选择:");
		int choose=scan.nextInt();
		return choose;
	}
	

}
