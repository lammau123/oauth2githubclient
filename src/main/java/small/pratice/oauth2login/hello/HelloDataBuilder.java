package small.pratice.oauth2login.hello;

public class HelloDataBuilder {
	private String data;
	
	static public HelloDataBuilder create() {
		return new HelloDataBuilder();
	}
	
	public HelloDataBuilder withMessage(String message) {
		this.data = message;
		return this;
	}
	
	public HelloData build() {
		HelloData helloData = new HelloData(this.data);
		
		return helloData;
	}
}
