package model.database.mongo;

public class ProductAdapter<T> {
	T product;

	public ProductAdapter() {
	}

	public T getProduct() {
		return product;
	}

	public void setProduct(T product) {
		this.product = product;
	}
}