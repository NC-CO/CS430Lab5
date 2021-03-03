# CS430Lab5
I created a GUI for accessing a database. The database represents multiple libraries of books. It contains ISBN numbers, names of the books, shelf numbers, authors, library names, people who have checked out the books, and dates for when the books have been checked out. 

My GUI first checks to make sure the user has a valid ID. If they don't, it prompts the user to create one. The user can then search for the book they want to check out by ISBN number, name of the book, or by author. If the book is available, the gui tells the user which library it's available at and what shelf it's on. It also alerts the user if the book is not in stock.

The GUI was ran with: 

java -cp .:/usr/share/java/mysql-connector-java.jar Lab5 -cp .:/usr/share/java/mysql-connector-java.jar Lab5
