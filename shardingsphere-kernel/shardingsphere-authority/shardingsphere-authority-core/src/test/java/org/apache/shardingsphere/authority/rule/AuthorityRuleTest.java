/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.authority.rule;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Properties;
import org.apache.shardingsphere.authority.config.AuthorityRuleConfiguration;
import org.apache.shardingsphere.authority.model.ShardingSpherePrivileges;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.infra.metadata.user.Grantee;
import org.apache.shardingsphere.infra.metadata.user.ShardingSphereUser;
import org.junit.Test;

public final class AuthorityRuleTest {

    @Test
    public void assertFindUser() {
        AuthorityRule rule = createAuthorityRule();
        Optional<ShardingSphereUser> adminUser = rule.findUser(new Grantee("admin", "localhost"));
        assertTrue(adminUser.isPresent());
        assertThat(adminUser.get().getGrantee().getUsername(), is("admin"));
        assertThat(adminUser.get().getGrantee().getHostname(), is("localhost"));
        Optional<ShardingSphereUser> notFindUser = rule.findUser(new Grantee("admin", "127.0.0.1"));
        assertFalse(notFindUser.isPresent());
    }

    @Test
    public void assertFindPrivileges() {
        AuthorityRule rule = createAuthorityRule();
        Optional<ShardingSpherePrivileges> privileges = rule.findPrivileges(new Grantee("admin", "localhost"));
        assertTrue(privileges.isPresent());
    }

    @Test
    public void assertRefresh() {
        Collection<ShardingSphereUser> users = new LinkedList<>();
        users.add(new ShardingSphereUser("root", "root", "localhost"));
        users.add(new ShardingSphereUser("admin", "123456", "localhost"));
        users.add(new ShardingSphereUser("sharding-sphere", "123456", "127.0.0.1"));
        AuthorityRule rule = createAuthorityRule();
        rule.refresh(Collections.emptyMap(), users);
        Optional<ShardingSpherePrivileges> privileges = rule.findPrivileges(new Grantee("sharding-sphere", "localhost"));
        assertTrue(privileges.isPresent());
    }
    
    private AuthorityRule createAuthorityRule() {
        Collection<ShardingSphereUser> users = new LinkedList<>();
        users.add(new ShardingSphereUser("root", "root", "localhost"));
        users.add(new ShardingSphereUser("admin", "123456", "localhost"));
        AuthorityRuleConfiguration ruleConfig = new AuthorityRuleConfiguration(users, new ShardingSphereAlgorithmConfiguration("ALL_PERMITTED", new Properties()));
        return new AuthorityRule(ruleConfig, Collections.emptyMap());
    }
}