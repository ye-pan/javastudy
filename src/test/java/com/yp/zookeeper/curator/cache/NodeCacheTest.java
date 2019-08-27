package com.yp.zookeeper.curator.cache;

import com.yp.zookeeper.curator.ZKSimpleTest;
import org.junit.Test;
import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.*;

public class NodeCacheTest extends ZKSimpleTest {

    @Test
    public void testNodeCache() throws Exception {
        NodeCache nodeCache = new NodeCache(client, path);
        try {
            CyclicBarrier barrier = new CyclicBarrier(2);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(() -> {
                System.out.println("Node data changed, new data: " + new String(nodeCache.getCurrentData().getData()));
                barrier.await();
            });
            String cacheValue = new String(nodeCache.getCurrentData().getData());
            assertEquals(init, cacheValue);

            String newVal = "update";
            client.setData().forPath(path, newVal.getBytes());
            barrier.await();
            cacheValue = new String(nodeCache.getCurrentData().getData());
            assertEquals(newVal, cacheValue);
        } finally {
            nodeCache.close();
        }
    }
}