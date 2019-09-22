package com.yp.rpc.server.spring;

import com.yp.rpc.foo.SampleConfiguration;
import com.yp.rpc.foo.service.HelloService;
import com.yp.rpc.server.RpcRequest;
import com.yp.rpc.server.RpcResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SampleConfiguration.class})
public class ServerContainorTest {

    @Autowired
    public ServerContainor serverContainor;

    @Test
    public void should_invoke_successful_and_return_value() {
        RpcRequest request = helloServiceRequest("greeting", new String[]{"yepan"}, new Class<?>[]{String.class});
        RpcResponse response = serverContainor.invoke(request);
        assertThat(response.getId(), is(request.getId()));
        assertThat(response.getResult(), is("hello yepan"));
    }

    @Test(expected = RuntimeException.class)
    public void should_reject_when_method_invisible() {
        RpcRequest request = helloServiceRequest("foo", null, null);
        serverContainor.invoke(request);
    }

    @Test(expected = RuntimeException.class)
    public void should_reject_when_method_not_exist() {
        RpcRequest request = helloServiceRequest("bar", null, null);
        serverContainor.invoke(request);
    }

    private static RpcRequest helloServiceRequest(String methodName, Object[] parameters, Class<?>[] parameterTypes) {
        return new RpcRequest(UUID.randomUUID().toString(), HelloService.class.getName(), methodName, parameters, parameterTypes);
    }
}