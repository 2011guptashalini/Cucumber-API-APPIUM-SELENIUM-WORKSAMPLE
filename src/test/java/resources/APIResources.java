 package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResources {
	
	getStatusResponse("/status"),
	postRegisterClient("/api-clients"),
	postCart("/carts");
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
