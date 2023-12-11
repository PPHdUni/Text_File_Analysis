class StartsWith extends Word_Analysis {

    public StartsWith(String substring, boolean most_common, boolean length_analysis) {
        super(substring, most_common, length_analysis);
    }

    public boolean isValid(String word) {return word.startsWith(substring);}
}

class EndsWith extends Word_Analysis {

    public EndsWith(String substring, boolean most_common, boolean length_analysis) {
        super(substring, most_common, length_analysis);
    }

    public boolean isValid(String word) {return word.endsWith(substring);}
}
