/*
 * Copyright 2014 the original author or authors.
 *
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
 */

package org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.conflicts;

import org.gradle.api.Action;
import org.gradle.api.artifacts.ModuleIdentifier;
import org.gradle.api.internal.artifacts.ivyservice.resolveengine.ComponentResolutionState;

import java.util.Collection;

class DefaultConflictResolutionResult implements ConflictResolutionResult {
    private final Collection<? extends ModuleIdentifier> participatingModules;
    private final ComponentResolutionState selected;

    public DefaultConflictResolutionResult(Collection<? extends ModuleIdentifier> participatingModules, ComponentResolutionState selected) {
        this.participatingModules = participatingModules;
        this.selected = selected;
    }

    public void withParticipatingModules(Action<? super ModuleIdentifier> action) {
        for (ModuleIdentifier module : participatingModules) {
            action.execute(module);
        }
    }

    public ComponentResolutionState getSelected() {
        return selected;
    }

}
