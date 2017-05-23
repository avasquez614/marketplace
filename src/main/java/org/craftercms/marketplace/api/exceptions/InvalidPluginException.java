package org.craftercms.marketplace.api.exceptions;

/**
 * Created by alfonso on 5/19/17.
 */
public class InvalidPluginException extends PluginReadingException {

    public InvalidPluginException(String message) {
        super(message);
    }

    public InvalidPluginException(String message, Throwable cause) {
        super(message, cause);
    }
}
