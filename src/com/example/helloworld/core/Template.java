package com.example.helloworld.core;
import static java.lang.String.format;

public class Template {
    private final String content;
    private final String defaultName;

    public Template(final String content, final String defaultName) {
        this.content = content;
        this.defaultName = defaultName;
    }

    public String render(String name) {
        return format(content, defaultName);
    }
}