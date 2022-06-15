package repositories;

import java.util.List;

import model.IIdentifiable;

public interface IRepository<T extends IIdentifiable<Id>, Id> {
	List<T> getAll();
	T getById(Id id);
	T create(T entity);
	T update(T entity);
	boolean delete(T entity);
	void saveAll(List<T> collection);
	List<T> getAllActive();
}
