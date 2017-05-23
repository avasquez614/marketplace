package org.craftercms.marketplace.api;

import java.io.File;

import org.craftercms.marketplace.api.descriptor.PluginDescriptor;

/**
 * Created by alfonso on 5/18/17.
 */
public class Plugin {

    private PluginDescriptor descriptor;
    private long loadDate;
    private File carFile;

    public PluginDescriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(PluginDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    public long getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(long loadDate) {
        this.loadDate = loadDate;
    }

    public File getCarFile() {
        return carFile;
    }

    public void setCarFile(File carFile) {
        this.carFile = carFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Plugin plugin = (Plugin)o;

        return descriptor.equals(plugin.descriptor);
    }

    @Override
    public int hashCode() {
        return descriptor.hashCode();
    }

}
