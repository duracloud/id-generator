/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.rest.impl;

import junit.framework.Assert;
import org.duracloud.id.generator.ldap.Ldap;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class IdGeneratorImplTest {

    private IdGeneratorImpl idGenerator;
    private Ldap ldap;

    @Before
    public void setUp() throws Exception {
        ldap = EasyMock.createMock("Ldap", Ldap.class);
        idGenerator = new IdGeneratorImpl(ldap);
    }

    @After
    public void tearDown() throws Exception {
        EasyMock.verify(ldap);
    }

    private void replayMocks() {
        EasyMock.replay(ldap);
    }

    @Test
    public void testGetNewUserId() throws Exception {
        int max = 5;
        EasyMock.expect(ldap.maxUserId()).andReturn(max);
        replayMocks();

        int id;
        for (int i = 1; i < 100; ++i) {
            id = idGenerator.getNewUserId();
            Assert.assertEquals(max + i, id);
        }
    }

    @Test
    public void testGetNewGroupId() throws Exception {
        int max = 5;
        EasyMock.expect(ldap.maxGroupId()).andReturn(max);
        replayMocks();

        int id;
        for (int i = 1; i < 100; ++i) {
            id = idGenerator.getNewGroupId();
            Assert.assertEquals(max + i, id);
        }
    }

    @Test
    public void testGetNewRightsId() throws Exception {
        int max = 5;
        EasyMock.expect(ldap.maxRightsId()).andReturn(max);
        replayMocks();

        int id;
        for (int i = 1; i < 100; ++i) {
            id = idGenerator.getNewRightsId();
            Assert.assertEquals(max + i, id);
        }
    }

}
