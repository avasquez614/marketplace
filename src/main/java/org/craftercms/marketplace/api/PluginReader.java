package org.craftercms.marketplace.api;

import java.io.File;

import org.craftercms.marketplace.api.exceptions.PluginReadingException;

/**
 * Created by alfonso on 5/19/17.
 */
public interface PluginReader {

    Plugin readPlugin(File carFile) throws PluginReadingException;

}
