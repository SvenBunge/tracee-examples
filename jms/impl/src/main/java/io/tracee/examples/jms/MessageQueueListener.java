package io.tracee.examples.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.interceptor.Interceptors;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.tracee.binding.jms.TraceeMessageListener;
import io.tracee.contextlogger.contextprovider.javaee.TraceeJmsErrorMessageListener;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "exampleQueue") })
@Interceptors({ TraceeMessageListener.class, TraceeJmsErrorMessageListener.class })
public class MessageQueueListener implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageQueueListener.class);

    @Override
    public void onMessage(Message message) {
        LOG.info("I just received the message \"{}\" on javaee/exampleQueue", message);
    }

}
