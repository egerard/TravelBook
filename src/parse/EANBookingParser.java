package parse;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

public class EANBookingParser {

	public boolean parse(HttpResponse response) {
		String payload = "";
		try {
			payload = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(payload.length() == 0){
			System.out.println("no response");
			return false;
		}
		
		System.out.println(payload);
		return true;
	}

}
