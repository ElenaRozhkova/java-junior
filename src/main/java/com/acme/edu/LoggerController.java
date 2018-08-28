package com.acme.edu;

import com.acme.edu.message.Message;

public class LoggerController {
    private Message currentMessage = null;
    private Saver saver = new Saver();

    public void log(Message message) {
        if (currentMessage == null) {
            this.saver.save(message.decorate());
            this.currentMessage = message;
            return;
        }
        if (currentMessage.isInstanceOf(message)) {
            this.currentMessage = currentMessage.accumulate(message);
        } else {
            saver.save(currentMessage.decorate());
            this.currentMessage = message;
        }
        saver.save(message.decorate());
    }

    public void flush() {
        this.saver.save(currentMessage.decorate());
        currentMessage = null;
    }

    public void setSaver(Saver saver) {
        this.saver = saver;
    }
}
