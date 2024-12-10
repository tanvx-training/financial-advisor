package dev.tanvx.api_gateway.filter;

import dev.tanvx.api_gateway.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class RequestTraceFilter implements GlobalFilter {

    private final FilterUtil filterUtil;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String traceId = filterUtil.getTraceId(requestHeaders);
        if(!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString();
            exchange = filterUtil.setTraceId(exchange, traceId);
        }
        log.debug("Request URI={}, Request method={}, TraceId={}",
                exchange.getRequest().getURI(), exchange.getRequest().getMethod(), traceId);
        return chain.filter(exchange);
    }
}
