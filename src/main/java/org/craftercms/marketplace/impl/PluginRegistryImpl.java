package org.craftercms.marketplace.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.craftercms.marketplace.api.Plugin;
import org.craftercms.marketplace.api.PluginReader;
import org.craftercms.marketplace.api.PluginRegistry;
import org.craftercms.marketplace.api.PluginScanner;
import org.craftercms.marketplace.api.exceptions.PluginAlreadyExistsException;
import org.craftercms.marketplace.api.exceptions.PluginNotFoundException;
import org.craftercms.marketplace.api.exceptions.PluginReadingException;
import org.craftercms.marketplace.api.exceptions.PluginRegistryException;

/**
 * Created by alfonso on 5/18/17.
 */
public class PluginRegistryImpl implements PluginScanner, PluginRegistry {

    public static final String DEFAULT_CAR_FILE_EXTENSION = "car";

    protected String carFileExtension;
    protected File carsFolder;
    protected PluginReader pluginReader;
    protected Set<Plugin> loadedPlugins;

    public PluginRegistryImpl() {
        carFileExtension = DEFAULT_CAR_FILE_EXTENSION;
        loadedPlugins = new HashSet<>();
    }

    public void setCarsFolder(File carsFolder) {
        this.carsFolder = carsFolder;
    }

    public void setPluginReader(PluginReader pluginReader) {
        this.pluginReader = pluginReader;
    }

    @Override
    public List<Plugin> scanForPlugins() throws PluginReadingException {
        Collection<File> carFiles = getAllCarFiles();
        List<Plugin> plugins = new ArrayList<>();

        for (File carFile : carFiles) {
            Plugin plugin = findLoadedPluginByCarFile(carFile);

            if (plugin == null || carFile.lastModified() >= plugin.getLoadDate()) {
                plugin = loadPlugin(carFile);
            }

            plugins.add(plugin);
        }

        return plugins;
    }

    @Override
    public Collection<Plugin> gelAllPlugins() {
        if (CollectionUtils.isNotEmpty(loadedPlugins)) {
            return Collections.unmodifiableSet(loadedPlugins);
        } else {
            return null;
        }
    }

    @Override
    public synchronized Plugin getPlugin(String id) throws PluginNotFoundException {
        Plugin plugin = findLoadedPluginById(id);
        if (plugin != null) {
            return null;
        } else {
            throw new PluginNotFoundException(id);
        }
    }

    @Override
    public synchronized Plugin addPlugin(File carFile, boolean replace) throws PluginAlreadyExistsException, PluginReadingException,
        PluginRegistryException {
        Plugin plugin = findLoadedPluginByCarFile(carFile);
        if (plugin != null && !replace) {
            throw new PluginAlreadyExistsException(plugin.getDescriptor().getId());
        } else {
            plugin = loadPlugin(carFile);

            String pluginId = plugin.getDescriptor().getId();
            File finalCarFile = new File(carsFolder, pluginId + "." + carFileExtension);

            try {
                FileUtils.moveFile(carFile, finalCarFile);
            } catch (IOException e) {
                throw new PluginRegistryException("Failed to move original CAR file from " + carFile + " to " + finalCarFile, e);
            }

            plugin.setCarFile(finalCarFile);

            return plugin;
        }
    }

    @Override
    public synchronized void removePlugin(String id) throws PluginNotFoundException {
        Plugin plugin = getPlugin(id);

        loadedPlugins.remove(plugin);

        FileUtils.deleteQuietly(plugin.getCarFile());
    }

    protected Plugin loadPlugin(File carFile) throws PluginReadingException {
        Plugin plugin = pluginReader.readPlugin(carFile);

        // Remove old version, if any
        loadedPlugins.remove(plugin);
        // Add new version
        loadedPlugins.add(plugin);

        return plugin;
    }

    protected Collection<File> getAllCarFiles() {
        return FileUtils.listFiles(carsFolder, new String[] {carFileExtension}, false);
    }

    protected Plugin findLoadedPluginById(String id) {
        if (CollectionUtils.isNotEmpty(loadedPlugins)) {
            return loadedPlugins.stream().filter(plugin -> plugin.getDescriptor().getId().equals(id)).findFirst().orElse(null);
        } else {
            return null;
        }
    }

    protected Plugin findLoadedPluginByCarFile(File carFile) {
        if (CollectionUtils.isNotEmpty(loadedPlugins)) {
            return loadedPlugins.stream().filter(plugin -> plugin.getCarFile().equals(carFile)).findFirst().orElse(null);
        } else {
            return null;
        }
    }

}
