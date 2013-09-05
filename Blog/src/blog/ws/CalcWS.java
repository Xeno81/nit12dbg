package blog.ws;

import javax.jws.*;

@WebService(serviceName="CalcProgram")
public class CalcWS {

	
	@WebMethod
	public int addition(int tal1, int tal2) {
		
		return tal1 + tal2;
	}
}
