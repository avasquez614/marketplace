package org.craftercms.marketplace.api.exceptions;

/**
 * Created by alfonso on 5/22/17.
 */
public class PluginRegistryException extends MarketplaceException {

    public PluginRegistryException(String message) {
        super(message);
    }

    public PluginRegistryException(String message, Throwable cause) {
        super(message, cause);
    }

}
