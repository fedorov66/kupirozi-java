package ru.kupirozi.mail;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import ru.kupirozi.config.MailConfig;

/**
 * Created by fedorov on 18.02.2018.
 */
public class MailHelper {

    private Mailer mailer;

    public MailHelper(MailConfig config) {
        mailer = MailerBuilder
        .withSMTPServer(config.getHost(), config.getPort(), config.getUsername(), config.getPassword())
        .withTransportStrategy(TransportStrategy.SMTP.SMTP_TLS)
        .withSessionTimeout(10 * 1000)
        .clearEmailAddressCriteria()
        .withDebugLogging(true)
        .buildMailer();
    }

}
