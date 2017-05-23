package org.craftercms.marketplace.api.descriptor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by alfonso on 5/18/17.
 */
public class PluginDescriptor {

    private String id;
    private Type type;
    private String name;
    private String version;
    private String description;
    private Website website;
    private Media media;
    private Organization organization;
    private List<Developer> developers;
    private License license;
    private Price price;
    private CrafterVersionsSupported crafterVersionsSupported;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("crafter_versions_supported")
    public CrafterVersionsSupported getCrafterVersionsSupported() {
        return crafterVersionsSupported;
    }

    public void setCrafterVersionsSupported(CrafterVersionsSupported crafterVersionsSupported) {
        this.crafterVersionsSupported = crafterVersionsSupported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PluginDescriptor that = (PluginDescriptor)o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public enum Type {
        PLUGIN,
        BLUEPRINT
    }

}
