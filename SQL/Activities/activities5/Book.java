package activities;

public abstract class Book {

    String title;

    // Abstract method
    public abstract void setTitle(String title);

    // Concrete method
    public String getTitle() {
        return title;
    }
}
