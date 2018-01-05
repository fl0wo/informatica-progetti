package observer_02;

public class User implements Observer {

	private String article;
	private Observable blog; // the observable object

	public void setObservable(Observable blog) {
		this.blog = blog;
		article = "No New Article!";
	}

	@Override
	public void update() { // used in observable i.e. blog
		System.out.println("State change reported by Subject.");
		article = (String) blog.getUpdate();
	}

	public String getArticle() {
		return article;
	}
}
