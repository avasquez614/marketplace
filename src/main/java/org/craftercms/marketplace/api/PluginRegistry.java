package org.craftercms.marketplace.api;

import java.io.File;
import java.util.Collection;

import org.craftercms.marketplace.api.exceptions.PluginAlreadyExistsException;
import org.craftercms.marketplace.api.exceptions.PluginNotFoundException;
import org.craftercms.marketplace.api.exceptions.PluginReadingException;
import org.craftercms.marketplace.api.exceptions.PluginRegistryException;

/**
 * Created by alfonso on 5/18/17.
 */
public interface PluginRegistry {

    Collection<Plugin> gelAllPlugins();

    Plugin getPlugin(String id) throws PluginNotFoundException;

    Plugin addPlugin(File carFile, boolean replace) throws PluginAlreadyExistsException, PluginReadingException, PluginRegistryException;

    void removePlugin(String id) throws PluginNotFoundException;

}
