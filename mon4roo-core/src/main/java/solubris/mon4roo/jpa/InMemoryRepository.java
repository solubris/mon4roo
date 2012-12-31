package solubris.mon4roo.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author walterst
 *
 * @param <T> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
public class InMemoryRepository<T extends Identifiable<ID>, ID extends Serializable> implements JpaRepository<T, ID> {

	// use TreeMap so results are sorted
	Map<ID, T> data=new TreeMap<ID, T>();

	@Override
	public void deleteAllInBatch() {
		data.clear();
	}

	@Override
	public void deleteInBatch(Iterable<T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findAll() {
        return new ArrayList<T>(data.values());
	}

	@Override
	public List<T> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T saveAndFlush(T item) {
    	data.put(item.getId(), item);
		return item;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		int firstResult = pageable.getOffset();
		int maxResults = firstResult + pageable.getPageSize();
    	int lastResult = (firstResult+maxResults > data.size() ? data.size() : firstResult+maxResults);
        return new PageImpl<T>(new ArrayList<T>(data.values()).subList(firstResult, lastResult), pageable, lastResult-firstResult);
        //return new ArrayList<T>(data.values()).subList(firstResult, lastResult);
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
	public void delete(Iterable<? extends T> arg0) {
		// TODO Auto-generated method stub
		
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
	public Iterable<T> findAll(Iterable<ID> arg0) {
		// TODO Auto-generated method stub
		return null;
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
