package demo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@SuppressWarnings('MethodName')
@TestFor(Hotel)
class HotelSpec extends Specification {

    @Unroll
    void "name is required. #name is #description"() {
        when:
        def hotel = new Hotel(name: name)

        then:
        expected == hotel.validate(['name'])
        hotel.errors['name']?.code == code

        where:
        name     | expected | code
        'Hilton' | true     | null
        null     | false    | 'nullable'
        ''       | false    | 'nullable'

        description = expected ? 'valid' : "invalid with error code ${code}"
    }

    void "test featuredImageUrl is optional"() {
        expect:
        new Hotel(featuredImageUrl: null).validate(['featuredImageUrl'])
    }
}
