package org.mutabilitydetector;

/*
 * #%L
 * MutabilityDetector
 * %%
 * Copyright (C) 2008 - 2014 Graham Allan
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.junit.Test;
import org.mutabilitydetector.locations.Dotted;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mutabilitydetector.AnalysisResult.definitelyImmutable;
import static org.mutabilitydetector.locations.Dotted.dotted;

public class ConfigurationTest {

    @Test
    public void hasHardcodedResultForClass() throws Exception {
        Configuration hasIt = new ConfigurationBuilder() {
            @Override public void configure() {
                hardcodeResult(definitelyImmutable("i.am.hardcoded"));
            }
        }.build();
        Configuration doesNotHaveIt = new ConfigurationBuilder() {
            @Override public void configure() {
                hardcodeResult(definitelyImmutable("i.am.not.the.same.hardcoded.class"));
            }
        }.build();

        Dotted isHardcoded = dotted("i.am.hardcoded");
        Dotted notHardcoded = dotted("i.am.not.hardcoded");

        Map<Dotted, AnalysisResult> hardcodedResults = hasIt.hardcodedResults();
        assertThat(hardcodedResults.containsKey(isHardcoded), is(true));
        assertThat(hardcodedResults.containsKey(notHardcoded), is(false));
        assertThat(doesNotHaveIt.hardcodedResults().containsKey(notHardcoded), is(false));
    }

}
