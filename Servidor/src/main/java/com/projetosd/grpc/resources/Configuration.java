package com.projetosd.grpc.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(
                "./properties/connection.properties");
        properties.load(file);
        return properties;
 
    }
}
