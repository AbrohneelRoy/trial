import java.util.*;
import java.util.stream.Collectors;

class Location {
    String id;
    String name;
    int distanceFromOrigin;

    public Location(String id, String name, int distanceFromOrigin) {
        this.id = id;
        this.name = name;
        this.distanceFromOrigin = distanceFromOrigin;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Distance from origin: " + distanceFromOrigin + " km)";
    }
}

class Ride {
    String rideId;
    String customerId;
    String driverId;
    String source;
    String destination;
    double distance;
    double fare;
    boolean isCompleted;

    public Ride(String rideId, String customerId, String driverId, String source, String destination, double distance) {
        this.rideId = rideId;
        this.customerId = customerId;
        this.driverId = driverId;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.fare = distance * 10;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "Ride ID: " + rideId +
                "\nFrom: " + source +
                "\nTo: " + destination +
                "\nDistance: " + distance + " km" +
                "\nFare: ₹" + fare +
                "\nStatus: " + (isCompleted ? "Completed" : "In Progress");
    }
}

class User {
    String id;
    String name;
    String password;
    int age;
    String gender;
    List<Ride> rideHistory;

    public User(String id, String name, String password, int age, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.rideHistory = new ArrayList<>();
    }
}

class Customer extends User {
    public Customer(String id, String name, String password, int age, String gender) {
        super(id, name, password, age, gender);
    }

    @Override
    public String toString() {
        return "Customer ID: " + id +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender;
    }
}

class CabDriver extends User {
    String currentLocationId;
    boolean isAvailable;

    public CabDriver(String id, String name, String password, int age, String gender, String currentLocationId) {
        super(id, name, password, age, gender);
        this.currentLocationId = currentLocationId;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Driver ID: " + id +
                "\nName: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nAvailable: " + (isAvailable ? "Yes" : "No");
    }
}

class Admin extends User {
    public Admin(String id, String name, String password, int age, String gender) {
        super(id, name, password, age, gender);
    }
}

