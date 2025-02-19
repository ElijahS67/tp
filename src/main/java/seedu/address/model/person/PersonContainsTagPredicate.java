package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Person}'s {@code Tag} matches all the tags (case-insensitive) specified by Student.
 */
public class PersonContainsTagPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsTagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {

        if (keywords.isEmpty()) { //keywords = trimmed user input
            return false;
        }

        Set<String> personTagNameSet = person
                .getTags()
                .stream()
                .map(tag -> tag.tagName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        Set<String> lowerCaseKeywords = keywords //to be case-insensitive
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        return personTagNameSet.containsAll(lowerCaseKeywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsTagPredicate) other).keywords)); // state check
    }

}
