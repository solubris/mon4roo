package solubris.mon4roo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

/**
 * InMemory repo, not threadsafe
 * 
 * @author walterst
 * 
 * @param <T>
 *            the domain type the repository manages
 * @param <ID>
 *            the type of the id of the entity the repository manages
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID extends Serializable> implements JpaRepository<T, ID> {

	// use TreeMap so results are sorted
	Map<ID, T> data = new TreeMap<ID, T>();

	@Override
	public void deleteAllInBatch() {
		data.clear();
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		delete(entities);
	}

	@Override
	public List<T> findAll() {
		return new ArrayList<T>(data.values());
	}

	@Override
	public List<T> findAll(Sort arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flush() {
		// nothing to do on flush
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> result = new ArrayList<S>();

		if (entities == null) {
			return result;
		}

		for (S entity : entities) {
			result.add(save(entity));
		}

		return result;
	}

	@Override
	public T saveAndFlush(T entity) {

		T result = save(entity);
		flush();

		return result;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		int firstResult = pageable.getOffset();
		int maxResults = firstResult + pageable.getPageSize();
		int lastResult = (firstResult + maxResults > data.size() ? data.size() : firstResult + maxResults);
		return new PageImpl<T>(new ArrayList<T>(data.values()).subList(firstResult, lastResult), pageable, lastResult
				- firstResult);
	}

	@Override
	public long count() {
		return data.size();
	}

	@Override
	public void delete(ID id) {
		data.remove(id);
	}

	@Override
	public void delete(T item) {
		data.remove(item.getId());
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		Assert.notNull(entities, "The given Iterable of entities must not be null!");

		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	public void deleteAll() {
		data.clear();
	}

	@Override
	public boolean exists(ID id) {
		return data.containsKey(id);
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> entities) {
		List<T> result = new ArrayList<T>();

		if (entities == null) {
			return result;
		}

		for (ID entityId : entities) {
			result.add(findOne(entityId));
		}

		return result;
	}

	@Override
	public T findOne(ID id) {
		return data.get(id);
	}

	@Override
	public <S extends T> S save(S item) {
		data.put(item.getId(), item);
		return item;
	}
}
