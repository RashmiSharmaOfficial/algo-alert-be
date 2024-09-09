package com.personal.algoAlert;

//import com.personal.codingPractice.mongodb.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AlgoAlertApplication {

//	@Autowired
//	ItemRepository groceryItemRepo;

	public static void main(String[] args) {
		SpringApplication.run(AlgoAlertApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//
//		System.out.println("-----CREATE GROCERY ITEMS-----\n");
//
//		createGroceryItems();
//
//		System.out.println("\n-----SHOW ALL GROCERY ITEMS-----\n");
//
//		showAllGroceryItems();
//
////		System.out.println("\n-----GET ITEM BY NAME-----\n");
////
////		getGroceryItemByName("Whole Wheat Biscuit");
////
////		System.out.println("\n-----GET ITEMS BY CATEGORY-----\n");
////
////		getItemsByCategory("millets");
////
////		System.out.println("\n-----UPDATE CATEGORY NAME OF SNACKS CATEGORY-----\n");
////
////		updateCategoryName("snacks");
////
////		System.out.println("\n-----DELETE A GROCERY ITEM-----\n");
////
////		deleteGroceryItem("Kodo Millet");
////
////		System.out.println("\n-----FINAL COUNT OF GROCERY ITEMS-----\n");
////
////		findCountOfGroceryItems();
//
//		System.out.println("\n-----THANK YOU-----");
//
//	}

	//CREATE
//	void createGroceryItems() {
//		System.out.println("Data creation started...");
//		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
//		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
//		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
//		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
//		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
//		System.out.println("Data creation complete...");
//	}
//
//	// READ
//	// 1. Show all the data
//	public void showAllGroceryItems() {
//
//		groceryItemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
//	}
//
//	// 2. Get item by name
//	public void getGroceryItemByName(String name) {
//		System.out.println("Getting item by name: " + name);
//		GroceryItem item = groceryItemRepo.findItemByName(name);
//		System.out.println(getItemDetails(item));
//	}
//
//	// 3. Get name and quantity of a all items of a particular category
//	public void getItemsByCategory(String category) {
//		System.out.println("Getting items for the category " + category);
//		List<GroceryItem> list = groceryItemRepo.findAll(category);
//
//		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getQuantity()));
//	}
//
//	// 4. Get count of documents in the collection
//	public void findCountOfGroceryItems() {
//		long count = groceryItemRepo.count();
//		System.out.println("Number of documents in the collection: " + count);
//	}
//
//	// Print details in readable form
//
//	public String getItemDetails(GroceryItem item) {
//
//		System.out.println(
//				"Item Name: " + item.getName() +
//						", \nQuantity: " + item.getQuantity() +
//						", \nItem Category: " + item.getCategory()
//		);
//
//		return "";
//	}
}

