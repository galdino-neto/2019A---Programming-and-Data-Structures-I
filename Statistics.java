
import java.util.ArrayList;

public class Statistics {

    private int num_reviews;
    private int num_amazing;
    private ArrayList<Double> scores = new ArrayList<>();
    private final ArrayList<String> nintendoPlataforms = new ArrayList<>();
    private String bestGame;
    private String worstGame;
    private int nintendoCounter;
    private final String genre;

    public Statistics() {
        this.num_reviews = 0;
        this.num_amazing = 0;
        this.scores = null;
        this.bestGame = "";
        this.worstGame = "";
        this.nintendoCounter = 0;
        this.genre = "";
        this.setNintendoPlataforms();
    }

    public Statistics(double score, boolean isAmazing, String bestGame, String worstGame, String genre, String plataform) {
        this.num_reviews = 1;
        if (isAmazing) {
            this.num_amazing = 1;
        } else {
            this.num_amazing = 0;
        }
        this.scores.add(score);
        this.bestGame = bestGame;
        this.worstGame = worstGame;
        this.nintendoCounter = 1;
        this.genre = genre;
        this.setNintendoPlataforms();
    }

    public void addNintendoCounter(String plataform) {
        for (int i = 0; i < nintendoPlataforms.size(); i++) {
            if (plataform.toLowerCase().trim().contains(nintendoPlataforms.get(i))) {
                this.nintendoCounter++;
            }
        }
    }

    public void addReviews() {
        this.num_reviews++;
    }

    public void addScore(double score) {
        this.scores.add(score);
    }

    public String getBestGame() {
        return bestGame;
    }

    public String getGenre() {
        return genre;
    }

    public int getNintendoCounter() {
        return nintendoCounter;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public int getNum_amazing() {
        return num_amazing;
    }

    public double getPercentAmazing() {
        return ((this.getNum_amazing() * 100.00) / this.getNum_reviews());
    }

    public ArrayList<Double> getScores() {
        return scores;
    }

    public String getWorstGame() {
        return worstGame;
    }

    public double populationDeviation() {
        double value = 0;
        double average = scoreAverage();
        double deviations[] = new double[this.scores.size()];
        double sum = 0;
        for (int i = 0; i < deviations.length; i++) {
            deviations[i] = Math.pow(this.scores.get(i) - average, 2);
            sum += deviations[i];
        }
        return Math.sqrt((sum / deviations.length + 1));
    }

    public void setNintendoPlataforms() {
        this.nintendoPlataforms.add("nintendo");
        this.nintendoPlataforms.add("game boy");
        this.nintendoPlataforms.add("gba");
        this.nintendoPlataforms.add("gamecube");
        this.nintendoPlataforms.add("wii");
    }

    public double scoreAverage() {
        double sum = this.scores.get(0);
        for (int i = 1; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        return sum / scores.size();
    }

    public void updateAmazing(String score_phrase) {
        if (score_phrase.toLowerCase().contains("amazing")) {
            this.num_amazing++;
        }
    }

    public void updateBestGame(String gameTitle, double score) {
        double highestScore = this.scores.get(0);
        for (Double s : scores) {
            if (s > highestScore) {
                highestScore = s;
            }
        }
        if (highestScore < score) {
            this.bestGame = gameTitle;
        }
    }

    public void updateWorstGame(String gameTitle, double score) {
        double lowestScore = this.scores.get(0);
        for (Double s : scores) {
            if (s < lowestScore) {
                lowestScore = s;
            }
        }
        if (lowestScore > score) {
            this.worstGame = gameTitle;
        }
    }

    @Override
    public String toString() {
        return "\n----------------------------------------------"
                + "\nNum. Reviews = " + this.getNum_reviews()
                + "\n% Amazing = " + String.format("%.2f", this.getPercentAmazing()) + "%"
                + "\nPopulation Deviation = " + String.format("%.2f", this.populationDeviation())
                + "\nBest Game = " + this.getBestGame()
                + "\nWorst Game = " + this.getWorstGame()
                + "\nScore Average = " + String.format("%.2f", this.scoreAverage())
                + "\n----------------------------------------------\n";
    }

}
