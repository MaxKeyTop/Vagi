package com.connsec.web.socialsignon;

import java.io.UnsupportedEncodingException;

public class UTL {

	public UTL() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		 String urldecodeString="https://api.weibo.com/oauth2/authorize?client_id=1396991563&redirect_uri=http%3A%2F%2Fsso.connsec.com%2Fvagi%2Flogon%2Foauth20%2Fcallback%2Fsinaweibo&response_type=code&scope=all";
		 String   urldcode   =   java.net.URLDecoder.decode(urldecodeString,   "utf-8");  
		 
		 System.out.println(urldcode);
	}

}
