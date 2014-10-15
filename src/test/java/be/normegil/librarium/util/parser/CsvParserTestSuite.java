package be.normegil.librarium.util.parser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTCsvParserSafety.class,
		UTCsvParser.class
})
public class CsvParserTestSuite {

	@CsvSchema(readOnly = false, columns = {
			"id",
			"name"
	})
	public static class CsvParserExample {
		private UUID id;
		private String name;

		public CsvParserExample(final UUID id, final String name) {
			this.id = id;
			this.name = name;
		}


		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			CsvParserExample rhs = (CsvParserExample) obj;
			return new EqualsBuilder()
					.append(this.id, rhs.id)
					.append(this.name, rhs.name)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
					.append(id)
					.append(name)
					.toHashCode();
		}
	}

	@CsvSchema(readOnly = true, columns = {
			"id",
			"name"
	})
	public static class CsvParserReadOnlyExample {
		private UUID id;
		private String name;

		public CsvParserReadOnlyExample(final UUID id, final String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != getClass()) {
				return false;
			}
			CsvParserReadOnlyExample rhs = (CsvParserReadOnlyExample) obj;
			return new EqualsBuilder()
					.append(this.id, rhs.id)
					.append(this.name, rhs.name)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
					.append(id)
					.append(name)
					.toHashCode();
		}
	}
}