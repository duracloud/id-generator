/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.ldap;

import org.duracloud.id.generator.ldap.domain.LdapConfig;

/**
 * @author Andrew Woods
 *         Date: 1/16/13
 */
public interface Ldap {

    public void initialize(LdapConfig config);

    public int maxUserId();

    public int maxGroupId();

    public int maxRightsId();

}
