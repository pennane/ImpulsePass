package model.kide;

import java.util.List;

public class TestApiConn {
	public static void main(String[] args) {
		KideAppApi api = new KideAppApi();
		List<KideAppEvent> products = api.fetchEvents().get();

		System.out.println("Success?");

		for (KideAppEvent e : products) {
			System.out.println(e.toString());
		}
	}
}
