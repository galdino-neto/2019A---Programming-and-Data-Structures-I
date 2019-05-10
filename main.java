
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class main {

    public static void main(String[] args) {
        String arquivoCSV = "game-reviews.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        Map<String, Statistics> map = new HashMap<String, Statistics>();
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));
            linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] dados_jogo = linha.split(csvDivisor);
                Statistics e = map.get(dados_jogo[4]);
                if (e != null) {
//                  System.out.println("");
//                  System.out.println("TITULO: "+dados_jogo[0]);
//                  System.out.println("PLATAFORMA: "+dados_jogo[1]);
//                  System.out.println("SCORE FRASE: "+dados_jogo[2]);
//                  System.out.println("SCORE: "+dados_jogo[3]);
//                  System.out.println("GENRE: "+dados_jogo[4]);
//                  System.out.println("EDITORS CHOICE: "+dados_jogo[5]);
//                  System.out.println("RELEASE: "+dados_jogo[6]);
                    e.addReviews();
                    e.updateBestGame(dados_jogo[0], Double.parseDouble(dados_jogo[3]));
                    e.updateWorstGame(dados_jogo[0], Double.parseDouble(dados_jogo[3]));
                    e.addScore(Double.parseDouble(dados_jogo[3]));
                    e.updateAmazing(dados_jogo[2]);
                    e.addNintendoCounter(dados_jogo[1]);
                    map.put(dados_jogo[4], e);
                } else {
                    boolean isAmazing = false;
                    if (dados_jogo[2].toLowerCase().contains("amazing")) {
                        isAmazing = true;
                    }
                    e = new Statistics(Double.parseDouble(dados_jogo[3]), isAmazing, dados_jogo[0], dados_jogo[0], dados_jogo[4], dados_jogo[1]);
                    map.put(dados_jogo[4], e);
                }
            }
            
            System.out.println(map);

            String mostCommonNintendoGenre = "";
            if (map != null) {
                int value = 0;
                Set<String> keys = map.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                    String key = i.next();
                    if (key != null) {
                        if (map.get(key).getNintendoCounter() > value) {
                            mostCommonNintendoGenre = map.get(key).getGenre();
                            value = map.get(key).getNintendoCounter();
                        }
                    }
                }
            }
            System.out.println("Most Common Nintendo Genre: " + mostCommonNintendoGenre);

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
