package jdbc_musicplayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class MusicPlayerCRUD {
	
	Scanner scanner = new Scanner(System.in);
	MusicPlayer musicPlayer = new MusicPlayer();
	
	//method getconnection
public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			Class.forName(properties.getProperty("className"));
			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
			return connection;
		}

public void viewAllSong() throws ClassNotFoundException, IOException, SQLException {
	Connection connection = getConnection(); //calling a method
	PreparedStatement preparedStatement = connection.prepareStatement(
			"SELECT * FROM MUSICPLAYER ");//? MEANS PLACE HOLDER
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while (resultSet.next()) {
		System.out.println(resultSet.getInt("id") +" " + resultSet.getString("songname"));
	}
	
	connection.close();
}

public void playAllSong() {
	
}

public void playRandomSong() throws ClassNotFoundException, IOException, SQLException {
	Connection connection = getConnection(); //calling a method
	PreparedStatement preparedStatement = connection.prepareStatement(
			"SELECT * FROM MUSICPLAYER ORDER BY RAND() LIMIT 1");//? MEANS PLACE HOLDER
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while (resultSet.next()) {
		System.out.println("Random song is playing");
		System.out.println("Song name : " + resultSet.getString("songname"));
	}
	
	connection.close();
}

public void chooseSong(MusicPlayer musicPlayer) throws ClassNotFoundException, IOException, SQLException {
	viewAllSong();
	
	System.out.println("\nenter the song name");
	musicPlayer.setSongname(scanner.next());
	
	Connection connection = getConnection(); //calling a method
	PreparedStatement preparedStatement = connection.prepareStatement(
			"SELECT * FROM MUSICPLAYER WHERE SONGNAME=?");//? MEANS PLACE HOLDER
	preparedStatement.setString(1, musicPlayer.getSongname());
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while (resultSet.next()) {
		System.out.println("Song name : " + resultSet.getString("songname") + "is playing now \n");
	}
	
	connection.close();
}

public void insertSong(MusicPlayer musicPlayer) throws SQLException, ClassNotFoundException, IOException {
	System.out.println("How many songs you want to be add");
	int a = scanner.nextInt();
	
	for (int i = 0; i < a; i++) {
		System.out.println("enter the id");
		musicPlayer.setId(scanner.nextInt());
		
		System.out.println("enter the song name");
		musicPlayer.setSongname(scanner.next());
		
		System.out.println("enter the Singer name");
		musicPlayer.setSinger(scanner.next());
		
		System.out.println("enter the Album");
		musicPlayer.setAlbum(scanner.next());
		
		System.out.println("enter the Duration");
		musicPlayer.setDuration(scanner.next());
		
		Connection connection = getConnection(); //calling a method
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO MUSICPLAYER (ID, SONGNAME, SINGER, ALBUM, DURATION) VALUES(?,?,?,?,?)");//? MEANS PLACE HOLDER
		preparedStatement.setInt(1, musicPlayer.getId());
		preparedStatement.setString(2, musicPlayer.getSongname());
		preparedStatement.setString(3, musicPlayer.getSinger());
		preparedStatement.setString(4, musicPlayer.getAlbum());
		preparedStatement.setString(5, musicPlayer.getDuration());
		
		int count=preparedStatement.executeUpdate();
		if (count==1) {
			System.out.println("Inserted succesfully");
		} else {
			System.out.println(" Inserted unsuccesful");
		}
		
		connection.close();
	}
}

public void removeSong(MusicPlayer musicPlayer) throws SQLException, ClassNotFoundException, IOException {
	Connection connection = getConnection();
	PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MUSICPLAYER WHERE ID=?");//? MEANS PLACE HOLDER
	preparedStatement.setInt(1, musicPlayer.getId());
	
	int count=preparedStatement.executeUpdate();
	if (count==1) {
		System.out.println("Deleted");
	} else {
		System.out.println(" not Deleted");
	}
	
	connection.close();
}


public void editSong(MusicPlayer musicPlayer) throws ClassNotFoundException, IOException, SQLException {
	Connection connection = getConnection();
	PreparedStatement preparedStatement = connection.prepareStatement("UPDATE MUSICPLAYER SET SONGNAME=?, SINGER=?, ALBUM=?,DURATION=? WHERE ID=?");//? MEANS PLACE HOLDER
	preparedStatement.setString(1, musicPlayer.getSongname());
	preparedStatement.setString(2, musicPlayer.getSinger());
	preparedStatement.setString(3, musicPlayer.getAlbum());
	preparedStatement.setString(4, musicPlayer.getDuration());
	preparedStatement.setInt(5, musicPlayer.getId());
	
	int count=preparedStatement.executeUpdate();
	if (count==1) {
		System.out.println("Updated");
	} else {
		System.out.println(" not updated");
	}
	
	connection.close();
}




}
