package dev.tanvx.api_gateway.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtil {

    public static final String TRACE_ID = "financial-advisor-trace-id";

    public String getTraceId(HttpHeaders requestHeaders) {

        return requestHeaders.getOrEmpty(TRACE_ID)
                .stream()
                .findFirst()
                .orElse(StringUtils.EMPTY);
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {

        return exchange.mutate()
                .request(exchange.getRequest().mutate().header(name, value).build())
                .build();
    }

    public ServerWebExchange setTraceId(ServerWebExchange exchange, String traceId) {

        return setRequestHeader(exchange, TRACE_ID, traceId);
    }
}
