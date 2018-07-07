package addressbookapp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AddressServiceSpec extends Specification {

    AddressService addressService
    SessionFactory sessionFactory

    private Long setupData() {
        new Address(name: 'Steve', phone: '415-222-3333', email: 'steve@yahoo.com').save(flush: true, failOnError: true)
		new Address(name: 'Anna', phone: '650-444-5555', email: 'anna@gmail.com').save(flush: true, failOnError: true)
		Address address = new Address(name: 'Scott', phone: '647-888-6666', email: 'scott@aol.com').save(flush: true, failOnError: true)
		new Address(name: 'Betty', phone: '212-111-0000', email: 'betty@msn.com').save(flush: true, failOnError: true)
		new Address(name: 'Kelly', phone: '202-555-7777', email: 'kelly@outlook.com').save(flush: true, failOnError: true)
		address.id
    }

    void "test get"() {
        setupData()

        expect:
        addressService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Address> addressList = addressService.list(max: 2, offset: 2)

        then:
        addressList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        addressService.count() == 5
    }

    void "test delete"() {
        Long addressId = setupData()

        expect:
        addressService.count() == 5

        when:
        addressService.delete(addressId)
        sessionFactory.currentSession.flush()

        then:
        addressService.count() == 4
    }

    void "test save"() {
        when:
        Address address = new Address(name: 'Joy', phone: '650-111-2222', email: 'joy@gmail.com')
        addressService.save(address)

        then:
        address.id != null
    }
}
