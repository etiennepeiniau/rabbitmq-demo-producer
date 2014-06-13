package com.ekino.technoshare.rabbitmq.model;

public class Message {

    private MessageType type;
    private String value;

    public MessageType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public static class Builder {

        private Message message = new Message();

        public Builder setType(MessageType type) {
            message.type = type;
            return this;
        }

        public Builder setValue(String value) {
            message.value = value;
            return this;
        }

        public Message build() {
            return message;
        }

    }

}
