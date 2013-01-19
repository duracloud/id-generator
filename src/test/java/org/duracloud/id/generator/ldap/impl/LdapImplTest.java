/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.ldap.impl;

import junit.framework.Assert;
import org.duracloud.id.generator.error.NotInitializedException;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class LdapImplTest {

    private LdapImpl ldap;
    private LdapTemplate template;

    @Before
    public void setUp() throws Exception {
        template = EasyMock.createMock("LdapTemplate", LdapTemplate.class);
        ldap = new LdapImpl();
    }

    @After
    public void tearDown() throws Exception {
        EasyMock.verify(template);
    }

    private void replayMocks() {
        EasyMock.replay(template);
    }

    @Test
    public void testNotInitialize() throws Exception {
        replayMocks();

        boolean thrown = false;
        try {
            ldap.maxUserId();
            Assert.fail("exception expected");
        } catch (NotInitializedException e) {
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void testMaxUserId() throws Exception {
        String base = "ou=people";
        String filter = "objectClass=x-idp-person";
        int max = createMockTemplate(base, filter);

        ldap.setLdapTemplate(template);
        replayMocks();

        int id = ldap.maxUserId();
        Assert.assertEquals(max, id);

    }

    @Test
    public void testMaxGroupId() throws Exception {
        String base = "ou=groups";
        String filter = "objectClass=x-idp-group";
        int max = createMockTemplate(base, filter);

        ldap.setLdapTemplate(template);
        replayMocks();

        int id = ldap.maxGroupId();
        Assert.assertEquals(max, id);

    }

    @Test
    public void testMaxRightsId() throws Exception {
        String base = "ou=rights";
        String filter = "objectClass=x-idp-rights";
        int max = createMockTemplate(base, filter);

        ldap.setLdapTemplate(template);
        replayMocks();

        int id = ldap.maxRightsId();
        Assert.assertEquals(max, id);

    }

    private int createMockTemplate(String base, String filter) {
        int max = 5;
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= max; ++i) {
            ids.add(Integer.toString(i));
        }

        EasyMock.expect(template.search(EasyMock.eq(base),
                                        EasyMock.eq(filter),
                                        EasyMock.anyObject(AttributesMapper.class)))
                .andReturn(ids);
        return max;
    }
}
