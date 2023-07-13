package com.openai.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(
        @JsonProperty("message") Message message,
        @JsonProperty("finish_reason") String finishReason,
        @JsonProperty("index") int index
) {
}
