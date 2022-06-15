package model;

public interface IIdentifiable<ID> {
	ID getId();
	void setId(ID id);
	void setDeleted(boolean deleted);
	boolean isDeleted();
}
