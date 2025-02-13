package surveyApp;

import java.util.*;
import java.util.stream.Collectors;

public class Survey {
    private String title;
    private String topic;
    private String description;
    private List<String> questions = new ArrayList<>();
    private Map<String, Map<String, Integer>> responses = new HashMap<>();//// Stores responses per question, mapping answers to their counts
    private static final List<String> ALTERNATIVES = Arrays.asList("Agree" , "Sligtly Agree", "Slightly Disagree", "Disagree");

    public Survey(String title, String topic, String description) {
        this.title = title;
        this.topic = topic;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean addQuestion(String question){
        if (!questions.contains(question) && questions.size() < 40) {
            questions.add(question);
            responses.put(question, new HashMap<>());
            return true;
        }
        return false;
    }

    public boolean removeQuestion(String question){
        if(questions.size()>10 && questions.remove(question)){
            responses.remove(question);
            return true;
        }
        return false;
    }

    public String mostGivenAnswer() {
        return responses.values().stream()
                .flatMap(map -> map.entrySet().stream())// Flatten all responses into a single stream of entries
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingInt(Map.Entry::getValue)))// Group by answer and sum occurrences
                .entrySet().stream()// Convert the grouped results into a stream
                .max(Map.Entry.comparingByValue())// Find the answer with the highest count
                .map(Map.Entry::getKey)// Extract the answer text
                .orElse("No answers yet");// Default if no answers are available
    }

    public void printResults() {
        for (String question : questions) {// Iterates through each question
            System.out.println("Question: " + question);
            responses.get(question).forEach((answer, count) ->
                    System.out.println(answer + ": " + count));
        }
    }

    public void removeLowResponseQuestions(int totalCandidates) {
        List<String> removedQuestions = new ArrayList<>();
        questions.removeIf(question -> {
            boolean toRemove = responses.getOrDefault(question, new HashMap<>()).values().stream().mapToInt(i -> i).sum() < totalCandidates / 2;
            if (toRemove) {
                removedQuestions.add(question);
            }
            return toRemove;
        });
        if (!removedQuestions.isEmpty()) {
            System.out.println("Questions removed due to low response rate: " + removedQuestions);
        }
    }

    public List<String> getQuestions() {
        return questions;
    }

    public Map<String, Map<String, Integer>> getResponses() {
        return responses;
    }

    public static List<String> getAlternatives() {
        return ALTERNATIVES;
    }
}
