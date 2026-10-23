import java.util.Scanner;

public class mainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehicle [] vehicles = new Vehicle[6];

        initVehicles(vehicles);

        boolean quit = false;
        while (!quit) {
            System.out.println("What would you like to do?\n" +
                    "1 - list all vehicles\n" +
                    "2 - search by make/model\n" +
                    "3 - search by price range\n" +
                    "4 - search by color\n" +
                    "5 - add a vehicle\n" +
                    "6 - quit");
            System.out.printf("Choice -> ");
            switch(Integer.parseInt(sc.nextLine().trim())) {
                case 1 -> listAllVehicles(vehicles, sc);
                case 2 -> searchByMakeModel(vehicles, sc);
                case 3 -> searchByPriceRange(vehicles, sc);
                case 4 -> searchByColor(vehicles, sc);
                case 5 -> {
                    while (true) {
                        Vehicle [] temp = addVehicle(vehicles, sc);
                        if (temp != null) {
                            vehicles = temp;
                            System.out.print("Vehicle created and stored. Would you like to add another?\ny/n -> ");
                            String restart = sc.nextLine().trim().toLowerCase();

                            if (!restart.equals("y")) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                case 6 -> quit = true;
                default -> System.out.println("Sorry, I didn't recognize that.");
            }
        }
    }

    public static void initVehicles(Vehicle [] vehicles) {
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i] = new Vehicle();
        }

        vehicles[0].initVehicle(101121,"Ford Explorer","Red",45000,13500);
        vehicles[1].initVehicle(101122,"Toyota Camry","Blue",60000,11000);
        vehicles[2].initVehicle(101123,"Chevrolet Malibu","Black",50000,9700);
        vehicles[3].initVehicle(101124,"Honda Civic","White",70000,7500);
        vehicles[4].initVehicle(101125,"Subaru Outback","Green",55000,14500);
        vehicles[5].initVehicle(101126,"Jeep Wrangler","Yellow",30000,16000);
    }


    public static void listAllVehicles(Vehicle [] vehicles, Scanner sc) {
        boolean quit = false;
        while (!quit) {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }

            while (true) {
                System.out.printf("Options:\n(R)epeat this output\n(Q)uit\nchoice -> ");
                String choice = sc.nextLine().trim().toLowerCase();
                if (choice.equals("q")) {
                    quit = true;
                    break;
                } else if (choice.equals("r")) {
                    break;
                } else {
                    System.out.println("Sorry, I didn't recognize that.");
                }
            }
        }
    }

    public static void searchMakeOrModel (Vehicle [] vehicles, Scanner sc, boolean searchModel) {
        String [] searchFor = new String[vehicles.length];
        for (int i = 0; i < vehicles.length; i++) {
            // variable length array, but we know the format of make/model is
            // "Make Model"
            // so splitting by space will place the make in the first index
            if (searchModel) {
                searchFor[i] = vehicles[i].getMakeModel().split(" ")[1];
            } else {
                searchFor[i] = vehicles[i].getMakeModel().split(" ")[0];
            }

        }
        while(true) {
            System.out.print("What would you like to search for?\nChoice -> ");
            String choice = sc.nextLine().trim();

            System.out.println("Searching for \"" + choice + "\"...");
            DynArray matches = new DynArray(10);
            boolean match_found = false;
            for (int i = 0; i < searchFor.length; i++) {
                if (searchFor[i].equals(choice)) {
                    matches.appendDA(i);
                    match_found = true;
                }
            }

            if (match_found) {
                if (matches.getSize() == 1) {
                    System.out.println("Match found!");
                } else {
                    System.out.printf("%d matches found!\n", matches.getSize() + 1);
                }
            } else {
                System.out.printf("Match was not found matching pattern \"%s\".\nWould you like to try again?\n" +
                                  "y/n -> ", choice);
                String restart = sc.nextLine().trim().toLowerCase();

                if (restart.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }

            int[] arr = matches.getArrDA();
            for (int i = 0; i < matches.getSize(); i++) {
                System.out.println(vehicles[arr[i]]);
            }

            System.out.print("Would you like to search again?\n" +
                                "y/n -> ");
            String restart = sc.nextLine().trim().toLowerCase();

            if (restart.equals("n")) {
                break;
            }
        }
    }

    public static void searchBothMakeModel(Vehicle [] vehicles, Scanner sc) {

        while(true) {
            System.out.print("What make would you like to search for?\nChoice -> ");
            String choice = sc.nextLine().trim();
            System.out.println("Searching for \"" + choice + "\"...");

            DynArray matches = new DynArray(10);
            boolean match_found = false;
            for (int i = 0; i < vehicles.length; i++) {
                if (vehicles[i].getMakeModel().equals(choice)) {
                    matches.appendDA(i);
                    match_found = true;
                }
            }

            if (match_found) {
                if (matches.getSize() == 1) {
                    System.out.println("Match found!");
                } else {
                    System.out.printf("%d matches found!\n", matches.getSize() + 1);
                }
            } else {
                System.out.printf("Match was not found matching pattern \"%s\".\nWould you like to try again?\n" +
                        "y/n -> ", choice);
                String restart = sc.nextLine().trim().toLowerCase();

                if (restart.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }

            int[] arr = matches.getArrDA();
            for (int i = 0; i < matches.getSize(); i++) {
                System.out.println(vehicles[arr[i]]);
            }

            System.out.print("Would you like to search again?\n" +
                    "y/n -> ");
            String restart = sc.nextLine().trim().toLowerCase();

            if (restart.equals("n")) {
                break;
            }
        }
    }

    public static void searchByMakeModel(Vehicle [] vehicles, Scanner sc) {
        while (true) {
            System.out.print("Would you like to search by (make) or (model) or (both)?\nChoice -> ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("make")) {
                searchMakeOrModel(vehicles, sc, false);
                break;
            } else if (input.equals("model")) {
                searchMakeOrModel(vehicles, sc,  true);
                break;
            } else if (input.equals("both")) {
                searchBothMakeModel(vehicles, sc);
                break;
            } else {
                System.out.println("Sorry, I didn't recognize that.");
            }
        }
    }

    public static void searchByPriceRange(Vehicle [] vehicles,  Scanner sc) {
        int times_fucked_up = 0;
        while (true) {
            System.out.print("Type the range of prices you are looking to purchase a vehicle in.\n" +
                    "Example: 5000 - 10000\nChoice -> ");
            String[] choice = sc.nextLine().split("-");
            if (choice.length != 2) {
                System.out.print("The correct input should be two numbers separated by a dash. TRY AGAIN!");
                for (int i = 0; i < times_fucked_up; i++) {
                    System.out.print("!!!!!");
                }
                System.out.print('\n');
                times_fucked_up ++;
                continue;
            }
            choice[0] = choice[0].trim();
            choice[1] = choice[1].trim();
            int lower = Integer.parseInt(choice[0]);
            int higher = Integer.parseInt(choice[1]);

            DynArray matches = new DynArray(10);
            System.out.println("Searching...");
            for (int i = 0; i < vehicles.length; i++) {
                if (vehicles[i].getPrice() >= lower && vehicles[i].getPrice() <= higher) {
                    matches.appendDA(i);
                }
            }

            if (matches.getSize() == 0) {
                System.out.printf("Match not found in the range between %d and %d.\nWould you like to try again?\n" +
                        "y/n -> ",
                        lower,
                        higher);
                String choice1 = sc.nextLine().trim();

                if (choice1.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }

            if (matches.getSize() == 1) {
                System.out.println("Match found!");
            } else {
                System.out.printf("%d matches found!\n", matches.getSize());
            }

            int [] arr = matches.getArrDA();
            for (int i = 0; i < matches.getSize(); i++) {
                System.out.println(vehicles[arr[i]]);
            }

            System.out.print("Would you like to search again?\n" +
                    "y/n -> ");
            String restart = sc.nextLine().trim().toLowerCase();
            if (restart.equals("n")) {
                break;
            }
        }
    }

    public static void searchByColor(Vehicle [] vehicles, Scanner sc) {
        while(true) {
            System.out.print("What color vehicle would you like to search for?\nChoice -> ");
            String choice = sc.nextLine().trim();
            System.out.println("Searching for \"" + choice + "\"...");
            DynArray matches = new DynArray(10);
            boolean match_found = false;
            for (int i = 0; i < vehicles.length; i++) {
                if (vehicles[i].getColor().equals(choice)) {
                    matches.appendDA(i);
                    match_found = true;
                }
            }

            if (match_found) {
                if (matches.getSize() == 1) {
                    System.out.println("Match found!");
                } else {
                    System.out.printf("%d matches found!\n", matches.getSize() + 1);
                }
            } else {
                System.out.printf("Match was not found matching pattern \"%s\".\nWould you like to try again?\n" +
                        "y/n -> ", choice);
                String restart = sc.nextLine().trim().toLowerCase();

                if (restart.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }

            int[] arr = matches.getArrDA();
            for (int i = 0; i < matches.getSize(); i++) {
                System.out.println(vehicles[arr[i]]);
            }

            System.out.print("Would you like to search again?\n" +
                    "y/n -> ");
            String restart = sc.nextLine().trim().toLowerCase();

            if (restart.equals("n")) {
                break;
            }
        }
    }

    public static Vehicle[] addVehicle(Vehicle [] vehicles, Scanner sc) {
        String restart;
        System.out.printf("Create a new vehicle?\ny/n -> ");
        restart = sc.nextLine().trim().toLowerCase();
        if (!restart.equals("y")) {
            return null;
        }
        
        String makemodel, color;
        int odometer;
        double sale_price;
        long VID;
        while (true) {
            System.out.print("What is the Make and Model of the vehicle you want to add?\n" +
                    "Enter make and model separated by spaces\nEnter -> ");
            makemodel = sc.nextLine().trim();
            
            System.out.print("What color is it?\nColor -> ");
            color = sc.nextLine().trim();
            
            System.out.print("What is the odometer reading?\n Odometer -> ");
            odometer = Integer.parseInt(sc.nextLine().trim());
            
            System.out.print("What will the car sell for? \nSale Price -> ");
            sale_price = Double.parseDouble(sc.nextLine().trim());
            
            System.out.print("What is the Vehicle ID no. ?\nVID -> ");
            VID = Long.parseLong(sc.nextLine().trim());

            Vehicle new_vehicle = new Vehicle(VID, makemodel, color, odometer, sale_price);

            System.out.println("The vehicle you are adding will have these characteristics:\n");
            System.out.println(new_vehicle);
            
            System.out.printf("Is this ok?\ny/n -> ");
            restart = sc.nextLine().trim().toLowerCase();

            if (!restart.equals("y")) {
                continue;
            }

            Vehicle [] newVehicles = new Vehicle[vehicles.length + 1];
            for (int i = 0; i < vehicles.length; i++) {
                newVehicles[i] = vehicles[i];
            }

            newVehicles[newVehicles.length - 1] = new_vehicle;
            return newVehicles;
        }
    }


}
