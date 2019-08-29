package com.yp.zookeeper.curator.locks;

import com.yp.zookeeper.curator.ZKSimpleTest;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LockerTest extends ZKSimpleTest {

    @Test
    public void testLocker() throws Exception {
        String lockBasePath = "/locks";
        InterProcessLock lock = new InterProcessMutex(client, lockBasePath);
        try(Locker locker = new Locker(lock, Integer.MAX_VALUE, TimeUnit.MILLISECONDS)) {
            assertTrue(lock.isAcquiredInThisProcess());
        }
        assertFalse(lock.isAcquiredInThisProcess());
    }

}