import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class LoginData {
    Scanner scanner = new Scanner(System.in);
    private final String cls = "\033[H\033[2J";
    private final ArrayList<String> gamersInfo = new ArrayList<>();
    //    private final Menu menuObject = new Menu();
    private String playerUserOne;
    private String playerUserTwo;
    private String playerPassOne;
    private String playerPassTwo;


    /**
     * a constructor that call  'heckExistFile'  function
     */
    public LoginData() {
        System.out.println(cls);
        checkExistFile();
    }

    public void loginLevel(int menuInput) throws IOException {
        File file = new File("src/loginData.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        if (menuInput == 1) {
            startMenu(1);
        } else if (menuInput == 2) {
            System.out.println("player one :");
            startMenu(1);
            System.out.println("cls");
            System.out.println("player two :");
            startMenu(2);
        }
        String check;
        while ((check = bufferedReader.readLine()) != null) {
            gamersInfo.add(check);

        }
    }

    /**
     * check src folder to find login data file and if it doesn't have make a new one
     */
    private static void checkExistFile() {

        File file;

        try {
            file = new File("src/loginData.txt");
            //check exist
            if (file.exists()) ;
            else {
                file.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("\n");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * this is menu that appear  after main menu  to set up users account
     *
     * @throws IOException for Buffered class
     */
    private void startMenu(int playerNum) throws IOException {
        while (true) {
//            System.out.println(cls);
            System.out.println("1-log in");
            System.out.println("2-sign up");
            System.out.println("3-log out");
            System.out.println("4-show my information table");
            System.out.println("5-go ahead");

            int startInput;
            startInput = scanner.nextInt();
            if (startInput == 1) {
                login(playerNum);
            } else if (startInput == 2) {
                signUp(playerNum);
            } else if (startInput == 3) {
                //----------------------
            } else if (startInput == 4) {
                if (gamersInfo.size() != 0) {
//                    showInfoTable();
                } else {
                    System.out.println(cls);
                    System.out.println("pleas first log in or sign up!");
                }

            } else if (startInput == 5) {
                break;
            } else {
                System.out.println("Invalid input , enter again!! ");
            }
            System.out.println(cls);
        }
    }

    /**
     * sign up user
     *
     * @throws IOException buffered class
     */
    private void signUp(int playerNum) throws IOException {

        System.out.println(cls);
        File file = new File("src/loginData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        Formatter formatter = new Formatter(file);


        if (playerNum == 1) {
            System.out.print("please enter your username:\t ");
            playerUserOne = scanner.nextLine();
            scanner.next();
            System.out.print("\npleas enter your password: \t");
            playerPassOne = scanner.nextLine();
            scanner.next();
            formatter.format("%s\n%s\n0\n0\n0\n0\n",playerUserOne,playerPassOne);
//            bufferedWriter.write(playerUserOne + "\n" + playerPassOne + "\n" + "0\n0\n0\n0\n");
            System.out.println("cls");
            System.out.println("Your account has been successfully connected.");

        } else if (playerNum == 2) {
            System.out.print("please enter your username:\t ");
            playerUserTwo = scanner.nextLine();
            scanner.next();
            System.out.print("\npleas enter your password: \t");
            playerPassTwo = scanner.nextLine();
            scanner.next();
            bufferedWriter.write(playerUserTwo + "\n" + playerPassTwo + "\n" + "0\n0\n0\n0\n");
            System.out.println(cls);
            System.out.println("Your account has been successfully connected.");
        }


    }

    /**
     * log in user and upload data in an arraylist
     *
     * @throws IOException buffered class
     */
    private void login(int playerNum) throws IOException {

        String pass;
        String user;
        System.out.println(cls);


        System.out.print("please enter your username:\t ");
        user = scanner.nextLine();
        scanner.next();

        System.out.print("\npleas enter your password: \t");
        pass = scanner.nextLine();
        scanner.next();

        if (checkExist(user, pass)) {
            if (playerNum == 1) {
                System.out.println("Your account has been successfully connected.");
                playerUserOne = user;
                playerPassOne = pass;
            } else if (playerNum == 2) {
                System.out.println("Your account has been successfully connected.");
                playerUserTwo = user;
                playerPassTwo = pass;
            }


        } else {
            System.out.print("\n dont have any account with this user and password!!");
        }


    }

    /**
     * check that exist an account whit input username and password and save the index of local arraylist including that name
     *
     * @param user input username from player
     * @param pass input password from player
     * @return true or false mean exist an account or not
     * @throws IOException buffered class
     */
    private boolean checkExist(String user, String pass) throws IOException {
        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String check;
        int counter = 0;


        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }
        for (String s : gamerInfo) {
            if (user.equals(s)) {
                counter++;
            } else if (pass.equals(s) && counter == 1) {
                return true;
            } else {
                counter = 0;
            }
        }
        return false;
    }


    /**
     * change users information after every game and save it
     *
     * @param playerNum to show which player information need to change
     * @param num       number show that which argument ( win , fail , equal) must increase
     * @throws IOException buffered class
     */
    public void changeGameInfo(int playerNum, int num) throws IOException {

        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        String check;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//        FileWriter fileWriter = new FileWriter(file, true);
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
//        StringBuffer inputBuffer = new StringBuffer();


        // copy text in file in arraylist
        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }

        //check and change the arraylist and overwrite in file
        int counter = 0;
        int size = 0;
        if (gamerInfo.size() != 0) {

            while (true) {
                if (playerNum == 1) {
                    if ((gamerInfo.get(size)).equals(playerUserOne)) {
                        counter++;
                        size++;
                    }
                    if ((gamerInfo.get(size)).equals(playerPassOne) && counter == 1) {
                        counter++;
                        size++;

                    } else {
                        counter = 0;
                        size++;
                    }
                    if (counter == 2) {
                        size += num;
                        String newTemp = gamerInfo.get(size);
                        int temp = Integer.parseInt(newTemp) + 1;
                        gamerInfo.remove(size);
                        gamerInfo.add(size, String.valueOf(temp));


                        size += (3 - num);
                        newTemp = gamerInfo.get(size);
                        temp = Integer.parseInt(newTemp) + 1;
                        gamerInfo.remove(size);
                        gamerInfo.add(size, String.valueOf(temp));


                        break;
                    }
                } else if (playerNum == 2) {
                    if ((gamerInfo.get(size)).equals(playerUserTwo)) {
                        counter++;
                        size++;
                    }
                    if ((gamerInfo.get(size)).equals(playerPassTwo) && counter == 1) {
                        counter++;
                        size++;

                    } else {
                        counter = 0;
                        size++;
                    }
                    if (counter == 2) {
                        size += num;
                        String newTemp = gamerInfo.get(size);
                        int temp = Integer.parseInt(newTemp) + 1;
                        gamerInfo.remove(size);
                        gamerInfo.add(size, String.valueOf(temp));


                        size += (3 - num);
                        newTemp = gamerInfo.get(size);
                        temp = Integer.parseInt(newTemp) + 1;
                        gamerInfo.remove(size);
                        gamerInfo.add(size, String.valueOf(temp));


                        break;
                    }
                }


            }
            Formatter formatter = new Formatter(file);
            for (String creat : gamerInfo) {
                formatter.format("%s\n", creat);
            }
            formatter.close();


        }
    }


}

