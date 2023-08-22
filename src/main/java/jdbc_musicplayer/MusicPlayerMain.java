package jdbc_musicplayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MusicPlayerMain {
	private static Scanner scanner =new Scanner(System.in);
	private static int choice;
	static boolean loop = true;
	static MusicPlayerCRUD crud = new MusicPlayerCRUD();
	static MusicPlayer musicPlayer = new MusicPlayer();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		while (loop) {
			musicPlayerMenu();
		}
	}
	
	
	public static void musicPlayerMenu() throws ClassNotFoundException, SQLException, IOException {
		System.out.println("=======Menu=======");
		System.out.println("1. Play Songs \n"+ "2. Add/Remove songs \n" + "3. Edit Song \n" + "4. Exit \n");
		choice=scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("1. Play All Songs \n"+ "2. Play Random songs \n" + "3. Choose to play \n" + "4. Go Back \n");
			choice=scanner.nextInt();
			switch (choice) {
			case 1:
				crud.playAllSong();
				break;
			case 2:
				crud.playRandomSong();
				break;
			case 3:
				crud.chooseSong(musicPlayer);
				break;
			case 4:
				musicPlayerMenu();
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			break;
		
		
		
		
		
		case 2:
			System.out.println("1. Add Song \n"+ "2. Remove Song \n" + "3. Go Back \n");
			choice=scanner.nextInt();
			switch (choice) {
			case 1:
				crud.insertSong(musicPlayer);
				break;
			case 2:
				System.out.println("enter the id");
				musicPlayer.setId(scanner.nextInt());
				
				crud.removeSong(musicPlayer);
				break;
			case 3:
				musicPlayerMenu();
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			break;
			
		case 3:
			System.out.println("1. Select Song to edit \n" + "2. Go Back \n");
			choice=scanner.nextInt();
			switch (choice) {
			case 1:
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
				
				
				
				crud.editSong(musicPlayer);
				break;
			case 2:
				musicPlayerMenu();
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			break;
		case 4:
			System.out.println("Thank You...!!");
			loop = false;
			break;

		default:
			System.out.println("invalid input");
			break;
		}
	}
}	