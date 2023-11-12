package Application;

import View.ClientView;

public class Main {

	public static void main(String[] args) {
		System.out.println("Displaying Content View");
		ClientView contentView = new ClientView();
		contentView.render();
	}

}
