package pawtropolis.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.strategy.ActionEnum;
import pawtropolis.model.strategy.Command;
import pawtropolis.model.strategy.StrategyMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
@Component
public class InputManager {
    private InputStreamReader inputReader;
    private BufferedReader bufferedReader;

    private static String WORDS_SEPARATOR = " ";

    @Autowired
    public InputManager() {
        inputReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputReader);
    }

    public String inputString() {
        String input = "";
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            log.error("error in the reading of the input");
        }
        return input;
    }

    public String chooseFromOptions(String message, List<String> options) {
        log.info(message);
        options.forEach(log::info);
        String choice = "";
        while (!options.contains(choice)) {
            choice = inputString();
        }
        return choice;
    }

    public List<Command> inputPhraseAsCommands() {
        String wholeCommand = inputString();
        return cropPhraseIntoCommands(wholeCommand.toLowerCase(), Arrays.asList(ActionEnum.values()));
    }

    private List<Command> cropPhraseIntoCommands(String phrase, List<ActionEnum> actionEnums) {
        List<String> wordsInPhrase = new ArrayList<>(Arrays.asList(phrase.split(WORDS_SEPARATOR)));
        List<String> modifiableList = new ArrayList<>(Arrays.asList(phrase.split(WORDS_SEPARATOR)));
        List<Command> presentCommands = new ArrayList<>();

        for (String word : wordsInPhrase.stream().filter(word -> !ActionEnum.UNKNOWN.equals(ActionEnum.fromValue(word))).toList()) {
            ActionEnum action = ActionEnum.fromValue(word);
            List<String> parameterPerAction = new ArrayList<>();
            int actionPosition = modifiableList.indexOf(word);
            for (int i = 0; i <= StrategyMapping.NUMBER_OF_PARAMETER_PER_STRATEGY.get(action); i++) {
                String possibleParameter = modifiableList.get(actionPosition + i);
                List<String> plausibleParameters = StrategyMapping.POSSIBLE_PARAMETERS_PER_STRATEGY.get(action);
                if (!ObjectUtils.isEmpty(plausibleParameters) && plausibleParameters.contains(possibleParameter)) {
                    parameterPerAction.add(possibleParameter);
                }
                modifiableList.remove(plausibleParameters);
            }
            Command command = new Command(action, parameterPerAction);
            presentCommands.add(command);
            modifiableList.remove(word);
        }
        return presentCommands;
    }
}
