package org.craftercms.marketplace.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

import org.apache.commons.io.FilenameUtils;
import org.craftercms.commons.zip.ZipUtils;
import org.craftercms.marketplace.api.Plugin;
import org.craftercms.marketplace.api.PluginReader;
import org.craftercms.marketplace.api.descriptor.PluginDescriptor;
import org.craftercms.marketplace.api.exceptions.InvalidPluginException;
import org.craftercms.marketplace.api.exceptions.PluginReadingException;
import org.yaml.snakeyaml.Yaml;

/**
 * Created by alfonso on 5/19/17.
 */
public class PluginReaderImpl implements PluginReader {

    public static final String DEFAULT_PLUGIN_DESCRIPTOR_FILENAME = "plugin.yaml";

    private String pluginDescriptorFilename;

    public PluginReaderImpl() {
        this.pluginDescriptorFilename = DEFAULT_PLUGIN_DESCRIPTOR_FILENAME;
    }

    public void setPluginDescriptorFilename(String pluginDescriptorFilename) {
        this.pluginDescriptorFilename = pluginDescriptorFilename;
    }

    @Override
    public Plugin readPlugin(File carFile) throws PluginReadingException {
        File expandedCarFolder;
        try {
            String expandedCarFolderName = FilenameUtils.getBaseName(carFile.getName()) + "-expanded";
            expandedCarFolder = Files.createTempDirectory(expandedCarFolderName).toFile();

            ZipUtils.unZipFiles(carFile, expandedCarFolder);
        } catch (IOException e) {
            throw new PluginReadingException("Failed to expand CAR folder", e);
        }

        File pluginDescriptorFile = new File(expandedCarFolder, pluginDescriptorFilename);
        if (pluginDescriptorFile.exists()) {
            Reader reader;
            try {
                reader = new BufferedReader(new FileReader(pluginDescriptorFile));
            } catch (FileNotFoundException e) {
                throw new PluginReadingException("Unable to read " + pluginDescriptorFilename, e);
            }

            Yaml yaml = new Yaml();
            PluginDescriptor pluginDescriptor;

            try {
                pluginDescriptor = yaml.loadAs(reader, PluginDescriptor.class);
            } catch (Exception e) {
                throw new InvalidPluginException("Invalid descriptor " + pluginDescriptorFilename + " in CAR file " + carFile.getName(), e);
            }

            Plugin plugin = new Plugin();
            plugin.setLoadDate(System.currentTimeMillis());
            plugin.setCarFile(carFile);
            plugin.setDescriptor(pluginDescriptor);

            return plugin;
        } else {
            throw new InvalidPluginException("CAR file " + carFile.getName() +  " is missing " + pluginDescriptorFilename);
        }
    }

}
