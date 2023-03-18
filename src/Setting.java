import java.io.*;
import java.util.Formatter;


public class Setting {
    private final String address = "src/new.txt";


    /**
     * a constructor that call readSetting function
      * @param array an array include information
     * @throws IOException
     */
    public Setting(int[] array) throws IOException {
        readSetting(array);
    }

    /**
     * read saved information : side size , block cell , won condition , from txt file and save in an array
      * @param info will include the information saved in txt file
     * @throws IOException
     */
    public void readSetting(int[] info) throws IOException {

        File file = new File(address);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int index = 0;


        for (int i = 0; i <3 ; i++) info[index++] = Integer.parseInt(bufferedReader.readLine());

    }

    /**
     * chang the information in txt file with new user choice
      * @param sideSize  new size of the board
     * @param blockCell new number of cell that user want to block
     * @param wonCondition new number of cell that need to win
     * @throws IOException
     */
    public void changeSetting(int sideSize, int blockCell, int wonCondition) throws IOException {

        Formatter formatter = new Formatter(address);
        formatter.format("%s\n%s\n%s", sideSize, blockCell, wonCondition);
        formatter.close();


    }

    /**
     * change back txt file  to default setting
      * @throws IOException
     */
    public void defaultSetting() throws IOException {

        Formatter formatter = new Formatter(address);
        formatter.format("%s\n%s\n%s",4,3,3 );
        formatter.close();
    }


}


