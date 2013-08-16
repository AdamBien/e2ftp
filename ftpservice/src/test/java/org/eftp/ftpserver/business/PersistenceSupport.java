/*
 *
 */
package org.eftp.ftpserver.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author adam-bien.com
 */
public class PersistenceSupport {

    protected EntityManager em;
    protected EntityTransaction tx;

    public void initEM() {
        this.em = Persistence.createEntityManagerFactory("it").createEntityManager();
        this.tx = this.em.getTransaction();
    }

}
