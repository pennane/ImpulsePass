package view;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
import model.EventPojo;
public class TestApiConn {
	public static void main(String[] args) throws IOException {
		URL url = new URL("https://api.kide.app/api/products?city=P%C3%A4%C3%A4kaupunkiseutu&productType=1&categoryId=&companyId=&pageSize=&searchText=");
		InputStreamReader reader = new InputStreamReader(url.openStream());
		Gson gson = new Gson();
		EventPojo eventPojo = new EventPojo();
		Type collectionType = new TypeToken<EventPojo>() {
		}.getType();
		eventPojo = gson.fromJson(reader, collectionType);
		System.out.println("Success?");
		//SHOULD(!!!) print out first object from the retrieved json data
		System.out.println(eventPojo.getModel()[0].toString());
		
	}
}
