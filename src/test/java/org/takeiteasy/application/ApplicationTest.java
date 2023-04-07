package org.takeiteasy.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class ApplicationTest {
    @Test
    void getInstance() {
        ApplicationImpl application = ApplicationImpl.getInstance();
        ApplicationImpl application1 = ApplicationImpl.getInstance();
        assertNotNull(application);
        assertSame(application1, application);
    }
}