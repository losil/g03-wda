package ch.hslu.swde.wda.server.rmi;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RMIServerTest {


    @Test
    private void testConfigFileLoader() {

        File configFile = new File(Objects.requireNonNull(RMIServer.class.getClassLoader().getResource("server.properties")).getFile());
        String sourceUrl = null;
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            int rmiPort = Integer.valueOf(props.getProperty("rmiPort", "1099"));
            sourceUrl = props.getProperty("sourceUrl");
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(sourceUrl);
    }

}