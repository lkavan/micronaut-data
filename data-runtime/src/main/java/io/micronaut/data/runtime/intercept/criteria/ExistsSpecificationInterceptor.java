/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.runtime.intercept.criteria;

import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.Internal;
import io.micronaut.data.intercept.RepositoryMethodKey;
import io.micronaut.data.operations.RepositoryOperations;

/**
 * Interceptor that supports exists specifications.
 *
 * @author Nick Hensel
 * @since 3.8
 */
@Internal
public class ExistsSpecificationInterceptor extends AbstractSpecificationInterceptor<Object, Boolean> {
    /**
     * Default constructor.
     *
     * @param operations The operations
     */
    protected ExistsSpecificationInterceptor(RepositoryOperations operations) {
        super(operations);
    }

    @Override
    public Boolean intercept(RepositoryMethodKey methodKey, MethodInvocationContext<Object, Boolean> context) {
        return getCriteriaRepositoryOperations(methodKey, context, null)
            .exists(buildExistsQuery(methodKey, context));
    }
}
