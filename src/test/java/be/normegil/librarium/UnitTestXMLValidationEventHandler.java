package be.normegil.librarium;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class UnitTestXMLValidationEventHandler implements ValidationEventHandler {

    @Override
    public boolean handleEvent(final ValidationEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("EVENT").append(Constants.LINE_ENDING)
                .append("SEVERITY:").append(event.getSeverity()).append(Constants.LINE_ENDING)
                .append("MESSAGE:").append(event.getMessage()).append(Constants.LINE_ENDING)
                .append("LINKED EXCEPTION:").append(event.getLinkedException()).append(Constants.LINE_ENDING)
                .append("LOCATOR").append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("LINE NUMBER:").append(event.getLocator().getLineNumber()).append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("COLUMN NUMBER:").append(event.getLocator().getColumnNumber()).append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("OFFSET:").append(event.getLocator().getOffset()).append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("OBJECT:").append(event.getLocator().getObject()).append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("NODE:").append(event.getLocator().getNode()).append(Constants.LINE_ENDING)
                .append(Constants.TABULATION).append("URL:").append(event.getLocator().getURL()).append(Constants.LINE_ENDING);
        throw new AssertionError(stringBuilder.toString());
    }
}
