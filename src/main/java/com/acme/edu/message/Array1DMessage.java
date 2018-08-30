package com.acme.edu.message;

import com.acme.edu.DecorateException;

public class Array1DMessage implements Message {
    private final String PREFIX = "primitives array";
    private final String SEPARATOR = ": ";

    private int[] accumulatedMessage;
    private String decoratedMessage;

    public Array1DMessage(int[] rawMessage) {
        this.accumulatedMessage = rawMessage;
        this.decoratedMessage = "";
    }

    @Override
    public Message accumulate(Message message) {
        return this;
    }

    @Override
    public Message decorate() throws DecorateException {
        int[] array = accumulatedMessage;
        StringBuilder arrayAsString = new StringBuilder();
        try {
            arrayAsString.append("{");
            for (int currentIndex = 0; currentIndex < array.length; ++currentIndex) {
                if (currentIndex < array.length - 1) {
                    arrayAsString.append(array[currentIndex]).append(", ");
                } else {
                    arrayAsString.append(array[currentIndex]);
                }
            }
            arrayAsString.append("}");
            decoratedMessage = PREFIX + SEPARATOR + arrayAsString.toString();
        } catch (Exception e) {
            throw new DecorateException(e);
        }
        return this;
    }

    @Override
    public boolean isInstanceOf(Message message) {
        return message instanceof Array1DMessage;
    }

    @Override
    public String getDecoratedString() {
        return decoratedMessage;
    }
}
