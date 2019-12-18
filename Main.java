import org.javacord.api.*;

public class Main{
    public static void main(String[] args) {
        String token = "nOnE oF yOuR bEesWAx";

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.setMessageCacheSize(10, 60 * 60);

        api.addListener(new BotListener());

        String YodaTextPoint = getYodaStringPoint('.');
        String YodaTextComma = getYodaStringPoint(',');       
    }

    public String getYodaStringPoint(char charSplit){
    	String csvFile = "yoda.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = charSplit;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
