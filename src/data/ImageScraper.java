package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ImageScraper {
	
	public static void main(String[] args) {
		new ImageScraper().scrapeStuff(1993,"chevrolet","camaro");
	}
	
	public String scrapeStuff(int year, String make, String model){
		String imageLink = "https://az853139.vo.msecnd.net/static/images/not-found.png";
		
		try {
			
			Document doc = Jsoup.connect("https://images.search.yahoo.com/search/images;?p="+year+"+"+make+"+"+model+"&imgsz=medium").get();
		
			Elements resultImages = doc.select("a[href] img[src]");
			
			System.out.println(doc.baseUri());
			
			List<Integer> imageIndexes = new ArrayList<>();

			//get a random one
			for (int i = 0; i < resultImages.size(); i++) {
				System.out.println(resultImages.get(i).attr("alt"));
				
				if(resultImages.get(i).attr("alt").toLowerCase().contains(make.toLowerCase())){
					imageIndexes.add(i);
					System.out.println(resultImages.get(i));
				}
			}
			
		
			int randomImageIndex = 1+(int)(Math.random()*imageIndexes.size()-1);
			System.out.println(randomImageIndex);
			//have this get everything before the &
			String[] imageLinkSplit = (resultImages.get(randomImageIndex).attr("src")).trim().split("&");
			imageLink = imageLinkSplit[0];
			
			System.out.println(imageLink);
			
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imageLink;
		
	}
	
}
