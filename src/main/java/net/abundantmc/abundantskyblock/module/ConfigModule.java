package net.abundantmc.abundantskyblock.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("abundant.properties"));
            Names.bindProperties(binder(), properties);
        } catch (IOException ex) {
            //...
        }
    }
}
