package WolframCloudPackage;

//different types of new documents that can be created
public enum DocType{
	Notebook, Package, Template, JavaScript, Text, XML, CSS, HTML;
	
	private String id;
	
	//Id = {"nb", "wl", "temp", "js", "txt", "xml", "css", "html"};
	static{
		Notebook.id = "nb";
		Package.id = "wl";
		Template.id = "temp";
		JavaScript.id = "js";
		Text.id = "txt";
		XML.id = "xml";
		CSS.id = "css";
		HTML.id = "html";
	}
	public String getId(){
		return id;
	}
}