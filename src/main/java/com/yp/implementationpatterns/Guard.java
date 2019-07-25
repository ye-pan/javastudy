package com.yp.implementationpatterns;

public class Guard {

    public void compute() {
        Server server = getServer();
        if(server != null) {
            Client client = server.getClient();
            if(client != null) {
                Request current = client.getRequest();
                if(client != null) {
                    processRequest(current);
                }
            }
        }
    }

    private void processRequest(Request current) {

    }

    /**
     * 用卫述句改造
     */
    public void computeV2() {
        Server server = getServer();
        if(server == null) {
            return;
        }

        Client client = server.getClient();
        if(client == null) {
            return;
        }

        Request current = client.getRequest();
        if(current == null) {
            return;
        }
        processRequest(current);
    }

    private Server getServer() {
        return null;
    }
}
