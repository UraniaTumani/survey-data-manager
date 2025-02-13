package surveyApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SurveyManager {

        private List<Survey> surveys = new ArrayList<>();
        private List<Candidate> candidates = new ArrayList<>();
        private static final Scanner scanner = new Scanner(System.in);


        public void addSurvey(Survey survey) {
            surveys.add(survey);
        }

        public void registerCandidate(Candidate candidate) {
            candidates.add(candidate);
        }

        public Candidate findMostSurveyTaker() {
            return candidates.stream()
                    .max(Comparator.comparingInt(c -> c.surveyResponses.size()))
                    .orElse(null);
        }

    public void modifySurvey(Survey survey) {
        while (true) {
            System.out.println("1. Add a question\n2. Remove a question\n3. Finish modifications\nEnter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter new question: ");
                String newQuestion = scanner.nextLine();
                if (!survey.addQuestion(newQuestion)) {
                    System.out.println("Failed to add question.");
                }
            } else if (choice == 2) {
                System.out.print("Enter question to remove: ");
                String removeQuestion = scanner.nextLine();
                if (!survey.removeQuestion(removeQuestion)) {
                    System.out.println("Failed to remove question.");
                }
            } else if (choice == 3) {
                break;
            }
        }
    }}
