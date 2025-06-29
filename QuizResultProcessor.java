package com.gla;
import java.util.*;

class InvalidQuizException extends Exception {
    public InvalidQuizException(String message) {
        super(message);
    }
}

public class QuizResultProcessor {
    private String[] correctAnswers;
    private List<Integer> userScores;

    public QuizResultProcessor(String[] correctAnswers) {
        this.correctAnswers = correctAnswers;
        this.userScores = new ArrayList<>();
    }

    public int calculateScore(String[] userAnswers) throws InvalidQuizException {
        if (userAnswers.length != correctAnswers.length) {
            throw new InvalidQuizException("Answer length mismatch.");
        }
        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (correctAnswers[i].equalsIgnoreCase(userAnswers[i])) {
                score++;
            }
        }
        userScores.add(score);
        return score;
    }

    public String getGrade(int score) {
        int total = correctAnswers.length;
        double percent = (double) score / total * 100;
        if (percent >= 90) return "A";
        else if (percent >= 80) return "B";
        else if (percent >= 70) return "C";
        else if (percent >= 60) return "D";
        else return "F";
    }

    public List<Integer> getUserScores() {
        return userScores;
    }

    public static void main(String[] args) {
        String[] correct = {"A", "B", "C", "D", "A"};
        QuizResultProcessor processor = new QuizResultProcessor(correct);

        String[][] userSubmissions = {
            {"A", "B", "C", "D", "A"},
            {"A", "C", "C", "D", "B"},
            {"A", "B", "D", "D", "A"}
        };

        for (String[] userAnswers : userSubmissions) {
            try {
                int score = processor.calculateScore(userAnswers);
                String grade = processor.getGrade(score);
                System.out.println("Score: " + score + "/" + correct.length + ", Grade: " + grade);
            } catch (InvalidQuizException e) {
                System.out.println("Invalid submission: " + e.getMessage());
            }
        }

        System.out.println("All user scores: " + processor.getUserScores());
    }
}
