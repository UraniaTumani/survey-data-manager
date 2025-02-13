package surveyApp;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SurveyManager manager = new SurveyManager();
        System.out.print("Enter survey title: ");
        String title = scanner.nextLine();
        System.out.print("Enter survey topic: ");
        String topic = scanner.nextLine();
        System.out.print("Enter survey description: ");
        String description = scanner.nextLine();

        Survey survey = new Survey(title, topic, description);
        manager.addSurvey(survey);

        do {
            System.out.println("Modify the survey (add/remove questions) before proceeding.");
            manager.modifySurvey(survey);
        } while (survey.getQuestions().size() < 10 || survey.getQuestions().size() > 40);

        System.out.print("Enter number of candidates: ");
        int numCandidates = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCandidates; i++) {
            System.out.print("Enter candidate first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter candidate last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter candidate email: ");
            String email = scanner.nextLine();
            System.out.print("Enter candidate phone number: ");
            String phone = scanner.nextLine();
            Candidate candidate = new Candidate(firstName, lastName, email, phone);
            manager.registerCandidate(candidate);
            candidate.takeSurvey(survey, scanner);
            candidate.printCandidateAnswers(survey);
        }

        survey.removeLowResponseQuestions(numCandidates);
        survey.printResults();
        System.out.println("Most given answer: " + survey.mostGivenAnswer());

        Candidate mostSurveyTaker = manager.findMostSurveyTaker();
        if (mostSurveyTaker != null) {
            System.out.println("Candidate who took the most surveys: " + mostSurveyTaker.getFullName());
        }

    }

}
