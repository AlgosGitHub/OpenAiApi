package com.openai.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public record ChatCompletionRecord(
        @JsonProperty("id") String id,
        @JsonProperty("object") String object,
        @JsonProperty("created") long created,
        @JsonProperty("model") String model,
        @JsonProperty("usage") Usage usage,
        @JsonProperty("choices") Choice[] choices
) {
}