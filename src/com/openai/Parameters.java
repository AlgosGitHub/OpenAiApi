package com.openai;

import java.util.HashMap;
import java.util.Map;

public class Parameters {

    Map<String, Object> parameters = new HashMap<>();

    public void setParameter(String name, Object value) {
        parameters.put(name, value);
    }

    {
        setParameter("model", "gpt-3.5-turbo");
    }

}
