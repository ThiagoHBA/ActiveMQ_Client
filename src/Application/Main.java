package Application;

import View.ContentView;

public class Main {

	public static void main(String[] args) {
		System.out.println("Displaying Content View");
		ContentView contentView = new ContentView();
		contentView.render();
	}

}
