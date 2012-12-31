package solubris.mon4roo.jpa;

/**
 * Entity is identifiable by having a getId method
 * This is required by the InMemoryRepository
 * 
 * Could use jpa IdentifiableType instead
 * 
 * @author walterst
 *
 * @param <ID>
 */
public interface Identifiable<ID> {
	public ID getId();
}
