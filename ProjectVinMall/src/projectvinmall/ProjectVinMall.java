package projectvinmall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface IMall {

    String soString();

    void sortByName();

    void sortByRating();

    void sortByPrice();
}

public class ProjectVinMall {
    Scanner sc = new Scanner(System.in);

    ArrayList<String> BooksList = new ArrayList<>();
    ArrayList<String> AppliancesList = new ArrayList<>();
    ArrayList<String> DrinksList = new ArrayList<>();
    ArrayList<String> ElectronicsList = new ArrayList<>();
    ArrayList<String> FoodsList = new ArrayList<>();
    ArrayList<String> VehiclesList = new ArrayList<>();
    ArrayList<String> EmployeesList = new ArrayList<>();

    List<Book> bookData = new ArrayList<>();
    List<Appliance> applianceData = new ArrayList<>();
    List<Drink> drinkData = new ArrayList<>();
    List<Electronic> electronicData = new ArrayList<>();
    List<Food> foodData = new ArrayList<>();
    List<Vehicle> vehicleData = new ArrayList<>();
    List<Employee> employeeData = new ArrayList<>();

    HashMap<String, ArrayList<String>> dataMap = new HashMap<>();

    String[] fileList = {
        "foodin.txt",
        "appliancein.txt",
        "bookin.txt",
        "drinkin.txt",
        "electronicin.txt",
        "vehiclesin.txt",
        "employeein.txt"
    };

    public void setFile() {
        dataMap.put("foodin.txt", FoodsList);
        dataMap.put("appliancein.txt", AppliancesList);
        dataMap.put("bookin.txt", BooksList);
        dataMap.put("drinkin.txt", DrinksList);
        dataMap.put("electronicin.txt", ElectronicsList);
        dataMap.put("vehiclesin.txt", VehiclesList);
        dataMap.put("employeein.txt", EmployeesList);
    }

    ArrayList<String> inputLines = new ArrayList<>();
    ArrayList<String> outputLines = new ArrayList<>();

    String df;

    public void readData(String df) {
        setFile();
        try (Scanner sc = new Scanner(new File(df))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                System.out.println("Data loaded: " + line);
                dataMap.get(df).add(line);
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Input Exception # " + ex);
        }

    }

