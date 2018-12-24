package com.invillia.acme.helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FixtureHelper {

    public static String readFixture(String fixture) throws Exception {
        final Path path = Paths.get(FixtureHelper.class.getClassLoader().getResource("fixtures/" + fixture).toURI());
        return new String(Files.readAllBytes(path));
    }
}
