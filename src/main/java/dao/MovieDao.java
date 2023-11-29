package dao;

import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.Employee;
import model.Movie;
import java.sql.*;

public class MovieDao {

	
	public List<Movie> getMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */
		List<Movie> movies = new ArrayList<>();

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmnest-db", "root", "Ethiopia00@");
                 Statement statement = connection.createStatement()) {

                // Step 3: Execute the query to get all movies
                String selectQuery = "SELECT * FROM Movie;";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                // Step 4: Process the result set and add movies to the list
                while (resultSet.next()) {
                    // Populate Movie object from the result set
                    Movie movie = new Movie();
                    movie.setMovieID(resultSet.getInt("ID"));
                    movie.setMovieName(resultSet.getString("Name"));
                    movie.setMovieType(resultSet.getString("Type"));
                    movie.setDistFee(resultSet.getInt("DistrFee"));
                    movie.setNumCopies(resultSet.getInt("NumCopies"));
                    movie.setRating(resultSet.getInt("Rating"));

                    // Add the Movie object to the list
                    movies.add(movie);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
		
		
		return movies;

	}
	
	public Movie getMovie(String movieID) {

		/*
		 * The students code to fetch data from the database based on "movieID" will be written here
		 * movieID, which is the Movie's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Movie" class object
		 */

		Movie movie = new Movie();

//        try {
//            // Step 1: Load the JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Step 2: Establish the connection
//            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmnest-db", "root", "Ethiopia00@");
//                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie WHERE ID = ?")) {
//
//                // Set the movieID as a parameter for the PreparedStatement
//                preparedStatement.setInt(1, Integer.parseInt(movieID));
//
//                // Execute the query
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                // Check if the result set is not empty
//                if (resultSet.next()) {
//                    // Populate Movie object from the result set
//                    movie.setMovieID(resultSet.getInt("ID"));
//                    movie.setMovieName(resultSet.getString("Name"));
//                    movie.setMovieType(resultSet.getString("Type"));
//                    movie.setDistFee(resultSet.getInt("DistrFee"));
//                    movie.setNumCopies(resultSet.getInt("NumCopies"));
//                    movie.setRating(resultSet.getInt("Rating"));
//                } else {
//                    // Handle the case when no movie with the given ID is found
//                    System.out.println("Movie with ID " + movieID + " not found.");
//                }
//
//            }
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        }

        return movie;
	}
	
	public String addMovie(Movie movie) {

		/*
		 * All the values of the add movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the movie details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		
		//my changes
		try {
		    // Step 1: Load the JDBC driver
		    Class.forName("com.mysql.cj.jdbc.Driver");

		    // Step 2: Establish the connection
		    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmnest-db", "root", "");
		         PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movie (ID, Name, Type, DistrFee, NumCopies, Rating) VALUES (?, ?, ?, ?, ?, ?)")) {

		        // Set values for the PreparedStatement
		        preparedStatement.setInt(1, 11);
		        preparedStatement.setString(2, "Alex");
		        preparedStatement.setString(3, "Drama");
		        preparedStatement.setInt(4, 10000);
		        preparedStatement.setInt(5, 3);
		        preparedStatement.setInt(6, 1);

		        // Execute the update
		        int rowsAffected = preparedStatement.executeUpdate();

		        // Check if the insertion was successful
		        if (rowsAffected > 0) {
		            System.out.println("Movie inserted successfully!");
		            return "success";
		        } else {
		            System.out.println("Failed to insert movie.");
		            return "failure";
		        }
		    }

		} catch (Exception e) {
		    System.err.println("Error: " + e.getMessage());
		    return "failure";
		}
		/*Sample data ends*/

	}
	
	public String editMovie(Movie movie) {
		/*
		 * All the values of the edit movie form are encapsulated in the movie object.
		 * These can be accessed by getter methods (see Movie class in model package).
		 * e.g. movieName can be accessed by movie.getMovieName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/filmnest-db?autoReconnect=true&useSSL=false", "root", "Ethiopia00@");
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE Movie SET Name=?, Type=?, DistrFee=?, NumCopies=?, Rating=? WHERE ID=?")) {

                // Set values for the PreparedStatement
                preparedStatement.setString(1, movie.getMovieName());
                preparedStatement.setString(2, movie.getMovieType());
                preparedStatement.setInt(3, movie.getDistFee());
                preparedStatement.setInt(4, movie.getNumCopies());
                preparedStatement.setInt(5, movie.getRating());
                preparedStatement.setInt(6, movie.getMovieID());

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                	System.out.println("Success");
                	return "success";
                } else {
                	System.out.println(movie.getMovieID());
                	return "failure"; 
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return "Error";
        }
		/*Sample data ends*/

	}

	public String deleteMovie(String movieID) {
		/*
		 * movieID, which is the Movie's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		/*Sample data begins*/
		
		return "success";
		/*Sample data ends*/

	}
	
	
	public List<Movie> getBestsellerMovies() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of the bestseller movies has to be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		
		/*Sample data begins*/
		for (int i = 0; i < 5; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

	public List<Movie> getSummaryListing(String searchKeyword) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of summary listing of revenue generated by a particular movie or movie type must be implemented
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * Store the revenue generated by an movie in the soldPrice attribute, using setSoldPrice method of each "movie" object
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	
	

	public List<Movie> getMovieSuggestions(String customerID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie suggestions for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom the movie suggestions are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}
	
	public List<Movie> getCurrentMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch currently hold movie for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom currently hold movie are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	
public List<Movie> getQueueOfMovies(String customerID){
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch movie queue for a customer, indicated by customerID, must be implemented
		 * customerID, which is the Customer's ID for whom movie queue are fetched, is given as method parameter
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 */

		List<Movie> movies = new ArrayList<Movie>();
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
		
		
		
	}
	
	

//	public List getMoviesBySeller(String sellerID) {
//		
//		/*
//		 * The students code to fetch data from the database will be written here
//		 * Query to fetch movies sold by a given seller, indicated by sellerID, must be implemented
//		 * sellerID, which is the Sellers's ID who's movies are fetched, is given as method parameter
//		 * The bid and order details of each of the movies should also be fetched
//		 * The bid details must have the highest current bid for the movie
//		 * The order details must have the details about the order in which the movie is sold
//		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
//		 * Each bid record is required to be encapsulated as a "Bid" class object and added to the "bids" List
//		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
//		 * The movies, bids and orders Lists have to be added to the "output" List and returned
//		 */
//
//		List output = new ArrayList();
//		List<Movie> movies = new ArrayList<Movie>();
//		List<Bid> bids = new ArrayList<Bid>();
//		List<Order> orders = new ArrayList<Order>();
//		
//		/*Sample data begins*/
//		for (int i = 0; i < 4; i++) {
//			Movie movie = new Movie();
//			movie.setMovieID(123);
//			movie.setDescription("sample description");
//			movie.setType("BOOK");
//			movie.setName("Sample Book");
//			movies.add(movie);
//			
//			Bid bid = new Bid();
//			bid.setCustomerID("123-12-1234");
//			bid.setBidPrice(120);
//			bids.add(bid);
//			
//			Order order = new Order();
//			order.setMinimumBid(100);
//			order.setBidIncrement(10);
//			order.setOrderID(123);
//			orders.add(order);
//		}
//		/*Sample data ends*/
//		
//		output.add(movies);
//		output.add(bids);
//		output.add(orders);
//		
//		return output;
//	}

	public List<Movie> getMovieTypes() {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList
		 * A query to fetch the unique movie types has to be implemented
		 * Each movie type is to be added to the "movie" object using setType method
		 */
		
		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieType("Drama");
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;
	}

	public List getMoviesByName(String movieName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	
	public List getMoviesByActor(String actorName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieName, which is the movie's name on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieName in their name has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List getMoviesByType(String movieType) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The movieType, which is the movie's type on which the query has to be implemented, is given as method parameter
		 * Query to fetch movies containing movieType as their type has to be implemented
		 * Each movie's corresponding order data also has to be fetched
		 * Each movie record is required to be encapsulated as a "Movie" class object and added to the "movies" List
		 * Each order record is required to be encapsulated as a "Order" class object and added to the "orders" List
		 * The movies and orders Lists are to be added to the "output" List and returned
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	
	public List getMovieRentalsByName(String movieName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	
	public List getMovieRentalsByCustomer(String customerName) {
		
		

		List<Movie> movies = new ArrayList<Movie>();
		
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List getMovieRentalsByType(String movieType) {
		
	

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movies.add(movie);
			
		}
		/*Sample data ends*/
		

		
		return movies;
	}
	

	public List<Movie> getBestsellersForCustomer(String customerID) {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Each record is required to be encapsulated as a "Movie" class object and added to the "movies" ArrayList.
		 * Query to get the Best-seller list of movies for a particular customer, indicated by the customerID, has to be implemented
		 * The customerID, which is the customer's ID for whom the Best-seller movies have to be fetched, is given as method parameter
		 */

		List<Movie> movies = new ArrayList<Movie>();
				
		/*Sample data begins*/
		for (int i = 0; i < 6; i++) {
			Movie movie = new Movie();
			movie.setMovieID(1);
			movie.setMovieName("The Godfather");
			movie.setMovieType("Drama");
			movie.setDistFee(10000);
			movie.setNumCopies(3);
			movie.setRating(5);
			movies.add(movie);
		}
		/*Sample data ends*/
		
		return movies;

	}

}
