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
			case 6:System.out.println("�����˳�!!!");return;
			default:System.out.println("������������������1-6");
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
			System.out.println("���������ĵ绰����:");
			Scanner scan=new Scanner(System.in);
			tel=scan.nextLine();
			Matcher m=p1.matcher(tel);
			boolean haspic=m.find();
			if(haspic==true && tel.length()==11) {
				break;
			}
			else {
				System.out.println("��ʽ����!(11λ����)");
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
			System.out.println("���������(����):");
			Scanner scan=new Scanner(System.in);
			city=scan.nextLine();
			Matcher m=p1.matcher(city);
			boolean haspic=m.find();
			if(haspic==true) {
				break;
			}
			else {
				System.out.println("��ʽ����!");
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
			System.out.println("������ip��ַ(����:112.112.11.11):");
			Scanner scan=new Scanner(System.in);
			ip=scan.nextLine();
			Matcher m=p1.matcher(ip);
			boolean haspic=m.find();
			if(haspic==true) {
				break;
			}
			else {
				System.out.println("��ʽ����!");
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
		System.out.println("����ϵ�����߸��ķ�������ַ����Ȼ������ʧ��!!!");
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
						System.out.println("����������:");
						Scanner scan=new Scanner(System.in);
						word=scan.nextLine();
						Matcher m=p1.matcher(word);
						boolean haspic=m.find();
						if(haspic==true) {
							break;
						}
						else {
							System.out.println("�����ʽ����!");
						}
					}
					String message=ser.translate(word, "zh", "en");
					ms.translateStr(message);
					//�ַ�������
					break;
				case 2:
					String word2="";
					while(true) {
						//String word="";
						System.out.println("������Ӣ��:");
						Scanner scan=new Scanner(System.in);
						word2=scan.nextLine();
						Matcher m=p2.matcher(word2);
						boolean haspic=m.find();
						if(haspic==true) {
							break;
						}
						else {
							System.out.println("�����ʽ����!");
						}
					}
					String message2=ser.translate(word2, "en", "zh");
					//�ַ�������
					ms.translateStr(message2);
					break;
				case 3:return;
				default :System.out.println("������1-3");
			}
			choose = menu2();
		}
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
	public static int menu2() {
		System.out.println("1.����ת��Ӣ��:");
		System.out.println("2.Ӣ��ת������:");
		System.out.println("3.����������");
		Scanner scan=new Scanner(System.in);
		System.out.println("���������ѡ��:");
		int choose=scan.nextInt();
		return choose;
	}
	

}
