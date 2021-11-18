package pl.isa.question;

import pl.isa.util.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionService extends Service {

    public List<Question> specifyQuestionsAccordingToCategoryAndType(QuestionCategory category, QuestionType type, List<Question> questions) {
        Iterator<Question> iterator = questions.iterator();
        while (iterator.hasNext()) {
            Question question = iterator.next();
            if (!(question.getQuestionType().equals(type)) || !(question.getQuestionCategory().equals(category))) {
                iterator.remove();
            }
        }
        return questions;
    }

    public List<Question.Answer> getAnswerListFromQuestion(Question question){
        List<Question.Answer> answers = new ArrayList<>();
        for (String answer : question.getQuestionAndAnswers().keySet()) {
            Iterator<Question.Answer> iterator =  question.getQuestionAndAnswers().get(answer).iterator();
            while (iterator.hasNext()){
                Question.Answer thisAnswer = iterator.next();
                answers.add(thisAnswer);
            }
        }
        return answers;
    }

    public List<Character> getCorrectAnswerLettersList(List<Question.Answer> answers){
        List<Character> correctAnswerLetters = new ArrayList<>();
        for (Question.Answer answer : answers) {
            if (answer.isCorrect()){
                correctAnswerLetters.add(answer.getLetter());
            }
        }
        return correctAnswerLetters;
    }

    public List<Character> getAllAnswerLettersList(List<Question.Answer> answers){
        List<Character> allAnswerLetters = new ArrayList<>();
        for (Question.Answer answer : answers) {
            allAnswerLetters.add(answer.getLetter());
        }
        return allAnswerLetters;
    }

}
