package ch.hslu.swde.wda.business.control.impl;

import ch.hslu.swde.wda.business.control.api.Control;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ControlImplTest {

    @Test
    void testEquals1() throws RemoteException {
        Control control = new ControlImpl();
        Control control1 = control;
        assertSame(control, control1);
    }

    @Test
    void testHashCode1() throws RemoteException {
        Control control = new ControlImpl();
        Control control1 = control;
        assertEquals(control.hashCode(), control1.hashCode());

    }

    @Test
    void testToString1() throws RemoteException {
        Control control = new ControlImpl();
        assertNotNull(control.toString());
    }
}