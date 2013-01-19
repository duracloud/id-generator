/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.ldap.impl;

import org.duracloud.id.generator.error.InvalidConfigException;
import org.duracloud.id.generator.error.NotInitializedException;
import org.duracloud.id.generator.ldap.Ldap;
import org.duracloud.id.generator.ldap.domain.LdapConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class LdapImpl implements Ldap {

    private final Logger log = LoggerFactory.getLogger(LdapImpl.class);

    private LdapTemplate ldapTemplate;

    @Override
    public void initialize(LdapConfig config) {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(config.getUrl());
        contextSource.setBase(config.getBaseDn());
        contextSource.setUserDn(config.getUserDn());
        contextSource.setPassword(config.getPassword());

        try {
            contextSource.afterPropertiesSet();

        } catch (Exception e) {
            log.error("Error creating LdapContextSource", e);
            throw new InvalidConfigException("Error creating LdapContextSource",
                                             e);
        }

        this.ldapTemplate = new LdapTemplate(contextSource);
    }

    @Override
    public int maxUserId() {
        String base = "ou=people";
        String filter = "objectClass=x-idp-person";
        List<Integer> ids = getIds(base, filter);
        return ids.isEmpty() ? 0 : Collections.max(ids);
    }

    @Override
    public int maxGroupId() {
        String base = "ou=groups";
        String filter = "objectClass=x-idp-group";
        List<Integer> ids = getIds(base, filter);
        return ids.isEmpty() ? 0 : Collections.max(ids);
    }

    @Override
    public int maxRightsId() {
        String base = "ou=rights";
        String filter = "objectClass=x-idp-rights";
        List<Integer> ids = getIds(base, filter);
        return ids.isEmpty() ? 0 : Collections.max(ids);
    }

    private List<Integer> getIds(String base, String filter) {
        AttributesMapper mapper = new UniqueIdentifierAttributesMapper();
        List<String> atts = getLdapTemplate().search(base, filter, mapper);

        List<Integer> ids = new ArrayList<>();
        if (null != atts && !atts.isEmpty()) {
            for (String att : atts) {
                ids.add(Integer.parseInt(att));
            }
        }
        return ids;
    }

    private class UniqueIdentifierAttributesMapper implements AttributesMapper {
        @Override
        public Object mapFromAttributes(Attributes attributes)
            throws NamingException {
            return attributes.get("uniqueIdentifier").get();
        }
    }

    private LdapTemplate getLdapTemplate() {
        if (null == ldapTemplate) {
            throw new NotInitializedException("Ldap not initialized!");
        }
        return ldapTemplate;
    }

    /**
     * For unit test
     *
     * @param ldapTemplate mock
     */
    protected void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }
}
