package be.normegil.librarium.tool;

import java.util.UUID;

public class UUIDGenerator {

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(UUID.randomUUID());
		}
	}

}
