package net.abundantmc.abundantskyblock.audience;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentService {
    public static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
            .tags(
                    TagResolver.builder()
                            .resolver(
                                    TagResolver.resolver("blank", Tag.styling(
                                            NamedTextColor.WHITE,
                                            TextDecoration.ITALIC.withState(false),
                                            TextDecoration.BOLD.withState(false),
                                            TextDecoration.UNDERLINED.withState(false),
                                            TextDecoration.STRIKETHROUGH.withState(false),
                                            TextDecoration.OBFUSCATED.withState(false)
                                    ))
                            )
                            .resolver(
                                    TagResolver.resolver("dot", Tag.inserting(Component.text("•")
                                            .color(NamedTextColor.DARK_GRAY)))
                            )
                            .resolver(TagResolver.standard())
                            .build()
            )
            .build();
    private static final int[] VALUES = {1000,900,500,400,100,90,50,40,10,9,5,4,1};

    /**
     * Gets a component from a list of strings
     * @param messages Strings
     * @return Component
     */
    public Component getComponent(String... messages) {
        if (messages == null || messages.length < 1) return null;
        if (messages.length == 1) return MINI_MESSAGE.deserialize(messages[0]);
        StringBuilder builder = new StringBuilder().append(messages[0]);
        Arrays.stream(Arrays.copyOfRange(messages, 1, messages.length)).forEach(s -> builder.append("\n").append(s));
        return MINI_MESSAGE.deserialize(builder.toString());
    }

    /**
     * Gets a blank component - White, no bold, italics, etc
     * @param messages
     * @return Component
     */
    public Component getBlankComponent(String... messages) {
        if (messages == null || messages.length < 1) return null;
        if (messages.length == 1) return MINI_MESSAGE.deserialize("<blank>" + messages[0]);
        StringBuilder builder = new StringBuilder().append("<blank>" + messages[0]);
        Arrays.stream(Arrays.copyOfRange(messages, 1, messages.length)).forEach(s -> builder.append("\n<blank>").append(s));
        return MINI_MESSAGE.deserialize(builder.toString());
    }

    /**
     * Gets a formatted String from a list of strings
     * @param messages Strings
     * @return String
     */
    public String legacyString(String... messages) {
        if (messages == null || messages.length < 1) return null;
        if (messages.length == 1) return MINI_MESSAGE.deserialize(messages[0]).toString();
        StringBuilder builder = new StringBuilder().append(messages[0]);
        Arrays.stream(Arrays.copyOfRange(messages, 1, messages.length)).forEach(s -> builder.append("\n").append(s));
        return LegacyComponentSerializer.legacyAmpersand().serialize(MINI_MESSAGE.deserialize(builder.toString()));
    }

    public String toLegacyString(Component component) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }

    /**
     * Gets component list from text
     * @param text Text in the list
     * @return Component list of text
     */
    public List<Component> getComponentList(String... text) {
        List<Component> components = new ArrayList<>();
        return Arrays.stream(text).map(this::getComponent).collect(Collectors.toList());
    }

    /**
     * Gets component list from text
     * @param text Text in the list
     * @return Component list of text
     */
    public List<Component> getComponentList(List<String> text) {
        List<Component> components = new ArrayList<>();
        return text.stream().map(this::getComponent).collect(Collectors.toList());
    }


    /**
     * Gets component list from text
     * @param text Text in the list
     * @return Component list of text
     */
    public List<Component> getBlankComponentList(String... text) {
        List<Component> components = new ArrayList<>();
        return Arrays.stream(text).map(this::getBlankComponent).collect(Collectors.toList());
    }

    /**
     * Gets component list from text
     * @param text Text in the list
     * @return Component list of text
     */
    public List<Component> getBlankComponentList(List<String> text) {
        List<Component> components = new ArrayList<>();
        return text.stream().map(this::getBlankComponent).collect(Collectors.toList());
    }

    public List<Component> getLoreLines(int charSplitAmount, String... lines) {
        List<Component> components = new ArrayList<>();
        String prevTagFormattingString = "";
        String tagFormattingString = "";
        for (String line : lines) {
            if (line.replaceAll("<[a-zA-Z]+>", "").length() <= charSplitAmount)
                components.add(getBlankComponent(line));
            else {
                int charCountExcludingTags = 0;
                int lastStringSplitCharIndex = 0;
                int tagStart = 0;
                boolean inTag = false;
                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    if (c == '<') {
                        if (x == 0 || line.charAt(x - 1) != '\\') {
                            inTag = true;
                            tagStart = x;
                            continue;
                        }
                        line = line.replaceFirst("\\<", "<");
                    }
                    if (c == '>' && inTag) {
                        inTag = false;
                        String tag = line.substring(tagStart, x + 1);
                        if (!tag.equals("<dot>"))
                            tagFormattingString += line.substring(tagStart, x + 1);
                        else
                            tagFormattingString += "<dark_gray>";
                        continue;
                    }
                    if (inTag) {
                        continue;
                    }
                    charCountExcludingTags++;

                    if (charCountExcludingTags > charSplitAmount && c == ' ') {
                        components.add(getBlankComponent(prevTagFormattingString + line.substring(lastStringSplitCharIndex, x)));
                        lastStringSplitCharIndex = x + 1;
                        charCountExcludingTags = 0;
                        prevTagFormattingString = tagFormattingString;
                    }
                }
                if (line.charAt(line.length() - 1) != ' ') {
                    components.add(getBlankComponent(prevTagFormattingString + line.substring(lastStringSplitCharIndex)));
                }
            }
        }
        return components;
    }

    /**
     * Appends the english order suffix to the given number.
     * @param i the number.
     * @return 1st, 2nd, 3rd, 4th, etc.
     */
    public String numberSuffix(int i) {
        int iModTen = i % 10;
        int iModHundred = i % 100;
        if (iModTen == 1 && iModHundred != 11) return i + "st";
        else if (iModTen == 2 && iModHundred != 12) return i + "nd";
        else if (iModTen == 3 && iModHundred != 13) return i + "rd";
        else return i + "th";
    }

    /**
     * Converts an integer to Roman Numeral
     * @param number Integer
     * @return Roman Equivalent String
     */
    public String toRomanNumeral(int number) {
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder roman = new StringBuilder();
        for(int i = 0; i < VALUES.length; i++) {
            while(number >= VALUES[i]) {
                number -= VALUES[i];
                roman.append(romanLiterals[i]);
            }
        }

        return roman.toString();
    }

    /**
     * Returns the server prefix for the specified text
     * @param text Prefix text
     * @return Server prefix
     */
    public String getPrefix(String text) {
        return "<b><yellow>" + capitalize(text) + " <!b><dark_gray>• <blank>";
    }

    /**
     * Returns the server prefix
     * @return Server prefix
     */
    public String getPrefix() {
        return "<b><yellow>ABUNDANT <!b><dark_gray>• <blank>";
    }

    /**
     * Returns the error prefix
     * @return Error prefix
     */
    public String getErrorPrefix() {
        return "<b><dark_red>ERROR<!b>: <blank><red>";
    }

    /**
     * Formats an error message
     * @param text Message
     * @return Error Message
     */
    public Component getErrorMessage(String text) {
        return getComponent(getErrorPrefix() + text);
    }

    /**
     * Get a list separated by commas
     * @param strings List of Strings
     * @return Comma Separated list
     */
    public String getCommaList(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        int size = strings.size();
        for (String name : strings) {
            count++;
            builder.append(name);
            if (count != size)
                builder.append(", ");
        }
        return builder.toString();
    }

    /**
     * Converts a roman numeral to an integer
     * @param romanNumeral Roman Numeral
     * @return Integer Equivalent
     */
    public int romanToInt(String romanNumeral) {
        if (romanNumeral == null || romanNumeral.length() == 0)
            return -1;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = romanNumeral.length(), result = map.get(romanNumeral.charAt(len - 1));
        for (int i = len - 2; i >= 0; i--) {
            if (map.get(romanNumeral.charAt(i)) >= map.get(romanNumeral.charAt(i + 1)))
                result += map.get(romanNumeral.charAt(i));
            else
                result -= map.get(romanNumeral.charAt(i));
        }

        return result;
    }


    private String capitalize(String str)  {
        if (str == null || str.length() <= 1) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private int getCharCountExcludingTags(String text) {
        int charCountExcludingTags = 0;
        boolean inTag = false;
        for (int x = 0; x < text.length(); x++) {
            char c = text.charAt(x);
            if (c == '<' && x - 1 >= 0 && text.charAt(x - 1) != '\\') {
                inTag = true;
                continue;
            }
            if (c == '>' && inTag) {
                inTag = false;
                continue;
            }
            if (inTag) {
                continue;
            }
            charCountExcludingTags++;
        }
        return charCountExcludingTags;
    }

    /**
     * Return a progress bar string
     * @param progressColor Color of bars that are complete
     * @param emptyColor Color of incomplete bars
     * @param barType Character(s) used in progress bar
     * @param progressBarLength Length of progress bar
     * @param progress progress (0 to 1)
     * @return
     */
    public String getProgressBar(String progressColor, String emptyColor, String barType, int progressBarLength, double progress) {
        String progressString = "";
        int numOfProgChars = (int) Math.floor(progress * progressBarLength);
        if (numOfProgChars == 0) return emptyColor + barType.repeat(progressBarLength);

        return progressColor + barType.repeat(numOfProgChars) + "<blank>" + emptyColor + barType.repeat(progressBarLength - numOfProgChars);
    }

    public String formatMoneyWithCommas(int money) {
        return ("" + money).replaceAll("(?<=\\d)(?=(\\d\\d\\d)+(?!\\d))", ",");
    }
}
