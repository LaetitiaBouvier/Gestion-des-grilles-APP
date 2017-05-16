package gestiondesgrillesapp.controller;

import java.util.ArrayList;
import java.util.List;

import gestiondesgrillesapp.model.User;

public class ObjectDBUtilException {
	
	public static void main(String[] args){
		
		ArrayList<User> userList = new ArrayList<>();
		User u = new User("TEST", "Test", "1351", "test@gmail.com", false);
		userList.add(u);
		
		User user = (User) extractUserManagingExceptions(userList);
	}

	public static Object extractUserManagingExceptions(Object userL){
		
		@SuppressWarnings("unchecked")
		ArrayList<Object> userList = (ArrayList<Object>) userL;
		
		if(userList == null || userList.size() == 0){
//			throw new IllegalArgumentException("L'instance de  dont le numéro est : "+userList.get(0).getNumero()+" n'existe pas !");
		}
		else if(userList.size() > 1){
//			throw new IllegalArgumentException("Plusieurs utillisateurs semblent partager le même ID : "+userList.get(0).getID()+" !");
		}
		return userList.get(0);
	}
}
