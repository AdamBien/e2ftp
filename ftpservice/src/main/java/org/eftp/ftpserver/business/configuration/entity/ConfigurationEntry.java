/*
 *
 */
package org.eftp.ftpserver.business.configuration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author adam-bien.com
 */
@Entity
public class ConfigurationEntry {

    @Id
    private String name;
    @Column(name = "c_value")
    private String value;

    public ConfigurationEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ConfigurationEntry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
