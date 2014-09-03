package be.normegil.librarium.tool;

public interface DataFactory<E> {

	public E getNew();

	public E getNext();

	public E getNew(boolean withLink);

	public E getNext(boolean withLink);
}
