package solubris.mon4roo.jpa;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Implement methods in @JpaSpecificationExecutor with @UnsupportedOperationException
 * This is required for convenience for spring-roo repositories which implement this.
 * For InMemory repository, dont really need these methods, so return @UnsupportedOperationException instead
 * 
 * @author walterst
 *
 * @param <T> the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 */
public class InMemoryRepositoryWithEmptyJpaSpecificationExecutor<T extends Identifiable<ID>, ID extends Serializable> extends InMemoryRepository<T, ID> implements JpaSpecificationExecutor<T> {

	@Override
	public T findOne(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long count(Specification<T> spec) {
		throw new UnsupportedOperationException();
	}

}
