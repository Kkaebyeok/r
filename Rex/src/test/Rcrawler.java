package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class Rcrawler {
	public static void main(String[] args) throws MalformedURIException, IOException{
		String url = "http://www.wooripension.com/local/tobe_sub_main.asp?sido_kind=1&sido_code=01&regugun_code=ALL&view_type=1&tabVal=1&sort_type=KM&viewCnt=1000&pageno=1";
		Document doc = Jsoup.parse(new URL(url),5000);
		Elements els = doc.select(".list_thumb li");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < els.size(); i++) {
			String title = els.get(i).select(".ps_name").text();
			list.add(title);
		}
		
		String filePath = "D:\\workspaces\\jsp-workspace\\Rex\\WebContent"; 
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		FileOutputStream fos = new FileOutputStream(filePath + "title.txt",true);
		for (String string : list) {
			fos.write((string+"\r\n").getBytes());
			fos.flush();
		}
		fos.close();
	}
}
