import domain.customer.Customer;
import domain.customer.CustomerType;
import domain.rental.Rental;
import domain.vehicle.Vehicle;
import domain.vehicle.VehicleType;
import repository.Repository;
import repository.VehicleRepositoryInMemory;
import service.vehicle.FindVehicleService;
import service.vehicle.RegisterVehicleService;
import service.vehicle.UpdateVehicleService;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        Repository<Vehicle> vr = new VehicleRepositoryInMemory();

        FindVehicleService fvs = new FindVehicleService(vr);
        RegisterVehicleService rvs = new RegisterVehicleService(vr, fvs);
        UpdateVehicleService uvs = new UpdateVehicleService(vr, fvs);

        rvs.register(VehicleType.SUV, "AAA123", "SUV", "2020");
        rvs.register(VehicleType.MEDIUM,"AAA124", "Medium", "2023");
        rvs.register(VehicleType.SMALL,"AAA125", "Small", "2010");

        System.out.println(fvs.findAll());
        System.out.println(fvs.findByPartialLicensePlate("AAA"));
        System.out.println(fvs.findByPartialLicensePlate("23"));
        System.out.println(fvs.findById(1));
        System.out.println(fvs.findById(5));

        System.out.println(uvs.update(1, "Nissan", ""));
        System.out.println(fvs.findAll());

        Customer teste2 = new Customer(CustomerType.PERSON, "CPF", "Nome 1");
        System.out.println(teste2);

        Rental rent = new Rental(1, 1);
        rent.setStartDateTime(LocalDateTime.of(2023, 9, 11, 14, 44, 45));
        rent.setStartPlace("Loja 1");
        System.out.println(rent);
        rent.setEndDateTime(LocalDateTime.of(2023, 9, 12, 14, 44, 45));
        rent.setEndPlace("Loja 2");
        System.out.println(rent);
    }
}