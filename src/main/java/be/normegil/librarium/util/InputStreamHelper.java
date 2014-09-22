package be.normegil.librarium.util;

import be.normegil.librarium.Constants;
import be.normegil.librarium.util.exception.IORuntimeException;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamHelper {

	public String toString(@NotNull InputStream stream) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder builder = new StringBuilder();

			String inputStr = reader.readLine();
			while (inputStr != null) {
				builder.append(inputStr);
				inputStr = reader.readLine();
				if (inputStr != null) {
					builder.append(Constants.LINE_ENDING);
				}
			}

			return builder.toString();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

}
