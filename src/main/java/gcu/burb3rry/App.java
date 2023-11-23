package gcu.burb3rry;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Welcome to pet world!");

        PetStoreDataAccessObject dao = new PetStoreDataAccessObject();
        ArrayList<Pet> myPets;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Show All Pets");
            System.out.println("2. Fetch One Pet by ID");
            System.out.println("3. Search Pets by Name");
            System.out.println("4. Add a New Pet");
            System.out.println("5. Update a Pet");
            System.out.println("6. Delete a Pet");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myPets = dao.getAllPets();
                    System.out.println("================ Show All Pets ================");
                    for (Pet pet : myPets) {
                        System.out.println(pet);
                    }
                    break;

                case 2:
                    System.out.println("================ Fetch One Pet by ID ================");
                    System.out.print("Enter Pet ID: ");
                    int petId = scanner.nextInt();
                    Pet foundPet = dao.getById(petId);
                    System.out.println(foundPet != null ? foundPet : "Pet not found");
                    break;

                case 3:
                    System.out.println("================ Search Pets by Name ================");
                    System.out.print("Enter search term: ");
                    String searchTerm = scanner.next();
                    ArrayList<Pet> foundPets = dao.searchForMany(searchTerm);
                    System.out.println("These are the pets whose names match the search term:");
                    for (Pet pet : foundPets) {
                        System.out.println(pet);
                    }
                    break;

                case 4:
                    System.out.println("================ Add a New Pet ================");
                    Pet newPet = createPetFromUserInput(scanner);
                    int updates = dao.addOne(newPet);
                    System.out.println(updates + " items inserted");
                    break;

                case 5:
                    System.out.println("================ Update a Pet ================");
                    Pet updatedPet = createPetFromUserInput(scanner);
                    updates = dao.updateOne(updatedPet);
                    System.out.println(updates + " updates applied");
                    break;

                case 6:
                    System.out.println("================ Delete a Pet ================");
                    System.out.print("Enter Pet ID to delete: ");
                    int deletePetId = scanner.nextInt();
                    Pet deletePet = new Pet();
                    deletePet.setId(deletePetId);
                    updates = dao.deleteOne(deletePet);
                    System.out.println(updates + " deletes applied");
                    break;

                case 0:
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }

    private static Pet createPetFromUserInput(Scanner scanner) {
        Pet pet = new Pet();
        System.out.print("Enter Pet ID: ");
        pet.setId(scanner.nextInt());
        System.out.print("Enter Pet Name: ");
        pet.setName(scanner.next());
        System.out.print("Enter Pet Description: ");
        pet.setDescription(scanner.next());
        System.out.print("Enter Pet Price: ");
        pet.setPrice(scanner.nextDouble());
        System.out.print("Enter Pet Category ID: ");
        pet.setCategoryId(scanner.nextInt());
        return pet;
    }
}


