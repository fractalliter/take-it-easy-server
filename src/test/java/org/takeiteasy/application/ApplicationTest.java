package org.takeiteasy.application;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    @Test
    void getInstance() {
        ApplicationImpl application = ApplicationImpl.getInstance();
        ApplicationImpl application1 = ApplicationImpl.getInstance();
        assertNotNull(application);
        assertSame(application1, application);
    }

    @Test
    void loadProperties() throws IOException {
        ApplicationImpl app = ApplicationImpl.getInstance();
        Properties props = app.loadProperties();
        assertNotNull(props);
        assertEquals(props.getProperty("server-backlog"),"4000");
    }
}