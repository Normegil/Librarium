package be.normegil.librarium.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTXSDUtil_Safety.class,
		UTXSDUtil.class
})
public class XSDUtilTestSuite {

	public static final Logger LOG = LoggerFactory.getLogger(XSDUtilTestSuite.class);

	public void generateXSD(Class aClass) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
			SchemaOutputResolver sor = new SchemaOutputResolver() {
				@Override
				public Result createOutput(final String namespaceUri, final String suggestedFileName) throws IOException {
					File file = new File(suggestedFileName);
					LOG.info("Generated XSD : " + file.getAbsolutePath());
					StreamResult result = new StreamResult(file);
					result.setSystemId(file.toURI().toURL().toString());
					return result;
				}
			};
			jaxbContext.generateSchema(sor);
		} catch (JAXBException e) {
			throw new be.normegil.librarium.util.exception.JAXBException(e);
		} catch (IOException e) {
			throw new be.normegil.librarium.util.exception.IOException(e);
		}
	}
}
