package Main;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;  
import java.util.Map;
import org.json.JSONObject;
import java.io.*;
public class Main {
	static String  b=null,number="",name1="",province1="",city1="",country1="",town1="",village1="",jie="",hao="";
	private static Map<String,Object> map = new HashMap<String,Object>();
    
	public static String searchnumber(String a) {
		String REGEX="(\\d{11})";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   number=m.group(0);
	           //System.out.println("number: " +number );
	          
	        } 
	       a = m.replaceAll("");
		return a;
	}
	public static String searchname(String a) {
		String REGEX="([^,]+,)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   name1=m.group(0);
	    	   name1=name1.substring(0,name1.length()-1);
	           //System.out.println("name: " +name1 );
	           
	        }
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchprovince(String a) {
		String REGEX="([^省]+自治区|.*?省|.*?行政区|.*?市)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   province1=m.group(0);
	           //System.out.println("province: " +province1 );
	           
	        }
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchcity(String a) {
		String REGEX="([^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   city1=m.group(0);
	           //System.out.println("city: " +city1 );
	           
	        }
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchcountry(String a) {
		String REGEX="([^县]+?县|.+?区|.+?市|.+?|.+?旗|.+?海域|.+?岛)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   country1=m.group(0);
	           //System.out.println("country: " +country1 );
	           
	        } 
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchtown(String a) {
		String REGEX="([^镇]+?镇|.+?街道|.+?乡)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   town1=m.group(0);
	    	  
	           //System.out.println("town: " +town1 );
	           
	        } 
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchroad(String a) {
		String REGEX="([^路]+?路|.+?街|.+?巷|.+?村)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   jie=m.group(0);
	    	  
	           //System.out.println("jie: " +jie );
	           
	        } 
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchdoornum(String a) {
		String REGEX="([^号]+号)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   hao=m.group(0);
	    	  
	           //System.out.println("hao: " +hao );
	           
	        } 
	       a = m.replaceFirst("");
		return a;
	}
	public static String searchvillage(String a) {
		String REGEX="(.*)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   village1=m.group(0);
	           //System.out.println("village: " +village1 );
	           
	        } 
	       a = m.replaceFirst("");
		return a;
	}
	public static int panduan(String a) {
		String REGEX="(北京|.+?天津|.+?上海|.+?重庆)";
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   province1=m.group(0);
	    	   return 1;
	        } 
	       
		return 0;
	}
	public static int shuru(String a) {
		String REGEX="([^!]+!)",c=null;
		Pattern p = Pattern.compile(REGEX);
	       Matcher m = p.matcher(a);
	       if (m.find( )) {
	    	   c=m.group(0);
	    	   b = m.replaceFirst("");
	    	   if(c.equals("1!"))
	    	   {
	    		  
	    		   return 1;
	    	  
	    	   }  
	        } 
	       
		return 0;
	}
	public static void main(String[] args) throws IOException{
		
		
		int c=0;
		/*Scanner scan = new Scanner(System.in);
        if (scan.hasNextLine()) 
        {
             b = scan.nextLine();
        }
        scan.close();*/
		File file1 = new File(args[0]);
		InputStream is = new FileInputStream(file1);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        File file = new File(args[1]);
        
    	if(!file.exists()){
    		file.createNewFile();

    	}
    	FileWriter fw = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fw);
		out.write("[");
		int f=1;
            while ((b= reader.readLine())!= null) {
            	b=searchnumber(b);
        		b=b.substring(0,b.length()-1);
        		c=shuru(b);
        		if(f==1) 
        		f=0;
        		else
        		{out.write(",");
        			out.write("\n");
        		
        		}
		if(c==1)
		{
			b=searchname(b);
			if(panduan(b)==0)
			b=searchprovince(b);
			b=searchcity(b);
			b=searchcountry(b);
			b=searchtown(b);
			b=searchvillage(b);
			map.put("姓名",name1);
			map.put("手机", number);
			map.put("地址", new String[]{province1,city1,country1,town1,village1});
			JSONObject j = new JSONObject(map);
			String s = j.toString();
			out.write(s);
		}
		
		else
	{
		b=searchname(b);
		if(panduan(b)==0)
		b=searchprovince(b);
		b=searchcity(b);
		b=searchcountry(b);
		b=searchtown(b);
		b=searchroad(b);
		b=searchdoornum(b);
		b=searchvillage(b);
		map.put("姓名",name1);
		map.put("手机", number);
		map.put("地址", new String[]{province1,city1,country1,town1,jie,hao,village1});
		JSONObject j = new JSONObject(map);
		String s = j.toString();
		out.write(s);
	}
		
}
	out.write("]"); 
    reader.close();
    is.close();
    out.close();           
}
}

