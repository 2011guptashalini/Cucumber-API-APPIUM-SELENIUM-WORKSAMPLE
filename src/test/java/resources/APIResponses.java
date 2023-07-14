 package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResponses {
	
	GetStatusResponse("GetStatusResponse.class"),
	AddClientResponse("AddClientResponse.class"),
	PostCartResponse("PostCartResponse.class");
	private String response;
	
	APIResponses(String response)
	{
		this.response=response;
	}
	
	public String getResponse()
	{
		return response;
	}

	

}
