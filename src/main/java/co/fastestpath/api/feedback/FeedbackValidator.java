package co.fastestpath.api.feedback;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class FeedbackValidator {
    private static final List<String> ILLEGAL_BODY = new ArrayList<>(Arrays.asList("fuck you", "blah blah"));

    public static boolean IS_VALID_FEEDBACK(String email, String body) {
        boolean a = ILLEGAL_BODY.stream().filter(s -> s.equalsIgnoreCase(body)).findFirst().isPresent();
        boolean b = ILLEGAL_BODY.contains(body);
        return a && b;
    }
}
