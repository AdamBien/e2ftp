/*
 *
 */
package org.eftp.ftpserver.business.configuration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adam-bien.com
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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
