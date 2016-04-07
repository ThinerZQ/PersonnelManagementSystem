package com.zq.util;

public class Encryption {

	public static String getPassword(String old_password){
		
		char new_password[] =old_password.toCharArray();
		String m = new String();
		
		char first_word=old_password.charAt(0);
		char last_word=old_password.charAt(old_password.length()-1);
		
		new_password[0]=last_word;
		new_password[old_password.length()-1]=first_word;
		
		for(int i=0;i<new_password.length;i++){
			m+=new_password[i];
		}
		return m;
	}
}
