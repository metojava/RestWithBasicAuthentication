package com.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;

public class AuthService {

	public boolean authenticate(String credenitals,List<String> clientInfo)
	{
		//YWRtaW46bWFtdWth
		if(credenitals == null)
			return false;
		String creds = null;
		try {
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(
					credenitals);
			creds = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		StringTokenizer st = new StringTokenizer(credenitals, ":");
		String user = st.nextToken();
		String pass = st.nextToken();
		for(String s:clientInfo)
		System.out.println(s);
		if(user.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("mamuka"))
			return true;
		
		return false;
		
	}
}
