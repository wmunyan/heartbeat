# heartbeat
micronaut 4 groovy endpoint failure

## Error Output
```
> Task :Application.main()
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_ 
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
16:07:42.425 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 971ms. Server Running: http://localhost:8080
16:07:51.549 [default-nioEventLoopGroup-1-3] ERROR i.m.http.server.RouteExecutor - Unexpected error occurred: Cannot invoke method heartbeat() on null object
java.lang.NullPointerException: Cannot invoke method heartbeat() on null object
    at org.codehaus.groovy.runtime.NullObject.invokeMethod(NullObject.java:98)
    at org.codehaus.groovy.vmplugin.v8.IndyGuardsFiltersAndSignatures.invokeGroovyObjectInvoker(IndyGuardsFiltersAndSignatures.java:149)
    at org.codehaus.groovy.vmplugin.v8.IndyInterface.fromCache(IndyInterface.java:321)
    at com.example.HeartbeatController.heartbeat(HeartbeatController.groovy:19)
    at com.example.$HeartbeatController$Definition$Exec.dispatch(Unknown Source)
    at io.micronaut.context.AbstractExecutableMethodsDefinition$DispatchedExecutableMethod.invoke(AbstractExecutableMethodsDefinition.java:442)
    at io.micronaut.context.DefaultBeanContext$BeanExecutionHandle.invoke(DefaultBeanContext.java:3858)
    at io.micronaut.web.router.AbstractRouteMatch.execute(AbstractRouteMatch.java:223)
    at io.micronaut.http.context.ServerRequestContext.with(ServerRequestContext.java:74)
    at io.micronaut.http.server.RouteExecutor.executeRouteAndConvertBody(RouteExecutor.java:480)
    at io.micronaut.http.server.RouteExecutor.callRoute(RouteExecutor.java:470)
    at io.micronaut.http.server.RequestLifecycle.lambda$normalFlow$2(RequestLifecycle.java:146)
    at io.micronaut.core.execution.ImperativeExecutionFlowImpl.flatMap(ImperativeExecutionFlowImpl.java:72)
    at io.micronaut.http.server.RequestLifecycle.lambda$normalFlow$4(RequestLifecycle.java:146)
    at io.micronaut.http.server.RequestLifecycle.lambda$runWithFilters$14(RequestLifecycle.java:264)
    at io.micronaut.http.filter.FilterRunner.processRequestFilter(FilterRunner.java:306)
    at io.micronaut.http.filter.FilterRunner.filterRequest0(FilterRunner.java:183)
    at io.micronaut.http.filter.FilterRunner.lambda$filterRequest0$3(FilterRunner.java:183)
    at io.micronaut.core.execution.ImperativeExecutionFlowImpl.flatMap(ImperativeExecutionFlowImpl.java:72)
    at io.micronaut.http.filter.FilterRunner.processRequestFilter(FilterRunner.java:272)
    at io.micronaut.http.filter.FilterRunner.filterRequest0(FilterRunner.java:183)
    at io.micronaut.http.filter.FilterRunner.lambda$filterRequest0$3(FilterRunner.java:183)
    at io.micronaut.http.filter.FilterRunner.processRequestFilter(FilterRunner.java:242)
    at io.micronaut.http.filter.FilterRunner.filterRequest0(FilterRunner.java:183)
    at io.micronaut.http.filter.FilterRunner.lambda$filterRequest0$3(FilterRunner.java:183)
    at io.micronaut.core.execution.ImperativeExecutionFlowImpl.flatMap(ImperativeExecutionFlowImpl.java:72)
    at io.micronaut.http.filter.FilterRunner.processRequestFilter(FilterRunner.java:272)
    at io.micronaut.http.filter.FilterRunner.filterRequest0(FilterRunner.java:183)
    at io.micronaut.http.filter.FilterRunner.filterRequest(FilterRunner.java:167)
    at io.micronaut.http.filter.FilterRunner.run(FilterRunner.java:162)
    at io.micronaut.http.server.RequestLifecycle.runWithFilters(RequestLifecycle.java:281)


```
