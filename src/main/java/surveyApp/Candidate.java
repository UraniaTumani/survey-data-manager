package surveyApp;

import java.util.*;

public class Candidate {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    Map<Survey, List<String>> surveyResponses = new HashMap<>();

    public Candidate(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void takeSurvey(Survey survey, Scanner scanner){
        List<String> answers = new ArrayList<>();
        for (String question : survey.getQuestions()){
            System.out.println(question + "(Agree/Slightly Agree/Slightly Disagree/Disagree or skip): ");
           String answer = scanner.nextLine().trim();
           answers.add(answer);
           if (Survey.getAlternatives().contains(answer)){
               survey.getResponses().get(question).merge(answer, 1,Integer::sum);
           }

        }
        surveyResponses.put(survey,answers);
    }

    public List<String> getAnswersForSurvey(Survey survey){
        return surveyResponses.getOrDefault(survey, Collections.emptyList());
    }
    public void printCandidateAnswers(Survey survey){
        System.out.println("Answers by " + firstName + " " + lastName + ":");
        List<String> answers = getAnswersForSurvey(survey);
        List<String> questions = survey.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i) + " - " + (i < answers.size() ? answers.get(i) : "No answer"));
        }
    }

    public String getFullName(){
        return  firstName + lastName;
    }
}
