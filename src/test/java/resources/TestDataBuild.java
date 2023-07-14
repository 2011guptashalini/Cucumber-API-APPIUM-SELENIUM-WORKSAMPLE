package resources;
import pojo.AddClient;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class TestDataBuild {
	
	public AddClient AddClientPayload() {
		
		AddClient ac = new AddClient();
		Faker faker = new Faker();
		ac.setClientEmail(faker.internet().emailAddress());
		ac.setClientName((faker.name()).toString());
		return ac;
	}


public AddClient AddClientInvalidPayload(String clientName, String clientEmail) {
	
	AddClient ac = new AddClient();
	ac.setClientEmail(clientEmail);
	ac.setClientName(clientName);
	return ac;
}
}


