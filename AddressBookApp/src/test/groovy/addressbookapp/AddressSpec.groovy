package addressbookapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AddressSpec extends Specification implements DomainUnitTest<Address> {

    def setup() {
    }

    def cleanup() {
    }

    void "test new domain"() {
        expect:
        domain != null
        domain.name == null
		domain.phone == null
		domain.email == null
	}
   	
	void "test domain instance"() {
        when:
        domain.name = 'Betty'

        then:
        domain.name == 'Betty'
	}
   	
    void "test basic persistence "() {
        setup:
		new Address(name: 'Steve', phone: '415-222-3333', email: 'steve@yahoo.com').save()
		new Address(name: 'Anna',  phone: '650-444-5555', email: 'anna@gmail.com' ).save()
		
        expect:
        Address.count() == 2
    }
	
	void "test name cannot be null"() {
        when:
		domain.name = null
		
		then:
		!domain.validate(['name'])
		domain.errors['name'].code == 'nullable'		
    }
	
    void "test name cannot be blank"() {
        when:
		domain.name = ''
		
		then:
		!domain.validate(['name'])
		domain.errors['name'].code == 'blank'		
    }
	
    void "test phone can be null"() {
        when:
		domain.phone = null
		
		then:
		domain.validate(['phone'])
    }
	
    void "test phone cannot be blank"() {
        when:
		domain.phone = ''
		
		then:
		!domain.validate(['phone'])
		domain.errors['phone'].code == 'blank'		
    }
	
    void "test email can be null"() {
        when:
		domain.email = null
		
		then:
		domain.validate(['email'])
    }
	
    void "test email cannot be blank"() {
        when:
		domain.email = ''
		
		then:
		!domain.validate(['email'])
		domain.errors['email'].code == 'blank'		
    }
	
    void "test email should be of proper format"() {
        when:
		domain.email = 'abc'
		
		then:
		!domain.validate(['email'])
		domain.errors['email'].code == 'email.invalid'		
    }	
}
