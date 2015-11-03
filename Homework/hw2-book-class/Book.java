/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author Nicholas Chan
 * @version 1.0
 */
class Book
{
    // The fields.
    private String author;
    private String title;
    private int pages;
    private String refNumber;
    private int borrowed;
    
    /**
     * Set the author and title fields when this object
     * is constructed.
     * 
     * @param bookAuthor The author of the book
     * @param bookTitle The title of the book 
     * @param bookPages The number of pages in the book
     */
    public Book(String bookAuthor, String bookTitle, int bookPages)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = bookPages;
        refNumber = "";
    }
    
    /**
     * Returns the name of the author
     *
     * @return author The name of the author 
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Returns the title of the book
     * 
     * @return title The title of the book
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Returns the number of pages in the book
     * 
     * @return pages The number of pages
     */
    public int getPages()
    {
        return pages;
    }
    
    /**
     * Prints the name of the author 
     */
    public void printAuthor()
    {
        System.out.println(author);
    }
    
    /**
     * Prints the title of the book
     */
    public void printTitle()
    {
        System.out.println(title);
    }
    
    /**
     * Prints the number of pages in the book
     */
    public void printPages()
    {
        System.out.println(pages);
    }
    
    /**
     * Borrow the book
     */
    public void borrow()
    {
        borrowed++;
    }
    
    /**
     * Print the details about the book
     */
    public void printDetails()
    {
        String ref = "ZZZ";
        if(refNumber.length() != 0)
        {
            ref = refNumber;
        }
        System.out.println("Title:" + title+", Author:" + author + ", Pages:" 
            + pages + ", Ref Number:" + ref + ", Borrowed " + borrowed + " times.");
    }
    
    /**
     * Set the reference number of the book
     * 
     * @param num Number to be changed to
     */
    public void setRefNumber(String num)
    {
        refNumber = num;
    }
}
