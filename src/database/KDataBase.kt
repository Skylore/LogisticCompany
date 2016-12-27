package database

import exceptions.BookedLoginException
import geolocation.controller.GoogleMapsAPIImpl
import model.*

import com.sun.istack.internal.NotNull

import java.util.*

class KDataBase {

    val departments: List<Department> = ArrayList()
    private val requests = LinkedList<Request>()
    private val delivered = ArrayList<Request>()
    private val workRequests = ArrayList<WorkRequest>()
    private val supportRequests = ArrayList<SupportRequest>()
    private var users: HashMap<String, User> = HashMap()

    @Throws(BookedLoginException::class)
    fun addUser(@NotNull user: User) {
        if (users.containsKey(user.login)) {
            throw BookedLoginException("Please choose another login")
        }

        users.put(user.login, user)
    }

    fun removeUser(@NotNull login: String): User? {
        try {
            return users.remove(login)
        } catch (e: Exception) {
            throw NoSuchElementException()
        }

    }

    fun addWorkRequest(@NotNull request: WorkRequest) {
        workRequests.add(request)
    }

    fun addRequest(@NotNull request: Request) {
        requests.add(request)
    }

    fun removeRequest(name: String): Request? {
        var res: Request? = null

        for (r in requests) {
            if (r.product.name == name) {
                res = r
                break
            }
        }
        requests.remove(res)
        return res
    }

    fun deliver(@NotNull request: Request) {
        delivered.add(request)
    }

    fun removeDelivered(id: Int): Request {
        for (request in delivered) {
            if (request.id == id) {
                try {
                    delivered.removeAt(id)
                    return request
                } catch (e: IndexOutOfBoundsException) {
                    e.printStackTrace()
                }

            }
        }

        throw IndexOutOfBoundsException()
    }

    fun addSupportRequest(@NotNull supportRequest: SupportRequest) {
        supportRequests.add(supportRequest)
    }

    fun removeSupportRequest(id: Int): SupportRequest {

        for (i in supportRequests.indices) {
            if (supportRequests[i].id == id) {
                return supportRequests.removeAt(i)
            }
        }

        throw NoSuchElementException()
    }

    private object DepartmentList {

        private val departments = ArrayList<Department>()


        private fun initDepartments() {
            val googleMapsAPI = GoogleMapsAPIImpl()

            val department4 = Department(4, googleMapsAPI.findLocation("Україна", "Запоріжжя", "Перемоги", "40"))

            val department3 = Department(3, googleMapsAPI.findLocation("Україна", "Вінниця", "Коцюбинського", "30"))

            val department2 = Department(2, googleMapsAPI.findLocation("Україна", "Харків", "Сумська", "126"))

            val department1 = Department(1, googleMapsAPI.findLocation("Україна", "Львів", "Словацького", "1"))

            val department = Department(0, googleMapsAPI.findLocation("Україна", "Київ", "Хрещатик", "22"))

            departments.add(department)
            departments.add(department1)
            departments.add(department2)
            departments.add(department3)
            departments.add(department4)

        }

        private fun getDepartments(): List<Department> {
            initDepartments()
            return departments
        }
    }

    fun getRequests(): List<Request> {
        return requests
    }

    fun getDelivered(): List<Request> {
        return delivered
    }

    fun getSupportRequests(): List<SupportRequest> {
        return supportRequests
    }

    fun getWorkRequests(): List<WorkRequest> {
        return workRequests
    }

    fun getUsers(): Map<String, User> {
        return users
    }
}
