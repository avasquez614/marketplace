package org.craftercms.marketplace.api.exceptions;

/**
 * Created by alfonso on 5/19/17.
 */
public class PluginReadingException extends MarketplaceException {

    public PluginReadingException(String message) {
        super(message);
    }

    public PluginReadingException(String message, Throwable cause) {
        super(message, cause);
    }

}
