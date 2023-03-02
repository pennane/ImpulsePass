package kide;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import database.ProductAdapter;
import model.CustomGson;
import model.KideAppEventDetails;

public class KideAppApi {
	public static final String API_BASE = "https://api.kide.app/api/";
	public static final String DEFAULT_EVENT_PARAMS = "city=P%C3%A4%C3%A4kaupunkiseutu&productType=1";
	public static final String PRODUCTS_ENDPOINT = "products";

	Gson gson;

	public KideAppApi() {
		gson = CustomGson.INSTANCE.getGson();
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

	public Optional<KideAppEventDetails> fetchEventDetails(String id) {
		Type type = new TypeToken<KideAppApiResponse<ProductAdapter<KideAppEventDetails>>>() {
		}.getType();
		Optional<ProductAdapter<KideAppEventDetails>> res = request(type, PRODUCTS_ENDPOINT + "/" + id, "");

		if (res.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(res.get().getProduct());
	}

	public Optional<List<KideAppEvent>> fetchEvents() {
		Type type = new TypeToken<KideAppApiResponse<List<KideAppEvent>>>() {
		}.getType();
		return request(type, PRODUCTS_ENDPOINT, DEFAULT_EVENT_PARAMS);
	}
}
