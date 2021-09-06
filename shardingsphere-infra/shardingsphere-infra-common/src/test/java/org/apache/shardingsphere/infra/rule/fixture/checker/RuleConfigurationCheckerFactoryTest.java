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

package org.apache.shardingsphere.infra.rule.fixture.checker;

import org.apache.shardingsphere.infra.rule.checker.RuleConfigurationChecker;
import org.apache.shardingsphere.infra.rule.checker.RuleConfigurationCheckerFactory;
import org.apache.shardingsphere.infra.rule.fixture.TestRuleConfiguration;
import org.apache.shardingsphere.infra.rule.fixture.TestRuleConfigurationWithoutChecker;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class RuleConfigurationCheckerFactoryTest {
    
    @Test
    public void assertNewInstance() {
        Optional<RuleConfigurationChecker> checker = RuleConfigurationCheckerFactory.newInstance(new TestRuleConfiguration());
        assertTrue(checker.isPresent());
        assertTrue(checker.get() instanceof RuleConfigurationCheckerFixture);
    }
    
    @Test
    public void assertNewInstanceWithoutChecker() {
        Optional<RuleConfigurationChecker> checker = RuleConfigurationCheckerFactory.newInstance(new TestRuleConfigurationWithoutChecker());
        assertFalse(checker.isPresent());
    }
}
