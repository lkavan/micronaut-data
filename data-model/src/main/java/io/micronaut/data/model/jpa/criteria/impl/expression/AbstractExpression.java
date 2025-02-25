/*
 * Copyright 2017-2024 original authors
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
package io.micronaut.data.model.jpa.criteria.impl.expression;

import io.micronaut.core.annotation.Internal;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.model.jpa.criteria.ExpressionType;
import io.micronaut.data.model.jpa.criteria.IExpression;

/**
 * The abstract expression.
 *
 * @param <E> The expression type
 * @author Denis Stepanov
 * @since 4.9
 */
@Internal
public abstract class AbstractExpression<E> implements IExpression<E> {

    @Nullable
    private final ExpressionType<E> expressionType;

    public AbstractExpression(@Nullable ExpressionType<E> expressionType) {
        this.expressionType = expressionType;
    }

    @Override
    public Class<? extends E> getJavaType() {
        return expressionType.getJavaType();
    }

    @NonNull
    public final ExpressionType<E> getExpressionType() {
        return expressionType;
    }
}
