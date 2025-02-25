/*
 * Copyright 2017-2020 original authors
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
package io.micronaut.data.hibernate

import io.micronaut.context.annotation.Property
import io.micronaut.data.hibernate.entities.RelPerson
import io.micronaut.data.hibernate.entities.UserWithWhere
import io.micronaut.data.repository.jpa.criteria.CriteriaQueryBuilder
import io.micronaut.data.tck.entities.Book
import io.micronaut.data.tck.repositories.BookSpecifications
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery

@MicronautTest(packages = "io.micronaut.data.tck.entities", rollback = false, transactional = false)
@Property(name = "datasources.default.name", value = "mydb")
@Property(name = 'datasources.default.db-type', value = 'postgres')
@Property(name = 'jpa.default.properties.hibernate.hbm2ddl.auto', value = 'create-drop')
class HibernatePostgresQuerySpec extends AbstractHibernateQuerySpec {

    @Inject
    AnimalRepository animalRepository

    void "test procedure"() {
        expect:
            animalRepository.add1Named(123) == 124
            animalRepository.add1Indexed(123) == 124
            animalRepository.add1(123) == 124
            animalRepository.add1Alias(123) == 124
    }

    void "test updateReturning"() {
        given:
        def saved = new UserWithWhere(id: UUID.randomUUID(), email: "test@email.com", deleted: true)
        userWithWhereRepository.save(saved)
        when:"Update returning custom native query executed"
        userWithWhereRepository.updateEmailById(saved.id, "test1@email.com")
        def obj = userWithWhereRepository.updateReturningCustom("test2@email.com", false, saved.id)
        then:"Object is returned"
        noExceptionThrown()
        obj
        obj.email == "test2@email.com"
        when:"Update email using native query and returning"
        def updatedEmail = userWithWhereRepository.updateAndReturnEmail("test3@email.com", saved.id)
        then:"Updated email value is returned"
        updatedEmail == "test3@email.com"
    }
}
