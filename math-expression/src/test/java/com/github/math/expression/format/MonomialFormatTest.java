/*
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.math.expression.format;

import com.github.math.expression.Monomial;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * 介绍信息
 *
 * @author 王帅
 * @since 1.0
 */
class MonomialFormatTest {

    @Test
    void parse() {
        MonomialFormat format = new MonomialFormat();
        Monomial monomial = format.parse("23a");
        System.out.println(monomial.coefficient());
    }

}