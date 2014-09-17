package be.normegil.librarium.libraries;

public class FieldWrapper {

	private java.lang.reflect.Field field;

	public FieldWrapper(java.lang.reflect.Field field) {
		this.field = field;
	}

	public String getName(){
		return field.getName();
	}

	public void set(Object entity, Object value) {
		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		try {
			field.set(entity, value);
		} catch (IllegalAccessException e) {
			throw new be.normegil.librarium.util.exception.IllegalAccessException(e);
		} finally {
			field.setAccessible(accessible);
		}
	}

	public java.lang.reflect.Field getOriginalField() {
		return field;
	}
}