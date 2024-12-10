package dev.tanvx.api_gateway.filter;

import dev.tanvx.api_gateway.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ResponseTraceFilter {

    private final FilterUtil filterUtil;

    @Bean
    public GlobalFilter postGlobalFilter() {

        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                    String traceId = filterUtil.getTraceId(requestHeaders);
                    HttpHeaders responseHeaders = exchange.getResponse().getHeaders();
                    if (!responseHeaders.containsKey(FilterUtil.TRACE_ID)) {
                        responseHeaders.add(FilterUtil.TRACE_ID, traceId);
                    }
                    log.debug("Response status={}, Request method={}, TraceId={}",
                            exchange.getResponse().getStatusCode(), exchange.getRequest().getMethod(), traceId);
                }));
    }
}
