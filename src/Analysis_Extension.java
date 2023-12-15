import java.util.ArrayList;

class StartsWith extends Word_Analysis {

    protected String substring;

    public StartsWith(String substring, boolean most_common, boolean length_analysis) {
        super(most_common, length_analysis);
        this.substring = substring;
    }

    public boolean isValid(String word) {return word.startsWith(substring);}

    public String toString() {
        return "StartsWith - " + substring;
    }
}

class EndsWith extends Word_Analysis {

    protected String substring;

    public EndsWith(String substring, boolean most_common, boolean length_analysis) {
        super(most_common, length_analysis);
        this.substring = substring;
    }

    public boolean isValid(String word) {return word.endsWith(substring);}

    public String toString() {
        return "EndsWith - " + substring;
    }
}

class Hybrid extends Word_Analysis {

    private ArrayList<Word_Analysis>  hybridCommands;

    public Hybrid(boolean most_common, boolean length_analysis, ArrayList<Word_Analysis> hybridCommands) {
        super(most_common, length_analysis);

        for(int i=0; i<hybridCommands.size(); i++) {
            if(hybridCommands.get(i).getClass()==Hybrid.class) {
                throw new IllegalArgumentException("Error: An Hybrid command list cannot contain an analysis command of Hybrid type");
            }
            if(hybridCommands.get(i).getClass()==StartsWith.class&&i!=0) {
                throw new IllegalArgumentException("Error: StartsWith analysis command can only be placed at the beginning" +
                        "of an Hybrid command list");
            }
            if(hybridCommands.get(i).getClass()==EndsWith.class&&i!=hybridCommands.size()-1) {
                throw new IllegalArgumentException("Error: EndsWith analysis command can only be placed at the end" +
                        "of an Hybrid command list");
            }
        }
        this.hybridCommands = hybridCommands;
    }

    public boolean isValid(String word) {

        for(Word_Analysis analysis: hybridCommands) {
            if (!analysis.isValid(word)) {return false;}
        }

        return true;
    }

    public String toString() {
        String returnString = "Hybrid";

        for(Word_Analysis analysis: hybridCommands) {
            returnString = returnString + " - ("+analysis.toString()+")";
        }

        return returnString;
    }
}
