package addressbookapp

class Address {

	String name
	String phone
	String email
	
    static constraints = {
		name  nullable: false, blank: false
		phone nullable: true,  blank: false
		email nullable: true,  blank: false, email:true		
    }

}
