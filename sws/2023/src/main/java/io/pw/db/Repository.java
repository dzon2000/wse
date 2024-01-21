package io.pw.db;

import java.util.List;
import java.util.Optional;

/**
 * Created by pwykowski
 */
public interface Repository<T> {

	List<T> findAll();
	List<T> findAllPaginate(int limit, int offset);
	Optional<T> findById(long id);
	void store(T t);
	void update(T t);
	T deleteById(long id);

}
