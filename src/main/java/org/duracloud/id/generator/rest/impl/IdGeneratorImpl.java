/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.rest.impl;

import org.duracloud.id.generator.ldap.Ldap;
import org.duracloud.id.generator.ldap.domain.AppConfig;
import org.duracloud.id.generator.ldap.domain.LdapConfig;
import org.duracloud.id.generator.rest.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Andrew Woods
 *         Date: 1/16/13
 */
public class IdGeneratorImpl implements IdGenerator {

    private final Logger log = LoggerFactory.getLogger(IdGeneratorImpl.class);

    private Ldap ldap;
    private int userId = 0;
    private int groupId = 0;
    private int rightsId = 0;

    public IdGeneratorImpl(Ldap ldap) {
        this.ldap = ldap;
    }

    @Override
    public void initialize(LdapConfig ldapConfig) {
        ldap.initialize(ldapConfig);
        userId = 0;
        groupId = 0;
        rightsId = 0;
    }

    /**
     * FIXME: remove!!
     *
     * @return
     */
    public AppConfig example() {
        AppConfig config = new AppConfig();

        LdapConfig ldapConfig = new LdapConfig();
        ldapConfig.setBaseDn("base-dn");
        ldapConfig.setUrl("url");
        ldapConfig.setUserDn("user-dn");
        ldapConfig.setPassword("password");
        config.setLdapConfig(ldapConfig);
        return config;
    }

    public int getNewUserId() {
        if (0 == userId) {
            userId = ldap.maxUserId();
        }
        return ++userId;
    }

    public int getNewGroupId() {
        if (0 == groupId) {
            groupId = ldap.maxGroupId();
        }
        return ++groupId;
    }

    public int getNewRightsId() {
        if (0 == rightsId) {
            rightsId = ldap.maxRightsId();
        }
        return ++rightsId;
    }
}
