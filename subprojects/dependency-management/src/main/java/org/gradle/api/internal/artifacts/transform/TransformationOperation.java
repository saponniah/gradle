/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.api.internal.artifacts.transform;

import org.gradle.internal.operations.BuildOperationCategory;
import org.gradle.internal.operations.BuildOperationContext;
import org.gradle.internal.operations.BuildOperationDescriptor;
import org.gradle.internal.operations.RunnableBuildOperation;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;

class TransformationOperation implements RunnableBuildOperation {
    private final Transformation transformation;
    private final TransformationSubject subject;
    private final ArtifactTransformDependenciesProvider dependenciesProvider;
    private TransformationSubject result;

    TransformationOperation(Transformation transformation, TransformationSubject subject, ArtifactTransformDependenciesProvider dependenciesProvider) {
        this.transformation = transformation;
        this.subject = subject;
        this.dependenciesProvider = dependenciesProvider;
    }

    @Override
    public void run(@Nullable BuildOperationContext context) {
        result = transformation.transform(subject, dependenciesProvider);
    }

    @Override
    public BuildOperationDescriptor.Builder description() {
        String displayName = "Transform " + subject.getDisplayName() + " with " + transformation.getDisplayName();
        return BuildOperationDescriptor.displayName(displayName)
            .progressDisplayName(displayName)
            .operationType(BuildOperationCategory.UNCATEGORIZED);
    }

    @Nullable
    public Throwable getFailure() {
        return result.getFailure();
    }

    @Nullable
    public List<File> getResult() {
        return result.getFiles();
    }
}
