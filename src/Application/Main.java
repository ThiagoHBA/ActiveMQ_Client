package Application;

import View.ManagerView;

public class Main {

	public static void main(String[] args) {
		System.out.println("Displaying Manager View");
		ManagerView managerView = new ManagerView();
		managerView.render();
	}

}
