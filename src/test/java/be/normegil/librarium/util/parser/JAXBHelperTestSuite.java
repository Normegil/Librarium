package be.normegil.librarium.util.parser;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.annotation.XSD;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.util.parser.adapter.jaxb.LocalDateJAXBAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.Month;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTJAXBHelperSafety.class,
		UTJAXBHelper.class
})
public class JAXBHelperTestSuite implements DataFactory<JAXBHelperTestSuite.JAXBTestClass> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<URL> URL_FACTORY = FactoryRepository.get(URL.class);
	private static final long ID = 0L;
	private static final String TITLE = "Title";
	private static final String IGNORED = "Ignored";
	private static Long index = 0L;

	@Override
	public JAXBTestClass getDefault() {
		return getDefault(true, false);
	}

	@Override
	public JAXBTestClass getNew() {
		return getNew(true, false);
	}

	@Override
	public JAXBTestClass getDefault(final boolean withLink, final boolean withIds) {
		return new JAXBTestClass(ID, TITLE, URL_FACTORY.getDefault(), LocalDate.of(2014, Month.SEPTEMBER, 2), IGNORED);
	}

	@Override
	public JAXBTestClass getNew(final boolean withLink, final boolean withIds) {
		JAXBTestClass jaxbTestClass = new JAXBTestClass(
				ID + index,
				TITLE + index,
				URL_FACTORY.getNew(),
				LocalDate.now(),
				IGNORED
		);
		index += 1;
		return jaxbTestClass;
	}

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	@XSD("JAXBTestClass.xsd")
	public static class JAXBTestClass {
		@XmlAttribute
		private Long id;

		private String title;

		private URL url;

		@XmlJavaTypeAdapter(LocalDateJAXBAdapter.class)
		private LocalDate date;

		@XmlTransient
		private String ignored;

		public JAXBTestClass() {
		}

		public JAXBTestClass(final long id, final String title, final URL url, final LocalDate date, final String ignored) {
			this.id = id;
			this.title = title;
			this.url = url;
			this.date = date;
			this.ignored = ignored;
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
			JAXBTestClass rhs = (JAXBTestClass) obj;
			return new EqualsBuilder()
					.append(this.id, rhs.id)
					.append(this.title, rhs.title)
					.append(this.url, rhs.url)
					.append(this.date, rhs.date)
					.isEquals();
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder()
					.append(id)
					.append(title)
					.append(url)
					.append(date)
					.toHashCode();
		}
	}
}