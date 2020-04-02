package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Client cliente = new Client();
		Order order;
		
		System.out.println("Enter Client data:");
		System.out.print("Name: ");
		cliente.setName(sc.nextLine());
		System.out.print("Email: ");
		cliente.setEmail(sc.next());
		System.out.print("Birth date (DD/MM/YYYY): ");
		cliente.setBirthDate(sdf.parse(sc.next()));
		
		System.out.println("Enter Order data:");
		System.out.print("Status: ");
		String status = sc.next();
		order = new Order(date, OrderStatus.valueOf(status), cliente);
		System.out.print("How many items to this order? ");
		int qtdItem = sc.nextInt();
		
		for (int i = 0; i < qtdItem; i++) {
			sc.nextLine();
			Product product = new Product();
			OrderItem orderItem = new OrderItem();
			System.out.println("Enter #"+(i+1)+" item data:");
			System.out.print("Product name: ");
			product.setName(sc.nextLine());
			System.out.println("Product price: ");
			product.setPrice(sc.nextDouble());
			System.out.println("Product quantity: ");
			orderItem.setQuantity(sc.nextInt());
			orderItem.setProduct(product);
			orderItem.setPrice(orderItem.subTotal());
			order.addItem(orderItem);
		}
		
		System.out.println("ORDER SUMARY:");
		System.out.println("Order moment: "+sdf.format(order.getMoment()));
		System.out.println("Order status: "+order.getStatus());
		System.out.printf("Client: %s (%s) - %s%n", cliente.getName(),sdf.format(cliente.getBirthDate()), cliente.getEmail());
		System.out.println("Order Items:");
		List<OrderItem> items = order.getOrderItem();
		Double sum = 0.00;
		for (OrderItem item : items) {
			Product produto = item.getProduct();
			System.out.printf("%s, %.2f, Quantity: %d Subtotal: %.2f%n", produto.getName(),produto.getPrice(),item.getQuantity(), item.getPrice());
			sum += item.getPrice();
		}
		System.out.printf("Total price: %.2f", sum);
		sc.close();
		
	}

}
