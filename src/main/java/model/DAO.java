package model;

public interface DAO<T> {

	/**
	 * 
	 * @param id
	 */
	T findById(Long id);

	/**
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 
	 * @param id
	 */
	void delete(Long id);

}