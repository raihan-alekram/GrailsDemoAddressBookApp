package addressbookapp

class BootStrap {

    def init = { servletContext ->
		def writeRole = new Role(authority: 'ROLE_WRITE').save()
		def writeUser = new User(username: 'writer', password: 'writer').save()

		UserRole.create writeUser, writeRole

		UserRole.withSession {
			it.flush()
			it.clear()
		}

		assert User.count() == 1
		assert Role.count() == 1
		assert UserRole.count() == 1

    }
	
    def destroy = {
    }
}
