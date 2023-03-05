package model.kide;

import java.util.List;

public class KideAppApiResponse<T> {
	private T model;
	private List<String> links;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

}
