package synergybot;

import java.util.HashMap;
import java.util.Map;

public class SynergyBot {
    private Map<String, String> responses;

    public SynergyBot() {
        responses = new HashMap<>();
        loadResponses();
    }

    private void loadResponses() {
        responses.put("hi", "Hey there! I'm SynergyBot 🤖");
        responses.put("hello", "Hello! How can I assist you today?");
        responses.put("how are you", "I'm always running at 100%! 😊");
        responses.put("name", "I'm SynergyBot, your group's virtual buddy.");
        responses.put("bye", "Goodbye! Stay connected. 👋");
        responses.put("help", "You can ask things like: add 5.5 and 3.2, sin 30, sqrt 49, toggle dark mode.");
    }

    public String getResponse(String userInput) {
        String input = userInput.toLowerCase().trim();
        String mathResult = handleMath(input);
        if (mathResult != null) return mathResult;

        for (String key : responses.keySet()) {
            if (input.contains(key)) return responses.get(key);
        }

        return "Hmm... I didn't quite get that. Try math like '2 + 3' or 'sin 45' or just say 'hi'.";
    }

    private String handleMath(String input) {
        try {
            input = input.replace("what is", "").replace("calculate", "").trim();

            if (input.matches(".*(\\+|add).*")) {
                String[] nums = input.split("(\\+|add|and)");
                double a = Double.parseDouble(nums[0].trim());
                double b = Double.parseDouble(nums[1].trim());
                return "Sum is: " + (a + b);
            }

            if (input.matches(".*(\\-|minus|subtract).*")) {
                String[] nums = input.split("(\\-|minus|subtract)");
                double a = Double.parseDouble(nums[0].trim());
                double b = Double.parseDouble(nums[1].trim());
                return "Difference is: " + (a - b);
            }

            if (input.matches(".*(\\*|multiply|x).*")) {
                String[] nums = input.split("(\\*|multiply|x)");
                double a = Double.parseDouble(nums[0].trim());
                double b = Double.parseDouble(nums[1].trim());
                return "Product is: " + (a * b);
            }

            if (input.matches(".*(\\/|divide).*")) {
                String[] nums = input.split("(\\/|divide|by)");
                double a = Double.parseDouble(nums[0].trim());
                double b = Double.parseDouble(nums[1].trim());
                if (b == 0) return "Can't divide by zero!";
                return "Quotient is: " + (a / b);
            }

            if (input.contains("sqrt")) {
                double x = Double.parseDouble(input.replace("sqrt", "").trim());
                return "Square root is: " + Math.sqrt(x);
            }

            if (input.contains("log")) {
                double x = Double.parseDouble(input.replace("log", "").trim());
                return "Logarithm (base 10) is: " + Math.log10(x);
            }

            if (input.contains("sin")) {
                double x = Double.parseDouble(input.replace("sin", "").trim());
                return "sin(" + x + ") = " + Math.sin(Math.toRadians(x));
            }

            if (input.contains("cos")) {
                double x = Double.parseDouble(input.replace("cos", "").trim());
                return "cos(" + x + ") = " + Math.cos(Math.toRadians(x));
            }

            if (input.contains("tan")) {
                double x = Double.parseDouble(input.replace("tan", "").trim());
                return "tan(" + x + ") = " + Math.tan(Math.toRadians(x));
            }

            if (input.contains("^")) {
                String[] parts = input.split("\\^");
                double base = Double.parseDouble(parts[0].trim());
                double exponent = Double.parseDouble(parts[1].trim());
                return base + " raised to the power " + exponent + " is " + Math.pow(base, exponent);
            }
        } catch (Exception e) {
            return "Error in calculation. Please check your input.";
        }
        return null;
    }
}