    public void loadData() {
        bookData.clear();
        employeeData.clear();
        applianceData.clear();
        drinkData.clear();
        electronicData.clear();
        vehicleData.clear();
        foodData.clear();
        for (int i = 0; i < fileList.length; i++) {
            readData(fileList[i]);
            switch (fileList[i]) {
                case "bookin.txt": {
                    for (int j = 0; j < BooksList.size(); j++) {
                        String[] item = BooksList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String author = item[4];
                        int pages = Integer.parseInt(item[5]);
                        bookData.add(new Book(id, name, price, rating, author, pages));
                    }
                    break;
                }
                case "employeein.txt": {
                    for (int j = 0; j < EmployeesList.size(); j++) {
                        String[] item = EmployeesList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        String position = item[2];
                        String phoneNum = item[3];
                        double salary = Double.parseDouble(item[4]);
                        employeeData.add(new Employee(id, name, position, phoneNum, salary));
                    }
                    break;
                }
                case "appliancein.txt": {
                    for (int j = 0; j < AppliancesList.size(); j++) {
                        String[] item = AppliancesList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String brand = item[4];
                        String model = item[5];
                        int releaseYear = Integer.parseInt(item[6]);
                        applianceData.add(new Appliance(id, name, price, rating, brand, model, releaseYear));
                    }
                    break;
                }
                case "drinkin.txt": {
                    for (int j = 0; j < DrinksList.size(); j++) {
                        String[] item = DrinksList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String size = item[4];
                        drinkData.add(new Drink(id, name, price, rating, size));
                    }
                    break;
                }
                case "electronicin.txt": {
                    for (int j = 0; j < ElectronicsList.size(); j++) {
                        String[] item = ElectronicsList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String brand = item[4];
                        String model = item[5];
                        int releaseYear = Integer.parseInt(item[6]);
                        int batteryCapacity = Integer.parseInt(item[7]);

                        electronicData.add(new Electronic(id, name, price, rating, brand, model, releaseYear, batteryCapacity));
                    }
                    break;
                }
                case "vehiclesin.txt": {
                    for (int j = 0; j < VehiclesList.size(); j++) {

                        String[] item = VehiclesList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String chassisNumber = item[4];
                        String engineType = item[5];
                        String brand = item[6];
                        int manufactureYear = Integer.parseInt(item[7]);
                        double inspectionFee = Double.parseDouble(item[8]);
                        vehicleData.add(new Vehicle(id, name, price, rating, chassisNumber, engineType, brand, manufactureYear, inspectionFee));
                    }
                    break;
                }
                case "foodin.txt": {
                    for (int j = 0; j < FoodsList.size(); j++) {
                        String[] item = FoodsList.get(j).split("-");
                        String id = item[0];
                        String name = item[1];
                        double price = Double.parseDouble(item[2]);
                        double rating = Double.parseDouble(item[3]);
                        String size = item[4];
                        foodData.add(new Food(id, name, price, rating, size));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void writeData(String df) {
        try (FileWriter fw = new FileWriter(df)) {

            ArrayList<String> tempList = dataMap.get(df);

            if (tempList != null) {

                for (String line : tempList) {
                    fw.write(line + System.lineSeparator());
                    System.out.println("Data stored: " + line);

                }
            }

        } catch (IOException ex) {
            System.out.println("Output Exception # " + ex);
        }
    }

    public void saveData() {
        for (String file : fileList) {
            switch (file) {
                case "bookin.txt":
                    BooksList.clear();
                    for (Book b : bookData) {
                        BooksList.add(b.toString());
                    }
                    dataMap.put(file, BooksList);
                    break;

                case "employeein.txt":
                    EmployeesList.clear();
                    for (Employee e : employeeData) {
                        EmployeesList.add(e.getInfo());
                    }
                    dataMap.put(file, EmployeesList);
                    break;

                case "appliancein.txt":
                    AppliancesList.clear();
                    for (Appliance a : applianceData) {
                        AppliancesList.add(a.toString());
                    }
                    dataMap.put(file, AppliancesList);
                    break;

                case "drinkin.txt":
                    DrinksList.clear();
                    for (Drink d : drinkData) {
                        DrinksList.add(d.toString());
                    }
                    dataMap.put(file, DrinksList);

                    break;

                case "electronicin.txt":
                    ElectronicsList.clear();
                    for (Electronic e : electronicData) {
                        ElectronicsList.add(e.toString());
                    }
                    dataMap.put(file, ElectronicsList);
                    System.out.println("---Electronic store data---");
                    break;

                case "vehiclesin.txt":
                    VehiclesList.clear();
                    for (Vehicle v : vehicleData) {
                        VehiclesList.add(v.toString());
                    }
                    dataMap.put(file, VehiclesList);
                    System.out.println("---Vehicle store data---");
                    break;

                case "foodin.txt":
                    FoodsList.clear();
                    for (Food f : foodData) {
                        FoodsList.add(f.toString());
                    }
                    dataMap.put(file, FoodsList);
                    System.out.println("---Food store data---");
                    break;

                default:
                    break;
            }
            writeData(file);
        }
    }

    //--------------------------------------------------Ham ho tro -------------------------------------------------------------------------------------------------------------------------------
    void listEmployee() {
        employeeData.forEach(Employee -> {
            System.out.println(Employee.getInfo());
        });
    }
    void listAppliance(){
        applianceData.forEach(Appliance -> System.out.println(Appliance.toString()));
    }
    void addAppliance(){
        System.out.print("Please enter ID: ");
        String Aid = getValidString();
        System.out.print("Please enter Name: ");
        String Aname= getValidString();
        System.out.print("Please enter Price: ");
        double Aprice = sc.nextDouble();
        System.out.print("Please enter Rating: ");
        double Arating = sc.nextDouble();
        sc.nextLine();
        System.out.print("Please enter Brand: ");
        String Abrand = getValidString();
        System.out.print("Please enter Model: ");
        String Amodel = getValidString();
        System.out.print("Please enter Release Year: ");
        int AreleaseYear = sc.nextInt();
        Appliance newapp = new Appliance(Aid, Aname, Aprice, Arating,Abrand,Amodel,AreleaseYear);
        applianceData.add(newapp);
        System.out.println("Appliance added success.");
    }
    void deleteAppliance(){
        System.out.print("Please enter ID: ");
        String Aid = getValidString();
        for (int index = 0; index < applianceData.size();index++){
            if (applianceData.get(index).getId().equalsIgnoreCase(Aid)){
                applianceData.remove(index);
                System.out.println("Appliance deleted success.");
                break;
            }
            else{
                System.out.println("Appliance ID not found.");
                break;
            }
        }
    }
    void editAppliance(){
        System.out.print("Please enter ID: ");
        String Aid = getValidString();
        for (int index = 0; index < applianceData.size();index++){
            if (applianceData.get(index).getId().equalsIgnoreCase(Aid)){
                System.out.print("Please enter Name: ");
                String Anewname= getValidString();
                System.out.print("Please enter Price: ");
                double Anewprice = sc.nextDouble();
                System.out.print("Please enter Rating: ");
                double Anewrating = sc.nextDouble();
                sc.nextLine();
                System.out.print("Please enter Brand: ");
                String Anewbrand = getValidString();
                System.out.print("Please enter Model: ");
                String Anewmodel = getValidString();
                System.out.print("Please enter Release Year: ");
                int AnewreleaseYear = sc.nextInt();
                Appliance newapp = new Appliance(Aid, Anewname, Anewprice, Anewrating,Anewbrand,Anewmodel,AnewreleaseYear);
                applianceData.set(index, newapp);
                break;
            }
            else{
                System.out.println("Appliance ID not found.");
                break;
            }
        }
    }
    void sortAppliance(){
        boolean Asort = true;
        while(Asort){
            System.out.println("Sort by: ");
            System.out.println("1. Brand");
            System.out.println("2. Price");
            System.out.println("0. Exit");
            int choose = getValidInput(3);
            switch(choose){
                case 1:
                    applianceData.sort(Comparator.comparing(Appliance::getBrand));
                    System.out.println("Sorted by Price successfully!");
                    applianceData.forEach(Appliance -> System.out.println(Appliance.toString()));
                    Asort=false;
                break;
                case 2:
                    applianceData.sort(Comparator.comparing(Appliance::getPrice));
                    System.out.println("Sorted by Price successfully!");
                    applianceData.forEach(Appliance -> System.out.println(Appliance.toString()));
                    Asort=false;
                break;
                case 0:
                    System.out.println("Exit.");
                    Asort=false;
                break;
                default:
                    System.out.println("Please choose again!");
            }
        }
    }
    void searchAppliance(){
        boolean Asearch=true;
        while(Asearch){
            System.out.println("Search by:");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("0. Exit");
            int choose = getValidInput(3);
            switch(choose){
                case 1:
                    System.out.print("Enter ID: ");
                    String searchId = getValidString();
                    boolean foundId = false;
                    for (Appliance a : applianceData) {
                        if (a.getId().equalsIgnoreCase(searchId)) {
                            System.out.println("Found appliance:");
                            System.out.println(a.toString());
                            foundId = true;
                            break;
                        }
                    }
                    if (!foundId) {
                        System.out.println("ID not found!");
                    }
                    Asearch = false;
                    break;
                case 2:
                    System.out.print("Enter Name: ");
                    String searchName = getValidString().toLowerCase();
                    boolean foundName = false;
                    for (Appliance a : applianceData) {
                        if (a.getName().equalsIgnoreCase(searchName)) {
                            if (!foundName) {
                                System.out.println("Found appliance:");
                                foundName = true;
                            }
                            System.out.println(a.toString());
                        }
                    }
                    if (!foundName) {
                        System.out.println("Name not found!");
                    }
                    Asearch = false;
                    break;
                case 0:
                    System.out.println("Exit.");
                    Asearch = false;
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        }
    }

    //----------------------------------------------------Anh em them ham UI o day----------------------------------------------------------------------------------------------------------------
    public void mainUI() {//Vi du y nhu cai nay
        System.out.println("----MALL MANAGEMENT SYSTEM----");
        System.out.println("1. Manage Books");
        System.out.println("2. Manage Appliances");
        System.out.println("3. Manage Drinks");
        System.out.println("4. Manage Electronics");
        System.out.println("5. Manage Foods");
        System.out.println("6. Manage Vehicles");
        System.out.println("7. Manage Employees");
        System.out.println("0. Exit");

    }

    public void EmployeeUI() {

        System.out.println("----EMPLOYEE MANAGEMENT----");
        System.out.println("1. Hire Emloyee");
        System.out.println("2. Edit Emloyee Information");
        System.out.println("3. Dismiss Employee");
        System.out.println("4. Find Employee");
        System.out.println("0. Back To MALL MANAGEMENT");

    }

    public void ApplianceUI() {
        System.out.println("----EMPLOYEE MANAGEMENT----");
        System.out.println("1. Display list");
        System.out.println("2. Add new appliance");
        System.out.println("3. Edit information");
        System.out.println("4. Delete appliance");
        System.out.println("5. Sort by (brand or price)");
        System.out.println("6. Search information by (id or name)");
        System.out.println("0. Back To MALL MANAGEMEN");
    }

    //###########################Dung ham nay de nhan input ma nguoi dung nhap ############################
    public static int getValidInput(int range) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print("Please select an option: ");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 0 && input <= range) { //Nho truyen range la gia tri toi da vao 
                    break; // valid
                } else {
                    System.out.printf("The value is out of range(0-%d).", range);
                }
            } else {
                System.out.println("Vui long nhap so nguyen.");
                scanner.next();
            }
        }

        return input;
    }
    public static String getValidString(){
        Scanner scanner = new Scanner(System.in);
        String string=null;
        
        while (true){  
            string = scanner.nextLine().trim();        
            if (string == null || string.isEmpty()){
                System.out.println("Try again!");
            } else {
                break;
            }     
        }       
        return string;
    }

    public static void clearScreen() {
        try {
            Thread.sleep(1000);
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Không thể xóa màn hình: " + e.getMessage());
        }
    }
    // clear cmd cho dep

    //----------------------------------------------------Bat dau code o day-----------------------------------------------------------------------
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        /*----------------------------------Mang luu du lieu cua anh em day--------------------------------------------------------------------------
        
        List<Book> bookData = new ArrayList<>();
        List<Appliance> applianceData = new ArrayList<>();
        List<Drink> drinkData = new ArrayList<>();
        List<Electronic> electronicData = new ArrayList<>();
        List<Food> foodData = new ArrayList<>();
        List<Vehicle> vehicleData = new ArrayList<>();
        List<Employee> employeeData = new ArrayList<>();
        
         */
        ProjectVinMall VinMall = new ProjectVinMall();

        VinMall.loadData();

        boolean stop = false;

        while (!stop) {
            clearScreen();
            VinMall.mainUI();
            int MainSelect = getValidInput(7);

            switch (MainSelect) {
                case 1: //Book

                    //------------------------------------endBook-------------------------------------
                    break;
                case 2://Appliance
                    boolean stopA = true;
                    while(stopA){
                        VinMall.ApplianceUI();
                        int ApplianceSelect = getValidInput(7);
                        switch(ApplianceSelect){
                            case 1:
                                VinMall.listAppliance();
                            break;
                            case 2:
                                VinMall.addAppliance();
                            break;
                            case 3:
                                VinMall.editAppliance();
                            break;
                            case 4:
                                VinMall.deleteAppliance();
                            break;
                            case 5:
                                VinMall.sortAppliance();
                            break;
                            case 6:
                                VinMall.searchAppliance();
                            break;
                            default:
                                System.out.println("Data saved.");
                                System.out.println("Exit.");
                                stopA = false;
                            break;
                        }
                    }
                    //------------------------------------endAppliance--------------------------------
                    break;
                case 3://Drinks

                    //------------------------------------endDrinks--------------------------------
                    break;
                case 4://Electronic

                    //------------------------------------endElectronic--------------------------------
                    break;
                case 5://Food

                    //------------------------------------endFood--------------------------------
                    break;
                case 6://Vehicle

                    //------------------------------------endVehicle--------------------------------
                    break;
                case 7://Employee

                    VinMall.listEmployee();
                    VinMall.EmployeeUI();
                    int EmployeeSelect = getValidInput(4);

                    //------------------------------------endEmployee--------------------------------
                    break;
                default:
                    VinMall.saveData();
                    clearScreen();
                    System.out.println("Data saved.");
                    System.out.println("Exit.");
                    stop = true;
            }
        }

    }
}
