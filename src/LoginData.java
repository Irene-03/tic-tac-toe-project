import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class LoginData {
    Scanner scanner = new Scanner(System.in);
    private final String cls = "\033[H\033[2J";
    private int indexUser = 0;
    private final ArrayList<String> gamersInfo = new ArrayList<>();
    private final Menu menuObject = new Menu();


    /**
     * check src folder to finde login data file and if doesn't have make a new one
     */
    public static void checkExistFile() {

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
     * this is menu that appear  before main menu  to set up user account
     * @throws IOException
     */
    public void startMenu() throws IOException {
        while (true) {
            System.out.println(cls);
            System.out.println("1-log in");
            System.out.println("2-sign up");
            System.out.println("3-log out");
            System.out.println("4-show my information table");
            System.out.println("5-exist");

            int startInput;
            startInput = scanner.nextInt();
            if (startInput == 1) {
                login();
            } else if (startInput == 2) {
                signUp();
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
        }
    }

    /**
     * sign up user
      * @throws IOException
     */
    public void signUp() throws IOException {

        ArrayList<String> gamerInfo = new ArrayList<>();
        String pass;
        String user;
        System.out.println(cls);
        File file = new File("src/loginData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.print("please enter your username:\t ");
        user = scanner.nextLine();
        System.out.print("\n pleas enter your password: \t");
        pass = scanner.nextLine();
        bufferedWriter.write(user + "\n" + pass + "\n" + "0\n0\n0\n0\n");
        System.out.println("cls");
        System.out.println("Your account has been successfully connected.");

        String check;
        while ((check = bufferedReader.readLine()) != null) {
            gamersInfo.add(check);

        }


    }

    /**
     * log in user and upload data in an arraylist
     * @throws IOException
     */
    public void login() throws IOException {

        String pass;
        String user;
        System.out.println(cls);
        File file = new File("src/loginData.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        System.out.print("please enter your username:\t ");
        user = scanner.nextLine();
        System.out.print("\n pleas enter your password: \t");
        pass = scanner.nextLine();
        if (checkExist(user, pass) == true) {
            System.out.println("Your account has been successfully connected.");
            String check;
            while ((check = bufferedReader.readLine()) != null) {
                gamersInfo.add(check);

            }
        } else {
            System.out.print("\n dont have any account with this user and password!!");
        }


    }

    /**
     * check that exist an account whit input username and password and save the index of local arraylist including that name
      * @param user input username from player
     * @param pass input password from player
     * @return true or false mean exist an account or not
     * @throws IOException
     */
    public boolean checkExist(String user, String pass) throws IOException {
        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String check;
        int counter = 0;


        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }
        for (int i = 0; i < gamerInfo.size(); i++) {
            if (user.equals(gamerInfo.get(i))) {
                counter++;
            } else if (pass.equals(gamerInfo.get(i)) && counter == 1) {
                indexUser = i - 1;
                return true;
            } else {
                counter = 0;
            }
        }
        return false;
    }


    /**
     * change users information after every game and save it
      * @param user player username
     * @param pass player password
     * @param num  number show that which argument ( win , fail , equal) must increase
     * @throws IOException
     */
    public static void changeGameInfo(String user, String pass, int num) throws IOException {
        ArrayList<String> gamerInfo = new ArrayList<>();
        File file = new File("src/loginData.txt");
        String check;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        StringBuffer inputBuffer = new StringBuffer();


        // copy text in file in arraylist
        while ((check = bufferedReader.readLine()) != null) {
            gamerInfo.add(check);

        }

        //check and change the arralist and overwrite in file
        int counter = 0;
        int size = 0;
        if (gamerInfo.size() != 0) {

            while (true) {

                if ((gamerInfo.get(size)).equals(user)) {
                    counter++;
                    size++;
                }
                if ((gamerInfo.get(size)).equals(pass) && counter == 1) {
                    counter++;
                    size++;

                } else {
                    counter = 0;
                    size++;
                }
                if (counter == 2) {
                    size += num;
                    String tempp = gamerInfo.get(size);
                    int temp = Integer.parseInt(tempp) + 1;
                    gamerInfo.remove(size);
                    gamerInfo.add(size, String.valueOf(temp));


                    size += (3 - num);
                    tempp = gamerInfo.get(size);
                    temp = Integer.parseInt(tempp) + 1;
                    gamerInfo.remove(size);
                    gamerInfo.add(size, String.valueOf(temp));


                    counter = 0;

                    break;
                }


            }
            Formatter formatter = new Formatter(file);
            for (int i = 0; i < gamerInfo.size(); i++) {
                String creat = gamerInfo.get(i);
                formatter.format("%s\n", creat);
            }
            formatter.close();


        }
    }



}