public class ZulaCabSystem {
    static List<Customer> customers = new ArrayList<>();
    static List<CabDriver> cabDrivers = new ArrayList<>();
    static List<Ride> rides = new ArrayList<>();
    static Map<String, Location> locations = new HashMap<>();
    static Admin admin = new Admin("admin", "Admin", "admin123", 40, "Male");
    static int rideCounter = 1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        showMainMenu();
    }

    static void initializeData() {
        // locations
        locations.put("1", new Location("1", "a", 0));
        locations.put("3", new Location("3", "c", 4));
        locations.put("4", new Location("4", "D", 7));
        locations.put("6", new Location("6", "f", 9));
        locations.put("2", new Location("2", "b", 15));
        locations.put("7", new Location("7", "g", 18));
        locations.put("8", new Location("8", "H", 20));
        locations.put("5", new Location("5", "e", 23));

        // cab drivers
        cabDrivers.add(new CabDriver("1", "Raj", "111", 35, "Male", "1"));
        cabDrivers.add(new CabDriver("2", "Priya", "222", 28, "Female", "3"));
        cabDrivers.add(new CabDriver("3", "Amit", "333", 40, "Male", "4"));

        // customers
        customers.add(new Customer("1", "Arun", "99", 25, "Male"));
        customers.add(new Customer("2", "Bala", "88", 30, "Male"));
        customers.add(new Customer("3", "Chitra", "77", 27, "Female"));
    }

    static void showMainMenu() {
        while (true) {
            System.out.println("\nZULA CAB SYSTEM");
            System.out.println("1. Admin Login");
            System.out.println("2. Cab Driver Login");
            System.out.println("3. Customer Login");
            System.out.println("4. Customer Sign Up");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    cabDriverLogin();
                    break;
                case 3:
                    customerLogin();
                    break;
                case 4:
                    customerSignUp();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    static void adminLogin() {
        System.out.print("\nEnter Admin Password: ");
        String password = scanner.nextLine();

        if (!password.equals(admin.password)) {
            System.out.println("Invalid admin password.");
            return;
        }

        System.out.println("\nWelcome, Admin!");
        adminMenu();
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\nADMIN MENU");
            System.out.println("1. Add New Cab");
            System.out.println("2. Add New Location");
            System.out.println("3. View All Cabs");
            System.out.println("4. View All Customers");
            System.out.println("5. View All Locations");
            System.out.println("6. View All Rides");
            System.out.println("7. Logout");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewCab();
                    break;
                case 2:
                    addNewLocation();
                    break;
                case 3:
                    viewAllCabs();
                    break;
                case 4:
                    viewAllCustomers();
                    break;
                case 5:
                    viewAllLocations();
                    break;
                case 6:
                    viewAllRides();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addNewCab() {
        System.out.println("\nADD NEW CAB");
        System.out.print("Enter Driver ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Driver Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.println("\nAvailable Locations:");
        viewAllLocations();
        System.out.print("\nEnter Current Location ID: ");
        String locationId = scanner.nextLine();

        if (!locations.containsKey(locationId)) {
            System.out.println("Invalid location ID.");
            return;
        }

        cabDrivers.add(new CabDriver(id, name, password, age, gender, locationId));
        System.out.println("\nNew cab added successfully!");
    }

    static void addNewLocation() {
        System.out.println("\nADD NEW LOCATION");
        System.out.print("Enter Location ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Location Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Distance From Origin (km): ");
        int distance = scanner.nextInt();
        scanner.nextLine();

        locations.put(id, new Location(id, name, distance));
        System.out.println("\nNew location added successfully!");
    }

    static void viewAllCabs() {
        System.out.println("\nALL CABS");
        if (cabDrivers.isEmpty()) {
            System.out.println("No cabs available.");
            return;
        }

        for (CabDriver driver : cabDrivers) {
            Location loc = locations.get(driver.currentLocationId);
            System.out.println("\n" + driver);
            System.out.println("Current Location: " + (loc != null ? loc.name : "Unknown"));
            System.out.println("Total Rides: " + driver.rideHistory.size());
        }
    }

    static void viewAllCustomers() {
        System.out.println("\nALL CUSTOMERS");
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }

        for (Customer customer : customers) {
            System.out.println("\n" + customer);
            System.out.println("Total Rides: " + customer.rideHistory.size());
        }
    }

    static void viewAllLocations() {
        System.out.println("\nALL LOCATIONS");
        if (locations.isEmpty()) {
            System.out.println("No locations available.");
            return;
        }

        locations.values().stream()
                .sorted(Comparator.comparingInt(l -> l.distanceFromOrigin))
                .forEach(location -> System.out.println(location));
    }

    static void viewAllRides() {
        System.out.println("\nALL RIDES");
        if (rides.isEmpty()) {
            System.out.println("No rides available.");
            return;
        }

        for (Ride ride : rides) {
            System.out.println("\n" + ride);
            if (ride.driverId != null) {
                CabDriver driver = cabDrivers.stream()
                        .filter(d -> d.id.equals(ride.driverId))
                        .findFirst()
                        .orElse(null);
                System.out.println("Driver: " + (driver != null ? driver.name : "Unknown"));
            }
            Customer customer = customers.stream()
                    .filter(c -> c.id.equals(ride.customerId))
                    .findFirst()
                    .orElse(null);
            System.out.println("Customer: " + (customer != null ? customer.name : "Unknown"));
        }
    }

    static void cabDriverLogin() {
        System.out.println("\nCAB DRIVER LOGIN");
        System.out.print("Enter Driver ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        CabDriver driver = cabDrivers.stream()
                .filter(d -> d.id.equals(id) && d.password.equals(password))
                .findFirst()
                .orElse(null);

        if (driver == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("\nWelcome, " + driver.name + "!");
        driverMenu(driver);
    }

    static void driverMenu(CabDriver driver) {
        while (true) {
            System.out.println("\nDRIVER MENU");
            System.out.println("1. Accept Ride");
            System.out.println("2. Complete Ride");
            System.out.println("3. View Ride History");
            System.out.println("4. View Current Location");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    acceptRide(driver);
                    break;
                case 2:
                    completeRide(driver);
                    break;
                case 3:
                    viewDriverHistory(driver);
                    break;
                case 4:
                    viewCurrentLocation(driver);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void acceptRide(CabDriver driver) {
        if (!driver.isAvailable) {
            System.out.println("\nYou already have an active ride.");
            return;
        }

        List<Ride> pendingRides = rides.stream()
                .filter(r -> r.driverId == null)
                .collect(Collectors.toList());

        if (pendingRides.isEmpty()) {
            System.out.println("\nNo rides available at the moment.");
            return;
        }

        System.out.println("\nAVAILABLE RIDES");
        for (int i = 0; i < pendingRides.size(); i++) {
            Ride ride = pendingRides.get(i);
            System.out.println("\n" + (i + 1) + ". " + ride);
            System.out.println("Distance from you: " +
                    calculateDistance(driver.currentLocationId, getLocationIdByName(ride.source)) + " km");
        }

        System.out.print("\nSelect ride to accept (0 to cancel): ");
        int rideChoice = scanner.nextInt();
        scanner.nextLine();

        if (rideChoice == 0) return;
        if (rideChoice < 1 || rideChoice > pendingRides.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Ride selectedRide = pendingRides.get(rideChoice - 1);
        selectedRide.driverId = driver.id;
        driver.isAvailable = false;
        System.out.println("\nRide accepted successfully!");
    }

    static void completeRide(CabDriver driver) {
        List<Ride> activeRides = rides.stream()
                .filter(r -> r.driverId != null && r.driverId.equals(driver.id) && !r.isCompleted)
                .collect(Collectors.toList());

        if (activeRides.isEmpty()) {
            System.out.println("\nNo active rides to complete.");
            return;
        }

        Ride ride = activeRides.get(0);
        ride.isCompleted = true;
        driver.isAvailable = true;
        driver.currentLocationId = getLocationIdByName(ride.destination);
        driver.rideHistory.add(ride);

        Customer customer = customers.stream()
                .filter(c -> c.id.equals(ride.customerId))
                .findFirst()
                .orElse(null);
        if (customer != null) {
            customer.rideHistory.add(ride);
        }

        System.out.println("\nRide completed successfully!");
        System.out.println("Fare: ₹" + ride.fare);
        System.out.println("Your earnings: ₹" + (ride.fare * 0.7));
    }

    static void viewDriverHistory(CabDriver driver) {
        System.out.println("\nYOUR RIDE HISTORY");
        if (driver.rideHistory.isEmpty()) {
            System.out.println("No ride history available.");
            return;
        }

        for (Ride ride : driver.rideHistory) {
            System.out.println("\n" + ride);
            System.out.println("Earnings: ₹" + (ride.fare * 0.7));
        }
    }

    static void viewCurrentLocation(CabDriver driver) {
        Location location = locations.get(driver.currentLocationId);
        System.out.println("\nYour current location: " +
                (location != null ? location.name : "Unknown"));
    }

    static void customerLogin() {
        System.out.println("\nCUSTOMER LOGIN");
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = customers.stream()
                .filter(c -> c.id.equals(id) && c.password.equals(password))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("\nWelcome, " + customer.name + "!");
        customerMenu(customer);
    }

    static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\nCUSTOMER MENU");
            System.out.println("1. Book Ride");
            System.out.println("2. View Ride History");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookRide(customer);
                    break;
                case 2:
                    viewCustomerHistory(customer);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void bookRide(Customer customer) {
        System.out.println("\nBOOK A RIDE");

        System.out.println("\nAvailable Cabs:");
        List<CabDriver> availableDrivers = cabDrivers.stream()
                .filter(d -> d.isAvailable)
                .collect(Collectors.toList());

        if (availableDrivers.isEmpty()) {
            System.out.println("No cabs available at the moment.");
            return;
        }

        for (int i = 0; i < availableDrivers.size(); i++) {
            CabDriver driver = availableDrivers.get(i);
            Location loc = locations.get(driver.currentLocationId);
            System.out.println((i+1) + ". Cab ID: " + driver.id +
                    ", Driver: " + driver.name +
                    ", Location: " + (loc != null ? loc.name : "Unknown"));
        }

        System.out.println("\nAvailable Locations:");
        viewAllLocations();

        System.out.print("\nEnter Source Location ID: ");
        String sourceId = scanner.nextLine();
        System.out.print("Enter Destination Location ID: ");
        String destId = scanner.nextLine();

        if (!locations.containsKey(sourceId) || !locations.containsKey(destId)) {
            System.out.println("Invalid location IDs.");
            return;
        }

        if (sourceId.equals(destId)) {
            System.out.println("Source and destination cannot be same.");
            return;
        }

        Location source = locations.get(sourceId);
        Location destination = locations.get(destId);
        double distance = Math.abs(destination.distanceFromOrigin - source.distanceFromOrigin);

        CabDriver nearestDriver = availableDrivers.stream()
                .min(Comparator.comparingInt(d ->
                        Math.abs(locations.get(d.currentLocationId).distanceFromOrigin - source.distanceFromOrigin)))
                .orElse(null);

        if (nearestDriver == null) {
            System.out.println("No cabs available at the moment.");
            return;
        }

        Ride ride = new Ride("R" + rideCounter++, customer.id, nearestDriver.id, source.name, destination.name, distance);
        rides.add(ride);
        nearestDriver.isAvailable = false;

        System.out.println("\nRide booked successfully!");
        System.out.println(ride);
        System.out.println("Assigned Driver: " + nearestDriver.name +
                " (Cab ID: " + nearestDriver.id +
                ", Distance: " + calculateDistance(nearestDriver.currentLocationId, sourceId) + " km)");
    }

    static void viewCustomerHistory(Customer customer) {
        System.out.println("\nYOUR RIDE HISTORY");
        if (customer.rideHistory.isEmpty()) {
            System.out.println("No ride history available.");
            return;
        }

        for (Ride ride : customer.rideHistory) {
            System.out.println("\n" + ride);
            if (ride.driverId != null) {
                CabDriver driver = cabDrivers.stream()
                        .filter(d -> d.id.equals(ride.driverId))
                        .findFirst()
                        .orElse(null);
                System.out.println("Driver: " + (driver != null ? driver.name : "Unknown"));
            }
        }
    }

    static void customerSignUp() {
        System.out.println("\nCUSTOMER SIGN UP");
        System.out.print("Enter New ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        customers.add(new Customer(id, name, password, age, gender));
        System.out.println("\nSign Up Successful! You can now log in.");
    }

    static double calculateDistance(String location1Id, String location2Id) {
        Location loc1 = locations.get(location1Id);
        Location loc2 = locations.get(location2Id);
        if (loc1 == null || loc2 == null) return 0;
        return Math.abs(loc1.distanceFromOrigin - loc2.distanceFromOrigin);
    }

    static String getLocationIdByName(String name) {
        return locations.values().stream()
                .filter(l -> l.name.equalsIgnoreCase(name))
                .findFirst()
                .map(l -> l.id)
                .orElse(null);
    }
}