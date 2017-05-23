package org.craftercms.marketplace.api;

import java.util.List;

import org.craftercms.marketplace.api.exceptions.PluginReadingException;

/**
 * Created by alfonso on 5/19/17.
 */
public interface PluginScanner {

    List<Plugin> scanForPlugins() throws PluginReadingException;

}
