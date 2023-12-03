package io.pw.db;

import java.util.List;
import java.util.Optional;

/**
 * Created by pwykowski
 */
public interface Repository<T> {

	List<T> findAll();
	Optional<T> findById(long id);
	void store(T t);
	void update(T t);

}
