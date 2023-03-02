package kide;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.EventDetailed;

public class KideAppApi {
	public static final String API_BASE = "https://api.kide.app/api/";
	public static final String DEFAULT_EVENT_PARAMS = "city=P%C3%A4%C3%A4kaupunkiseutu&productType=1";
	public static final String PRODUCTS_ENDPOINT = "products";

	Gson gson;

	public KideAppApi() {
		gson = new Gson();
	}

	/**
	 * Internal wrapper for requesting anything from the api
	 */
	private <T> Optional<T> request(Type returnType, String endpoint, String params) {
		URL url = null;
		KideAppApiResponse<T> response = null;

		try {
			url = new URL(API_BASE + endpoint + "?" + params);
			InputStreamReader streamReader = new InputStreamReader(url.openStream());
			response = gson.fromJson(streamReader, returnType);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(response.getModel());
	}

	public EventDetailed fetchEventDetails(String id) {
		URL url = null;
		EventDetailed result = new EventDetailed();
		try {
			url = new URL(API_BASE + PRODUCTS_ENDPOINT + "/" + id);
			InputStreamReader streamReader = new InputStreamReader(url.openStream());
			JsonObject apiResponse = gson.fromJson(streamReader, JsonObject.class);
			JsonObject product = apiResponse.getAsJsonObject("model").getAsJsonObject("product");
			String dateSalesFrom = product.get("dateSalesFrom").getAsString();
			String dateSalesUntil = product.get("dateSalesUntil").getAsString();
			String dateActualFrom = product.get("dateActualFrom").getAsString();
			String dateActualUntil = product.get("dateActualUntil").getAsString();
			Boolean salesStarted = product.get("salesStarted").getAsBoolean();
			Boolean salesEnded = product.get("salesEnded").getAsBoolean();
			int availability = product.get("availability").getAsInt();
			result.setDateSalesFrom(dateSalesFrom);
			result.setDateSalesUntil(dateSalesUntil);
			result.setDateActualFrom(dateActualFrom);
			result.setDateActualUntil(dateActualUntil);
			result.setSalesEnded(salesEnded);
			result.setSalesStarted(salesStarted);
			result.setAvailability(availability);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Optional<List<KideAppEvent>> fetchEvents() {
		Type type = new TypeToken<KideAppApiResponse<List<KideAppEvent>>>() {
		}.getType();
		return request(type, PRODUCTS_ENDPOINT, DEFAULT_EVENT_PARAMS);
	}

}
