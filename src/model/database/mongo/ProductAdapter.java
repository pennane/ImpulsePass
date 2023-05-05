package model.database.mongo;

/**
 * Adapteri KideApp tapahtuman tallentamiseen tietokantaan
 */
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