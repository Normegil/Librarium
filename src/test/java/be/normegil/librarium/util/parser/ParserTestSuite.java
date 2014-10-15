package be.normegil.librarium.util.parser;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTParserSafety.class,
		UTParser.class
})
public class ParserTestSuite {

	public static class ParserExample {
		private UUID id;
		private String name;

		public ParserExample(final UUID id, final String name) {
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
			ParserExample rhs = (ParserExample) obj;
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