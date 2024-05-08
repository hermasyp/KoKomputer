package com.catnip.kokomputer.data.source

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface FirebaseAuth {
    fun doLogin()

    fun getCurrentUser(): User?

    companion object {
        fun getInstance(): FirebaseAuth {
            return FirebaseAuthImpl()
        }
    }
}

class User()

class FirebaseAuthImpl() : FirebaseAuth {
    private var user: User? = null

    override fun doLogin() {
        user = User()
    }

    override fun getCurrentUser(): User? = user
}
