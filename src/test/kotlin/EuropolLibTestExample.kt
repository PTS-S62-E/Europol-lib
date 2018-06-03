import com.pts.europollib.EuropolLib
import com.pts.europollib.EuropolVehicle
import org.junit.Assert
import org.junit.Test

class EuropolLibTestExample {
    @Test
    fun test() {
        val lib = EuropolLib()

        val vehicleCount = lib.getVehicles().count()
        println("got $vehicleCount vehicles from the API ($vehicleCount)")

        lib.insertVehicle(EuropolVehicle("test", "test", "test"))
        val newVehicleCount = lib.getVehicles().count()
        println("Insert new vehicle, ${if (vehicleCount + 1 == newVehicleCount) "OK" else "Failed"} ($newVehicleCount)")
        Assert.assertEquals(vehicleCount + 1, newVehicleCount)

        val vehicleBySerial = lib.getVehicleBySerial("test")
        Assert.assertNotNull(vehicleBySerial)
        Assert.assertEquals(vehicleBySerial, EuropolVehicle("test", "test", "test"))

        lib.deleteVehicle("test")
        val deleteVehicleCount = lib.getVehicles().count()
        println("Delete new vehicle, ${if (vehicleCount == deleteVehicleCount) "OK" else "Failed"} ($deleteVehicleCount)")
        Assert.assertEquals(vehicleCount, deleteVehicleCount)
    }
}
