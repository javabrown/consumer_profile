package com.consumer_reports.codetest.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HateoasResponse<T> extends ResourceSupport {
    private T response;
    private Optional<ApiError> error;

    public HateoasResponse(Link link, T response, Optional<ApiError> error) {
        super.add(link);
        this.response = response;
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public String getError() {
       AtomicReference<String> message = new AtomicReference<>();
       error.ifPresent($->message.set($.getError()));
        return message.get();
    }
}
